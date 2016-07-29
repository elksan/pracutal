package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Optional;
import com.google.inject.Singleton;
import etc.ApplicationStatus;
import etc.LoggedInRole;
import etc.LoggedInUser;
import etc.LoggedInUserId;
import models.*;
import ninja.*;
import ninja.i18n.Messages;
import ninja.session.FlashScope;
import ninja.validation.FieldViolation;
import ninja.validation.JSR303Validation;
import ninja.validation.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import ninja.params.PathParam;
import ninja.session.Session;
import services.MailService;
import services.OfferService;
import services.UserService;
import vo.OfferVO;

@Singleton
@FilterWith(SecureFilter.class)
public class OfferController {

	@Inject
	OfferService offerService;
	@Inject
	MailService mailService;
	@Inject
	UserService userService;

	Messages messages;

	@Inject
	OfferController(Messages msg) {
		this.messages = msg;
	}

	Logger logger = LoggerFactory.getLogger(OfferController.class);
	int offerId;
	Map<Integer, Application> applicationMap;
	
	public Result offers(@LoggedInUserId int userId, @LoggedInRole int userRoleId){

		List<Offer> offers = offerService.getAllOffers(userRoleId, userId);

		logger.debug(String.valueOf(offers.size()));

		List<OfferType> offerTypes = offerService.getOfferTypes();
		List<Integer> userLoggedOffers = new ArrayList<>();

		int practices =0;
		int freelance = 0;
		int partTime = 0;
		int fullTime = 0;
		int thesis = 0;

		for (Offer offer : offers){
			if(offer.getOrganization().getId() == userId)
				userLoggedOffers.add(offer.getId());

			switch (offer.getOfferType().getId()){

				case 1:
					practices++;
					break;
				case 2:
					thesis++;
					break;
				case 3:
					fullTime++;
					break;
				case 4:
					partTime++;
					break;
				case 5:
					freelance++;
					break;
			}
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
		Offer offer = offerService.approveOffer(offerId);



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

		Offer offer = offerService.getApplicationsByOfferId(offerId);
		applicationMap = new HashMap<>();

		List<Application> applicationList = offer.getApplications();
		boolean isSomeCandidateApproved = false;
		for(Application application : applicationList){

			applicationMap.put(application.getId(), application);
			if(application.isApproved())
				isSomeCandidateApproved = true;
		}
		Result result = Results.html();
		result.render("applicationList", applicationList);
		result.render("offerId", offerId);
		result.render("isSomeCandidateApproved", isSomeCandidateApproved);
		result.render("offerIsClosed", offer.isClosed());

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

		List<Application> applications = offerService.getCandidatesByOfferId(offerId);
		applicationMap = new HashMap<>();

	/*	for(Application application : applicationList){

			applicationMap.put(application.getId(), application);
		}*/
		Result result = Results.html();
		result.render("applications", applications);
		result.render("offerId", offerId);

		return result;
	}

	public Result chooseFinalCandidate(@PathParam("applicationId") int applicationId, Context context){

		Result result = Results.redirect("/offers");
		FlashScope flashScope = context.getFlashScope();
		offerService.setFinalCandidate(applicationId);
		Optional<String> flashMessage = messages.get("offer.finalCandidate.success", context, Optional.of(result));

		if(flashMessage.isPresent())
			flashScope.success(flashMessage.get());

		return result;
	}

}
