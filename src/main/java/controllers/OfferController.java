package controllers;

import com.google.inject.Inject;

import models.User;
import ninja.Result;
import ninja.Results;
import ninja.session.Session;
import services.UserService;

public class OfferController {

	@Inject
	UserService userService;
	
	public Result offers(Session session){
		
		
		
		return Results.html();
	}
}
