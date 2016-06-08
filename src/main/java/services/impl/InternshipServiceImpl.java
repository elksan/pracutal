package services.impl;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import dao.InternshipDao;
import dao.OfferDao;
import dao.UserDao;
import models.Internship;
import models.LogbookEntry;
import models.Offer;
import models.User;
import ninja.jpa.UnitOfWork;
import org.hibernate.Hibernate;
import services.InternshipService;

import java.util.List;

/**
 * Created by Diego on 02-06-2016.
 */
public class InternshipServiceImpl implements InternshipService{

	@Inject
	InternshipDao internshipDao;

	@Inject
	UserDao userDao;

	@Inject
	OfferDao offerDao;

	@Transactional
	public void assignInternship(int studentId, int offerId) {

		User user = userDao.getUserById(studentId);
		Offer offer = offerDao.findOfferById(offerId);

		Internship internship = new Internship();
		internship.setStudent(user.getStudent());
		internship.setTitle(offer.getTitle());
		internship.setStartDate(offer.getStartDateInternship());
		internship.setEndDate(offer.getEndDateInternship());
		internship.setOffer(offer);

		internshipDao.saveInternship(internship);
	}

	@UnitOfWork
	public List<Internship> getInternships(int userId) {

		List<Internship> internshipList;

		internshipList = internshipDao.getinternships(userId);

		/*for(Internship internship : internshipList)
			Hibernate.initialize(internship.getOffer().getOrganization().getUser());*/

		return internshipList;
	}

	@UnitOfWork
	public Internship findInternshipById(int internshipId) {

		Internship internship = internshipDao.findInternshipById(internshipId);

		return internship;
	}

	@Transactional
	public void addLogbookEntry(LogbookEntry entry, Integer internshipId) {

		Internship internship = internshipDao.findInternshipById(internshipId);
		entry.setInternship(internship);

		internshipDao.createLogbookEntry(entry);

	}
}
