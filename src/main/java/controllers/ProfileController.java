package controllers;

import com.google.inject.Inject;

import com.google.inject.Singleton;
import etc.LoggedInUserId;
import etc.UserRole;
import models.Application;
import models.User;
import ninja.FilterWith;
import ninja.Result;
import ninja.Results;
import ninja.SecureFilter;
import ninja.i18n.Messages;
import ninja.params.PathParam;
import ninja.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.UserService;

import java.util.List;

@Singleton
@FilterWith(SecureFilter.class)
public class ProfileController {

	@Inject
	UserService userService;

	Logger logger = LoggerFactory.getLogger(OfferController.class);
	
	public Result profile(Session session){

		Result result = null;

		try {
			User user = userService.getUserById(session.get("username"));

			UserRole role = UserRole.valueOf(user.getRoles().get(0).getName().toUpperCase());
			switch (role){

				case ADMINISTRADOR:
					result = Results.redirect("/admin");
					break;
				case DIRECTOR:
					result = Results.redirect("/offers");
					break;
				default:
					result = Results.html();
					result.render("user", user);
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
			User user = userService.getOrganizationById(organizationId);

				result = Results.html().template("views/ProfileController/profile.ftl.html");
				result.render("user", user);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}
