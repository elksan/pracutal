package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.inject.Singleton;
import etc.LoggedInRole;
import etc.LoggedInUserId;
import models.*;
import ninja.*;
import ninja.validation.FieldViolation;
import ninja.validation.JSR303Validation;
import ninja.validation.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import ninja.params.PathParam;
import ninja.session.Session;
import services.OfferService;
import vo.OfferVO;

@Singleton
@FilterWith(SecureFilter.class)
public class OfferController {

	@Inject
	OfferService offerService;

	Logger logger = LoggerFactory.getLogger(OfferController.class);
	int offerId;
	Map<Integer, Application> applicationMap;
	
	public Result offers(@LoggedInUserId int userId, @LoggedInRole int userRoleId){

		List<Offer> offers = offerService.getAllOffers(userRoleId, userId);

		logger.debug(String.valueOf(offers.size()));

		if(!offers.isEmpty()) {
			logger.debug(String.valueOf(offers.get(0).getDescription()));

		}
		List<OfferType> offerTypes = offerService.getOfferTypes();
		List<Integer> userLoggedOffers = new ArrayList<>();

		for (Offer offer : offers){
			if(offer.getOrganization().getId() == userId)
				userLoggedOffers.add(offer.getId());
		}


		Result result = Results.html();
		result.render("offers", offers);
		result.render("offerTypes", offerTypes);
		result.render("userLoggedOffers", userLoggedOffers);

		return result;
	}

	public Result offerDetails(Session session, @PathParam("offerId") int offerId){

		this.offerId = offerId;
		Offer selectedOffer = offerService.findOfferById(offerId);
        OfferVO offerVO = new OfferVO(selectedOffer);
		return Results.html().render("selectedOffer", offerVO);
	}

	public Result newOffer(Session session){

		List<Career> careers = offerService.getCareers();
		List<OfferType> offerTypes = offerService.getOfferTypes();

		Result result = Results.html();
		result.render("careers", careers);
		result.render("offerTypes", offerTypes);

		return  result;
	}

	public Result saveOffer(@LoggedInUserId int userId, OfferVO offer){

        try {

            logger.debug("OFERTYPE>>>>>> " + offer.getOfferType());

            offer.setOrganizationId(userId);
            offerService.saveOffer(offer);

            return Results.redirect("/offers");

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return Results.redirect("/404notFound");
        }
	}

	public Result editOffer(Session session, @PathParam("offerId") int offerId){

		List<Career> careers = offerService.getCareers();
		List<OfferType> offerTypes = offerService.getOfferTypes();

		Offer selectedOffer = offerService.findOfferById(offerId);
		OfferVO offerVO = new OfferVO(selectedOffer);
		this.offerId = offerId;

		Result result = Results.html();
		result.render("selectedOffer", offerVO);
		result.render("careers", careers);
		result.render("offerTypes", offerTypes);

		return  result;
	}


	public Result updateOffer(Session session, OfferVO offer){

		logger.debug("OFERTYPE>>>>>> " + offer.getOfferType());
		logger.debug("Updating offer id: " + offerId);
		offer.setId(offerId);

		offer.setOrganizationId(Integer.parseInt(session.get("userId")));

		offerService.updateOffer(offer);

		return Results.redirect("/offers");
	}

	public Result deleteOffer(Session session, @PathParam("offerId") int offerId){

		logger.info("deleting offer, id: " + offerId);

		offerService.deleteOffer(offerId);

		return Results.redirect("/offers");
	}

	public Result approveOffer(Session session){

		logger.info("approving offer, id: " + offerId);

		offerService.approveOffer(offerId);

		return Results.redirect("/offers");
	}

	public Result publishOffer(Session session){

		logger.info("publishing offer, id: " + offerId);

		offerService.publishOffer(offerId);

		return Results.redirect("/offers");
	}

	public Result applyForOffer(@LoggedInUserId int userId){

		logger.info("applying for offer, userId: " + userId + " offerId: " + offerId);

		offerService.applyForOffer(offerId, userId);

		return Results.redirect("/offers");
	}

	public Result viewApplicants(){

		List<Application> applicationList = offerService.getApplicationsByOfferId(offerId);
		applicationMap = new HashMap<>();

		for(Application application : applicationList){

			applicationMap.put(application.getId(), application);
		}
		Result result = Results.html();
		result.render("applicationList", applicationList);
		result.render("offerId", offerId);

		return result;
	}

	public Result selectCandidate(Application selectedApplication){

		Application appToModify = applicationMap.get(selectedApplication.getId());
		appToModify.setCandidate(selectedApplication.isCandidate());

		return Results.ok();
	}

	public Result saveCandidates(){

		Offer offer = offerService.findOfferById(offerId);
		offer.setApplications(new ArrayList<>(applicationMap.values()));

		offerService.updateOffer(offer);

		return Results.redirect("/offers");
	}


	public Result candidates(){

		List<Student> studentList = offerService.getCandidatesByOfferId(offerId);
		applicationMap = new HashMap<>();

	/*	for(Application application : applicationList){

			applicationMap.put(application.getId(), application);
		}*/
		Result result = Results.html();
		result.render("studentList", studentList);
		result.render("offerId", offerId);

		return result;
	}

}
