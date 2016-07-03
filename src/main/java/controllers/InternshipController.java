package controllers;

import com.google.common.io.Files;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import etc.LoggedInRole;
import etc.LoggedInUserId;
import models.Evaluation;
import models.Internship;
import models.LogbookEntry;
import ninja.*;
import ninja.params.Param;
import ninja.params.PathParam;
import ninja.uploads.DiskFileItemProvider;
import ninja.uploads.FileItem;
import ninja.uploads.FileProvider;
import services.InternshipService;

import java.io.File;
import java.util.List;

/**
 * Created by Diego on 01-06-2016.
 */
@Singleton
@FilterWith(SecureFilter.class)
public class InternshipController {

	@Inject
	InternshipService internshipService;
	private Integer internshipId;

	public Result assignInternship(@PathParam("studentId") int studentId, @PathParam("offerId") int offerId){

		internshipService.assignInternship(studentId, offerId);

		return Results.redirect("/offers");
	}

	public Result internships(@LoggedInUserId int userId, @LoggedInRole int roleId){

		List<Internship> internshipList = internshipService.getInternships(userId, roleId);
		Result result = Results.html();
		result.render("internshipList", internshipList);
		return result;
	}

	public Result logbook(@PathParam("internshipId") int internshipId){

		Internship internship = internshipService.findInternshipById(internshipId);

		Result result = Results.html();
		result.render(internship);

		this.internshipId = internship.getId();

		return result;
	}

	@FileProvider(DiskFileItemProvider.class)
	public Result evaluationUploadFinish(@LoggedInUserId int userId, Context context, @Param("upfile") FileItem upfile) throws Exception {

		String evaluationName = userId + "_" + upfile.getFileName();
		File destination = new File("src/main/java/assets/uploadedContent/"+ evaluationName);

		if(!destination.createNewFile())
			return Results.badRequest();

		Files.copy(upfile.getFile(), destination );
		internshipService.addEvaluation(internshipId, evaluationName);

		return Results.redirect("/evaluate");
	}

	public Result logbookNewEntry(){

		return Results.html();
	}

	public Result logbookSaveEntry(LogbookEntry entry){

		internshipService.addLogbookEntry(entry, internshipId);
		return Results.redirect("/logbook/" + internshipId);
	}

	public Result evaluate(){

		List<Evaluation> evaluations = internshipService.getInternshipEvaluations(internshipId);

		return Results.html().render("evaluationFiles", evaluations);

	}
}
