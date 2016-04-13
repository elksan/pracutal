package dao;

import java.util.List;

import models.Career;
import models.Offer;

public interface OfferDao {

	
	public List<Offer> getAllOffers();
	public Offer findOfferById(int offerId);
	public void saveOffer(Offer offer);
	public List<Career> getCareers();
}
