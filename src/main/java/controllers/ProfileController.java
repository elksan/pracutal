package controllers;

import com.google.inject.Inject;

import models.User;
import ninja.Result;
import ninja.Results;
import ninja.session.Session;
import services.UserService;

public class ProfileController {

	@Inject
	UserService userService;
	
	public Result profile(Session session){
		

		try {
		User user = userService.getUserById(session.get("username"));
		
		Result result = Results.html();
		result.render(user);
		
		
		
			
		} catch (Exception e) {
			//session.clear();
		}
		
		return Results.html();
	}
}
