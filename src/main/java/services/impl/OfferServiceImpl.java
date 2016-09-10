package services.impl;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import dao.InternshipDao;
import dao.OfferDao;
import dao.UserDao;
import dao.impl.UserDaoImpl;
import etc.ApplicationStatus;
import models.*;
import ninja.i18n.Lang;
import ninja.jpa.UnitOfWork;
import org.hibernate.Hibernate;
import org.hibernate.metamodel.relational.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.InternshipService;
import services.MailService;
import services.OfferService;
import vo.OfferVO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class OfferServiceImpl implements OfferService {

	Logger logger = LoggerFactory.getLogger(OfferServiceImpl.class);
    @Inject
	MailService mailService;

    @Inject
    OfferDao offerDao;

	@Inject
	UserDao userDao;
    
    @Inject
    Lang lang;

	@Inject
	InternshipDao internshipDao;

	@UnitOfWork
	public List<Offer> getAllOffers(int userRoleId, int userId) {

		List<Offer> offers;

		switch (userRoleId){

			case 1:
			case 4:
				offers = offerDao.getApprovedOffers();
				break;
			case 2:
				offers = offerDao.getOffers(userId);
				break;
			default:
				offers = offerDao.getAvailableOffers();
				break;

		}

		/*if(offers != null ) {
			for (Offer offer : offers) {
				Hibernate.initialize(offer.getOfferType());
				Hibernate.initialize(offer.getOrganization());
			}
		}*/

		return offers;
		
	}

	@UnitOfWork
	public List<Offer> getOrganizationOffers(int userId) {

		return offerDao.getAllOffers();

	}

	@UnitOfWork
	public Offer findOfferById(int offerId) {

		return offerDao.findOfferById(offerId);
		
	}

	@UnitOfWork
	public Offer findOfferByIdWithOrganization(int offerId) {

		return offerDao.findOfferByIdWithOrganization(offerId);

	}

	@Transactional
	public void saveOffer(OfferVO offerVo) {


		Offer offer = new Offer(offerVo);

		OfferType offerType = offerDao.getOfferType(offerVo.getOfferTypeId());
		offer.setOfferType(offerType);

		List<Career> careers = offerDao.getCareers(offerVo.getCareers());
		offer.setCareers(careers);

		Organization organization = userDao.getOrganizationById(offerVo.getOrganizationId());
		offer.setOrganization(organization);

		SimpleDateFormat formatter = new SimpleDateFormat("d MMMM, yyyy", Locale.forLanguageTag("es-CL"));
		try {
			offer.setStartDateInternship(formatter.parse(offerVo.getStartDateInternship()));
			offer.setEndDateInternship(formatter.parse(offerVo.getEndDate()));

			offer.setStartDateAvailable(formatter.parse(offerVo.getStartDateAvailable()));
			offer.setEndDateAvailable(formatter.parse(offerVo.getEndDateAvailable()));

			logger.debug("startDate: " + offer.getStartDateInternship() );
			logger.debug("startDate: " + formatter.format(offer.getStartDateInternship()));

			logger.debug("endDate: " + offer.getEndDateInternship() );
			logger.debug("endDate: " + formatter.format(offer.getEndDateInternship()));
			offerDao.saveOffer(offer);

		} catch (ParseException e) {
			e.printStackTrace();
			logger.error("Date could not be processed: " + offerVo.getStartDateInternship() + "or " + offerVo.getEndDate());
		}
		
	}

	@UnitOfWork
	public List<Career> getCareers() {
		return offerDao.getCareers();
	}

	@UnitOfWork
	public List<OfferType> getOfferTypes() {
		return offerDao.getOfferTypes();
	}

	@Transactional
	public void deleteOffer(int offerId) {

		offerDao.deleteOffer(offerId);
	}

	@Transactional
	public void updateOffer(OfferVO offerVo) {

		Offer offer = offerDao.findOfferByIdWithOrganization(offerVo.getId());

		OfferType offerType = offerDao.getOfferType(offerVo.getOfferTypeId());
		offer.setOfferType(offerType);

		List<Career> careers = offerDao.getCareers(offerVo.getCareers());
		offer.setCareers(careers);

		Organization organization = userDao.getOrganizationById(offerVo.getOrganizationId());
		offer.setOrganization(organization);

		SimpleDateFormat formatter = new SimpleDateFormat("d MMMM, yyyy", Locale.forLanguageTag("es-CL"));
		try {
			offer.setStartDateInternship(formatter.parse(offerVo.getStartDateInternship()));
			offer.setEndDateInternship(formatter.parse(offerVo.getEndDate()));

			offer.setStartDateAvailable(formatter.parse(offerVo.getStartDateAvailable()));
			offer.setEndDateAvailable(formatter.parse(offerVo.getEndDateAvailable()));

			logger.debug("startDate: " + offer.getStartDateInternship() );
			logger.debug("startDate: " + formatter.format(offer.getStartDateInternship()));

			logger.debug("endDate: " + offer.getEndDateInternship() );
			logger.debug("endDate: " + formatter.format(offer.getEndDateInternship()));
			offerDao.updateOffer(offer);

		} catch (ParseException e) {
			e.printStackTrace();
			logger.error("Date could not be processed: " + offerVo.getStartDateInternship() + "or " + offerVo.getEndDate());
		}

	}

	@Transactional
	public void updateOffer(Offer offer) {

		offerDao.updateOffer(offer);
	}

	@Transactional
	public Offer approveOffer(int offerId){

		Offer offer = offerDao.findOfferByIdWithOrganization(offerId);
		offer.setApproved(true);

		offerDao.updateOffer(offer);

		User admin = userDao.getAdminUser();
		mailService.newOfferNotification(offer, admin);

		return offer;
	}

	@Transactional
	public void publishOffer(int offerId) {

		Offer offer = offerDao.findOfferById(offerId);

		if(offer.isCreatedByStudent()){

			logger.info("Oferta creada por estudiante");
		}
		offer.setAvailable(true);

		offerDao.updateOffer(offer);
	}

	@Transactional
	public void applyForOffer(int offerId, int userId) {

		Application application = new Application();

		Offer offer = offerDao.findOfferById(offerId);
		application.setOffer(offer);

		Student student = (Student) userDao.getUserById(userId);
		application.setStudent(student);

		offerDao.saveApplication(application);

	}

	@UnitOfWork
	public Offer getApplicationsByOfferId(int offerId) {

		//List<Application> applicationList = offerDao.getApplicationsByOfferId(offerId);
		Offer offer = offerDao.findOfferById(offerId);

		for (Application application : offer.getApplications()){
			Hibernate.initialize(application.getStudent());
		}

		return offer;
	}

	@UnitOfWork
	public List<Application> getCandidatesByOfferId(int offerId) {

		//List<Student> studentList = offerDao.getCandidatesByOfferId(offerId);
		Offer offer = offerDao.findOfferByIdWithApplications(offerId);

		return offer.getApplications();
	}

	@UnitOfWork
	public Application findApplicationById(int applicationId) {

		return offerDao.findApplicationById(applicationId);
	}

	@Transactional
	public boolean endApplicationProcess(int applicationId, int studentId) {

		Application application = offerDao.findApplicationById(applicationId);
		application.setApproved(true);
		application.setStatus(ApplicationStatus.ACEPTADA);

		offerDao.updateApplication(application);

		return true;
	}

	@Transactional
	public void setFinalCandidate(int applicationId) {

		Application application = offerDao.findApplicationByIdWithOfferAndUser(applicationId);
		mailService.notifyFinalCandidate(application);
		application.setApproved(true);

		offerDao.updateApplication(application);
	}

	@UnitOfWork
	public Boolean studentAlreadyApplied(int studentId, Integer offerId) {
		return offerDao.studentAlreadyApplied(studentId, offerId);
	}

	@Transactional
	public void approveStudentOffer(int offerId) {

		Offer offer = offerDao.findOfferById(offerId);
		User user = userDao.getUserById(offer.getCreatedBy());

		Internship internship = new Internship();
		internship.setStudent((Student) user);
		internship.setTitle(offer.getTitle());
		internship.setStartDate(offer.getStartDateInternship());
		internship.setEndDate(offer.getEndDateInternship());
		internship.setOffer(offer);
		internship.setOrganization(offer.getOrganization());


		internshipDao.saveInternship(internship);
		mailService.notifyStudentOfOwnInternship(internship);
		offer.setClosed(true);
		offer.setStudentOfferApproved(true);
		offerDao.updateOffer(offer);
	}
}
