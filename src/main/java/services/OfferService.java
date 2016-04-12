package services;

import java.util.List;

import models.Offer;
import vo.OfferVO;

public interface OfferService {

	public List<Offer> getAllOffers();
	public Offer findOfferById(int offerId);
	public void saveOffer(OfferVO offerVo);
}
