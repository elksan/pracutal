package services.impl;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import dao.OfferDao;
import dao.UserDao;
import dao.impl.UserDaoImpl;
import models.Career;
import models.Offer;
import models.OfferType;
import models.Organization;
import ninja.i18n.Lang;
import ninja.jpa.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.OfferService;
import vo.OfferVO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class OfferServiceImpl implements OfferService {

Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
    
    @Inject
    OfferDao offerDao;

	@Inject
	UserDao userDao;
    
    @Inject
    Lang lang;

	@UnitOfWork
	public List<Offer> getAllOffers(int userRoleId, int userId) {

		if(userRoleId != 1)
			return offerDao.getApprovedOffers(userId);
		else
			return offerDao.getAllOffers();
		
	}

	@UnitOfWork
	public List<Offer> getOrganizationOffers(int userId) {

		return offerDao.getAllOffers();

	}

	@UnitOfWork
	public Offer findOfferById(int offerId) {

		return offerDao.findOfferById(offerId);
		
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
	public void approveOffer(int offerId){

		Offer offer = offerDao.findOfferById(offerId);
		offer.setApproved(true);

		offerDao.updateOffer(offer);
	}
}
