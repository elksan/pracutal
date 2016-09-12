package dao;

import java.util.List;

import models.*;
import vo.OfferVO;

public interface OfferDao {

	
	List<Offer> getAllOffers();
	Offer findOfferById(int offerId);
	void saveOffer(Offer offer);
	List<Career> getCareers();
	List<Career> getCareers(List<Integer> careerIds);
	List<OfferType> getOfferTypes();
	OfferType getOfferType(int offerId);
	void deleteOffer(int offerId);
	void updateOffer(Offer offer);
	List<Offer> getApprovedOffers();
	List<Offer> getOffers(int organizationId);
	List<Offer> getAvailableOffers();
	void saveApplication(Application application);
	List<Application> getApplicationsByOfferId(int offerId);
	List<Student> getCandidatesByOfferId(int offerId);
	Offer findOfferByIdWithOrganization(int offerId);
	Application findApplicationById(int applicationId);
	Application findApplicationByIdWithOfferAndUser(int applicationId);
	void updateApplication(Application application);
	Offer findOfferByIdWithApplications(int offerId);
	Boolean studentAlreadyApplied(int studentId, Integer offerId);
	List<Application> getUnselectedCandidates(int offerId, int studentId);
	List<Offer> getAvailableOffersByOrganization(Integer organizationId);
}
