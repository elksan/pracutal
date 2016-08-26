package controllers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import etc.ExcelReader;
import models.Application;
import models.Organization;
import models.Student;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.exceptions.BadRequestException;
import ninja.params.Param;
import ninja.params.PathParam;
import ninja.session.FlashScope;
import ninja.uploads.DiskFileItemProvider;
import ninja.uploads.FileItem;
import ninja.uploads.FileProvider;
import ninja.validation.FieldViolation;
import ninja.validation.JSR303Validation;
import ninja.validation.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.AdminService;
import services.MailService;
import services.UserService;
import vo.ActivationFormVO;
import vo.OrganizationVO;
import vo.ResultVO;
import vo.UserVO;

import java.util.List;

@Singleton
public class AdminController {

	@Inject
	AdminService adminService;

	@Inject
	UserService userService;

	@Inject
	MailService mailService;

	@Inject
	ExcelReader excelReader;

	Logger logger = LoggerFactory.getLogger(AdminController.class);

	String tokenGlobal;
	Integer organizationId;

	public Result newStudent() {
		return Results.html();
	}
	
	public Result menu() {
		return Results.html();
	}

	public Result react() {
		return Results.html();
	}

	public Result newOrganization() {
		return Results.html();
	}

	public Result addOrganization(Context context, @JSR303Validation OrganizationVO organizationVO, Validation validation){

		Organization organization = userService.saveOrganization(organizationVO);

		ResultVO resultVO = new ResultVO();
		resultVO.setRedirect("/admin");

		FlashScope flashScope = context.getFlashScope();
		flashScope.success("organization.createSuccessful");

		mailService.sendMailForNewOrganization(context, organization);

		return Results.json().render(resultVO);

	}

	public Result editOrganization(@PathParam("organizationId") int organizationId) {

		Organization organization = userService.getOrganizationById(organizationId);
		Result result = Results.html().template("views/AdminController/newOrganization.ftl.html");
		result.render("organization", organization);

		this.organizationId = organization.getId();
		return result;
	}

	public Result updateOrganization(Context context, @JSR303Validation OrganizationVO organizationVO, Validation validationd) {

		organizationVO.setId(organizationId);
		Organization organization = userService.updateOrganization(organizationVO);

		ResultVO resultVO = new ResultVO();
		resultVO.setRedirect("/admin");

		FlashScope flashScope = context.getFlashScope();
		flashScope.success("organization.updateSuccessful");

		return Results.json().render(resultVO);
	}

	public Result accountRegistration(@PathParam("token") String token){
		try {
			boolean tokenIsValid = adminService.verifyToken(token);
			this.tokenGlobal = token;
			if (tokenIsValid) {
				return Results.html();
			} else {
				throw new BadRequestException();
			}
		}
		catch(Exception ex){
			logger.error(ex.getLocalizedMessage());
			throw new BadRequestException();
		}
	}

	public Result activateUser(@JSR303Validation ActivationFormVO activationFormVO, Validation validation){

		if (validation.hasViolations()) {

			logger.error("El formulario no cumple con las validaciones");
			logger.error(validation.getBeanViolations().toString());

			List<FieldViolation> violations = validation.getBeanViolations();
			Result result = Results.json().status(500).render("violations", violations);
			return result;
		}
		logger.debug("Sin error de validaciones");

		activationFormVO.setToken(tokenGlobal);
		ResultVO resultVO = null;
		try {
			adminService.activateUser(activationFormVO);

			resultVO = new ResultVO();
			resultVO.setRedirect("/login");
		}
		catch (Exception ex){
			logger.error(ex.getLocalizedMessage());
			return Results.json().status(400);
		}
		return Results.json().render(resultVO);
	}


	public Result students(){

		List<Student> students = userService.getAllStudents();

		Result result = Results.html();
		result.render("students", students);

		return result;
	}
	public Result organizations(){

		List<OrganizationVO> organizations = userService.getOrganizations();

		Result result = Results.html();
		result.render("organizations", organizations);

		return result;
	}

	public Result reports(){

		return Results.html();
	}


	public Result addStudents(){

		return Results.html();
	}


	@FileProvider(DiskFileItemProvider.class)
	public Result importStudents(@Param("upfile") FileItem upfile, Context context){

		upfile.getFileName();

		List<Student> studentList = excelReader.readStudentsFromExcel(upfile);
		userService.saveStudentList(studentList);
		for(Student student : studentList){

			mailService.sendMailForNewOrganization(context, student);
		}

		return Results.redirect("/admin");
	}


	public Result getStudentsWithInternshipAssigned(){

		Result result = Results.html();
		List<Application> applicationList = userService.getStudentsWithInternshipAssigned();

		result.render("applicationList", applicationList);
		return result;
	}


	public Result generateAdminAccount(UserVO userVO){
		adminService.generateAdminAccount(userVO);

		return Results.ok();
	}
}
