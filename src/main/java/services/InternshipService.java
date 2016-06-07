package services;

import models.Internship;
import models.LogbookEntry;

import java.util.List;

/**
 * Created by Diego on 02-06-2016.
 */
public interface InternshipService {

	void assignInternship(int studentId, int offerId);
	List<Internship> getInternships(int userId);

	Internship findInternshipById(int internshipId);

	void addLogbookEntry(LogbookEntry entry, Integer internshipId);
}
