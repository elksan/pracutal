package services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import dao.OfferDao;
import dao.impl.UserDaoImpl;
import models.Offer;
import services.OfferService;

public class OfferServiceImpl implements OfferService {

Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
    
    @Inject
    OfferDao offerDao;
    
    
	public List<Offer> getAllOffers() {

		return offerDao.getAllOffers();
		
	}
	
	public Offer findOfferById(int offerId) {

		return offerDao.findOfferById(offerId);
		
	}

}
