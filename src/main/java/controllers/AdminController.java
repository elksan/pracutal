package controllers;

import com.google.inject.Inject;
import models.Organization;
import models.User;
import ninja.Result;
import ninja.Results;
import services.AdminService;
import vo.OrganizationVO;
import vo.ResultVO;
import vo.UserVO;

public class AdminController {

	@Inject
	AdminService adminService;

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
	public Result addOrganization(OrganizationVO organizationVO){

		adminService.saveOrganization(organizationVO);

		ResultVO resultVO = new ResultVO();
		resultVO.setRedirect("/admin");

		return Results.json().render(resultVO);

	}
}
