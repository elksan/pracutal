package controllers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import ninja.FilterWith;
import ninja.Result;
import ninja.Results;
import ninja.SecureFilter;
import ninja.params.PathParam;
import services.InternshipService;

/**
 * Created by Diego on 01-06-2016.
 */
@Singleton
@FilterWith(SecureFilter.class)
public class InternshipController {

	@Inject
	InternshipService internshipService;

	public Result assignInternship(@PathParam("studentId") int studentId){

		internshipService.assignInternship(studentId);

		return Results.redirect("/offers");
	}
}
