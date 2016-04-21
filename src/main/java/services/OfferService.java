package services;

import java.util.List;

import models.Career;
import models.Offer;
import models.OfferType;
import vo.OfferVO;

public interface OfferService {

	public List<Offer> getAllOffers();
	public Offer findOfferById(int offerId);
	public void saveOffer(OfferVO offerVo);
	public List<Career> getCareers();
	public List<OfferType> getOfferTypes();
}
