package services;

import java.util.List;

import models.Career;
import models.Offer;
import models.OfferType;
import vo.OfferVO;

public interface OfferService {

	List<Offer> getAllOffers(int userRoleId, int userId);
	Offer findOfferById(int offerId);
	void saveOffer(OfferVO offerVo);
	List<Career> getCareers();
	List<OfferType> getOfferTypes();
	void deleteOffer(int offerId);
	void updateOffer(OfferVO offer);

	void approveOffer(int offerId);
}
