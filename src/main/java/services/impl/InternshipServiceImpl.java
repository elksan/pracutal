package services.impl;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import dao.InternshipDao;
import dao.OfferDao;
import dao.UserDao;
import etc.ApplicationStatus;
import etc.UserRole;
import models.*;
import ninja.jpa.UnitOfWork;
import org.hibernate.Hibernate;
import services.InternshipService;
import services.MailService;

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

	@Inject
	MailService mailService;

	@Transactional
	public void assignInternship(int studentId, int offerId) {

		User user = userDao.getUserById(studentId);
		Offer offer = offerDao.findOfferById(offerId);

		Internship internship = new Internship();
		internship.setStudent((Student) user);
		internship.setTitle(offer.getTitle());
		internship.setStartDate(offer.getStartDateInternship());
		internship.setEndDate(offer.getEndDateInternship());
		internship.setOffer(offer);


		List<Application> unselectedCandidates = offerDao.getUnselectedCandidates(offerId, studentId);
		mailService.notifyUnselectedStudents(unselectedCandidates);

		internshipDao.saveInternship(internship);
	}

	@Transactional
	public void assignInternship(int applicationId) {

		Application application = offerDao.findApplicationByIdWithOfferAndUser(applicationId);
		Internship internship = new Internship();
		internship.setTitle(application.getOffer().getTitle());
		internship.setStartDate(application.getOffer().getStartDateInternship());
		internship.setEndDate(application.getOffer().getEndDateInternship());
		internship.setOffer(application.getOffer());
		internship.setStudent(application.getStudent());
		internship.setOrganization(application.getOffer().getOrganization());

		application.getOffer().setClosed(true);
		application.setStatus(ApplicationStatus.ACEPTADA);

		offerDao.updateOffer(application.getOffer());
		offerDao.updateApplication(application);
		internshipDao.saveInternship(internship);

		List<Application> unselectedCandidates = offerDao.getUnselectedCandidates(application.getOffer().getId(), application.getStudent().getId());
		mailService.notifyUnselectedStudents(unselectedCandidates);
	}

	@UnitOfWork
	public List<Internship> getInternships(int userId, int roleId) {

		List<Internship> internshipList;
		UserRole userRole = UserRole.valueOf(userDao.findRoleById(roleId).getName().toUpperCase());

		switch (userRole){
			case ESTUDIANTE:
				internshipList = internshipDao.getInternshipsByStudentId(userId);
				break;
			case DIRECTOR:
			case ADMINISTRADOR:
				internshipList = internshipDao.getAllInternships();
				break;
			case EMPRESA:
				internshipList = internshipDao.getInternshipsByOrganizationId(userId);
				break;
			default:
				internshipList = internshipDao.getInternshipsByOrganizationId(userId);
				break;
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
		Internship internship = internshipDao.findInternshipWithEvaluationsById(internshipId);
		evaluation.setInternship(internship);
		if(evaluation.getInternship() == null) {
			throw new Exception("could not find internship with id: " + internshipId);
		}
		internship.getEvaluations().add(evaluation);
		internship.setClosed(true);
		internshipDao.updateinternship(internship);

	}

	@UnitOfWork
	public List<Evaluation> getInternshipEvaluations(Integer internshipId) {

		return internshipDao.findInternshipWithEvaluationsById(internshipId).getEvaluations();
	}
}
