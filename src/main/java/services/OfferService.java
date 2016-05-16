package services;

import java.util.List;

import models.*;
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
	void publishOffer(int offerId);

	void applyForOffer(int offerId, int userId);

	List<Application> getApplicationsByOfferId(int offerId);
}
