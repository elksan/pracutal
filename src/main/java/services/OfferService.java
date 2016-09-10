package services;

import java.util.List;

import models.*;
import vo.OfferVO;

public interface OfferService {

	List<Offer> getAllOffers(int userRoleId, int userId);
	Offer findOfferById(int offerId);
	void saveOffer(OfferVO offerVo);
	List<Career> getCareers();
	List<OfferTypeVO> getOfferTypes();
	void deleteOffer(int offerId);
	void updateOffer(OfferVO offer);
	void updateOffer(Offer offer);

	Offer approveOffer(int offerId);
	void publishOffer(int offerId);

	void applyForOffer(int offerId, int userId);

	Offer getApplicationsByOfferId(int offerId);
	List<Application> getCandidatesByOfferId(int offerId);

	Application findApplicationById(int applicationId);

	boolean endApplicationProcess(int applicationId, int studentId);

	void setFinalCandidate(int applicationId);

	Boolean studentAlreadyApplied(int studentId, Integer offerId);

	void approveStudentOffer(int offerId);
}
