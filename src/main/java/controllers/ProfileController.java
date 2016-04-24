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
		
		Result result = null;
		
		try {
			User user = userService.getUserById(session.get("username"));
			
			result = Results.html();
			result.render(user);

			session.put("role", user.getRoles().get(0).getId().toString());
			session.put("userId", user.getId().toString());
		
		
		
			
		} catch (Exception e) {
			//session.clear();
		}
		
		return result;
	}
}
