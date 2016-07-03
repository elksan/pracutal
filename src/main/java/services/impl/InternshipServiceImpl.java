package services.impl;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import dao.InternshipDao;
import dao.OfferDao;
import dao.UserDao;
import models.*;
import ninja.jpa.UnitOfWork;
import org.hibernate.Hibernate;
import services.InternshipService;

import java.util.Date;
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
		//internship.setStudent(user.getStudent());
		internship.setTitle(offer.getTitle());
		internship.setStartDate(offer.getStartDateInternship());
		internship.setEndDate(offer.getEndDateInternship());
		internship.setOffer(offer);

		internshipDao.saveInternship(internship);
	}

	@UnitOfWork
	public List<Internship> getInternships(int userId, int roleId) {

		List<Internship> internshipList;

		if(roleId == 3) {
			internshipList = internshipDao.getInternshipsByStudentId(userId);
		}
		else{
			//OuserDao.getOrganizationById(userId);
			internshipList = internshipDao.getInternshipsByOrganizationId(userId);
		}
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

	@Transactional
	public void addEvaluation(Integer internshipId, String evaluationName) throws Exception {

		Evaluation evaluation = new Evaluation();
		evaluation.setDate(new Date());
		evaluation.setFilename(evaluationName);
		Internship internship = internshipDao.findInternshipById(internshipId);
		evaluation.setInternship(internship);
		if(evaluation.getInternship() == null) {
			throw new Exception("could not find internship with id: " + internshipId);
		}
		internship.getEvaluations().add(evaluation);

		internshipDao.updateinternship(internship);

	}

	@UnitOfWork
	public List<Evaluation> getInternshipEvaluations(Integer internshipId) {

		return internshipDao.findInternshipById(internshipId).getEvaluations();
	}
}
