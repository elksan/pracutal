package dao;

import models.Internship;
import models.LogbookEntry;

import java.util.List;

/**
 * Created by Diego on 02-06-2016.
 */
public interface InternshipDao {

	void saveInternship(Internship internship);
	List<Internship> getInternshipsByStudentId(int userId);
	List<Internship> getInternshipsByOrganizationId(int userId);

	Internship findInternshipById(int internshipId);

	void updateinternship(Internship internship);
	void createLogbookEntry(LogbookEntry logbookEntry);

	Internship findInternshipWithEvaluationsById(int internshipId);

	List<Internship> getAllInternships();
}
