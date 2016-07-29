package services;

import models.Evaluation;
import models.Internship;
import models.LogbookEntry;

import java.util.List;

/**
 * Created by Diego on 02-06-2016.
 */
public interface InternshipService {

	void assignInternship(int studentId, int offerId);
	void assignInternship(int applicationId);
	List<Internship> getInternships(int userId, int roleId);

	Internship findInternshipById(int internshipId);

	void addLogbookEntry(LogbookEntry entry, Integer internshipId);

	void addEvaluation(Integer internshipId, String evaluationName) throws Exception;

	List<Evaluation> getInternshipEvaluations(Integer internshipId);


}
