package dao;

import java.util.List;

import models.Offer;

public interface OfferDao {

	
	public List<Offer> getAllOffers();
	public Offer findOfferById(int offerId);
}
