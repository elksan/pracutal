package controllers;

import com.google.common.io.Files;
import com.google.inject.Inject;

import com.google.inject.Singleton;
import etc.LoggedInUser;
import etc.LoggedInUserId;
import etc.UserRole;
import models.*;
import ninja.*;
import ninja.params.Param;
import ninja.params.PathParam;
import ninja.session.FlashScope;
import ninja.session.Session;
import ninja.uploads.DiskFileItemProvider;
import ninja.uploads.FileItem;
import ninja.uploads.FileProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.OfferService;
import services.UserService;
import vo.OfferVO;
import vo.OrganizationVO;
import vo.ResultVO;
import vo.StudentVO;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Singleton
@FilterWith(SecureFilter.class)
public class ProfileController {

	@Inject
	UserService userService;
	@Inject
	OfferService offerService;

	Logger logger = LoggerFactory.getLogger(OfferController.class);
	
	public Result profile(@LoggedInUserId Integer userId){

		Result result = null;

		try {
			User user = userService.findUserWithAddress(userId);

			UserRole role = UserRole.valueOf(user.getRoles().get(0).getName().toUpperCase());
			switch (role){

				case ADMINISTRADOR:
					result = Results.redirect("/admin");
					break;
				case DIRECTOR:
					result = Results.redirect("/offers");
					break;
				case EMPRESA:
					result = Results.html().template("views/ProfileController/organizationProfile.ftl.html");
					List<OfferVO> offers = offerService.getAvailableOffersByOrganization(user.getId());
					result.render("organization", user);
					result.render("offers", offers);
					break;
				case ESTUDIANTE:
					result = Results.html().template("views/ProfileController/studentProfile.ftl.html");
					List<Internship> completedInternships = userService.getCompletedInternshipsByUserId(user.getId());
					result.render("user", user);
					result.render("completedInternships", completedInternships);
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}


	public Result myApplications(@LoggedInUserId int userId){

		logger.info("getApplications, userId: " + userId);

		List<Application> applicationList = userService.getApplicationsByUserId(userId);

		Result result = Results.html();
		result.render("applications", applicationList);

		return result;
	}

	public Result viewOrganizationProfile(@PathParam("organizationId") int organizationId){

		Result result = null;

		try {
			User user = userService.findUserWithAddress(organizationId);
			result = Results.html().template("views/ProfileController/organizationProfile.ftl.html");
			List<OfferVO> offers = offerService.getAvailableOffersByOrganization(user.getId());
			result.render("organization", user);
			result.render("offers", offers);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public Result editOrganizationProfile(@LoggedInUserId Integer organizationId){

		Organization organization = (Organization) userService.findUserWithAddress(organizationId);
		Result result = Results.html().template("views/AdminController/newOrganization.ftl.html");
		result.render("organization", organization);
		result.render("fromProfile", true);
		return result;
	}

	public Result updateOrganizationProfile(@LoggedInUserId Integer organizationId, OrganizationVO organizationVO, Context context){

		organizationVO.setId(organizationId);
		userService.updateOrganization(organizationVO);

		ResultVO resultVO = new ResultVO();
		resultVO.setRedirect("/profile");

		FlashScope flashScope = context.getFlashScope();
		flashScope.success("profile.updateSuccessful");

		return Results.json().render(resultVO);
	}

	@FileProvider(DiskFileItemProvider.class)
	public Result uploadProfilePictureFinish(@Param("upfile") FileItem upfile, @LoggedInUserId int userId, FlashScope flashScope) {
		String filename = userId + "_" + upfile.getFileName();
		File destination = new File("src/main/java/assets/uploadedContent/"+ filename);
		String path = "/assets/uploadedContent/" + filename;
		try {
			if(!destination.createNewFile()) {
				logger.info("File " + destination + "already exists!, deleting...");
				destination.delete();
				logger.info("Deletion successful");
			}
			Files.copy(upfile.getFile(), destination );
			userService.updateProfilePhoto(userId, filename);

		}
		catch (IOException e) {
			logger.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
		return Results.json().render(path);
	}

	public Result editStudentProfile(@LoggedInUserId Integer studentId){

		Student student = (Student) userService.findUserWithAddress(studentId);
		Result result = Results.html().template("views/AdminController/newStudent.ftl.html");
		result.render("student", student);
		result.render("fromProfile", true);
		return result;
	}

	public Result updateStudentProfile(@LoggedInUserId Integer studentId, StudentVO studentVO, Context context){

		studentVO.setId(studentId);
		userService.updateStudent(studentVO);

		ResultVO resultVO = new ResultVO();
		resultVO.setRedirect("/profile");

		FlashScope flashScope = context.getFlashScope();
		flashScope.success("profile.updateSuccessful");

		return Results.json().render(resultVO);
	}

	@FileProvider(DiskFileItemProvider.class)
	public Result uploadCurriculumFinish(@Param("upfile") FileItem upfile, @LoggedInUserId int userId, FlashScope flashScope) {
		String filename = userId + "_" + upfile.getFileName();
		File destination = new File("src/main/java/assets/uploadedContent/curriculums/"+ filename);
		String path = "/assets/uploadedContent/curriculum" + filename;
		try {
			if(!destination.createNewFile()) {
				logger.info("File " + destination + "already exists!, deleting...");
				destination.delete();
				logger.info("Deletion successful");
			}
			Files.copy(upfile.getFile(), destination );
			logger.info("File " + destination + "successfully created");
			userService.updateCurriculum(userId, filename);
			logger.info("Pofile updated");

		}
		catch (IOException e) {
			logger.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
		return Results.json().render(path);
	}
}
