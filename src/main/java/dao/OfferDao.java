package dao;

import java.util.List;

import models.Career;
import models.Offer;
import models.OfferType;

public interface OfferDao {

	
	List<Offer> getAllOffers();
	Offer findOfferById(int offerId);
	void saveOffer(Offer offer);
	List<Career> getCareers();
	List<OfferType> getOfferTypes();
	OfferType getOfferType(int offerId);
}
