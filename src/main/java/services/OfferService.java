package services;

import java.util.List;

import models.Offer;

public interface OfferService {

	public List<Offer> getAllOffers();
	public Offer findOfferById(int offerId);
}
