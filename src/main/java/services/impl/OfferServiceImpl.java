package services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;
import com.google.inject.Inject;

import dao.OfferDao;
import dao.impl.UserDaoImpl;
import models.Offer;
import ninja.i18n.Lang;
import ninja.i18n.LangImpl;
import services.OfferService;
import vo.OfferVO;

public class OfferServiceImpl implements OfferService {

Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
    
    @Inject
    OfferDao offerDao;
    
    @Inject
    Lang lang;
    
    
	public List<Offer> getAllOffers() {

		return offerDao.getAllOffers();
		
	}
	
	public Offer findOfferById(int offerId) {

		return offerDao.findOfferById(offerId);
		
	}

	public void saveOffer(OfferVO offerVo) {
		
		Offer offer = new Offer(offerVo);
		
		SimpleDateFormat formatter = new SimpleDateFormat("d MMMM, yyyy", Locale.getDefault());
		try {
			offer.setStartDateInternship(formatter.parse(offerVo.getStartDateInternship()));
			offer.setEndDate(formatter.parse(offerVo.getEndDate()));
			
		} catch (ParseException e) {
			e.printStackTrace();
			logger.error("Date could not be processed: " + offerVo.getStartDateInternship() + "or " + offerVo.getEndDate());
		}
		logger.debug("startDate: " + offer.getStartDateInternship() );
		logger.debug("startDate: " + formatter.format(offer.getStartDateInternship()));
		
		logger.debug("endDate: " + offer.getEndDate() );
		logger.debug("endDate: " + formatter.format(offer.getEndDate()));
		
		offerDao.saveOffer(offer);
		
	}

}
