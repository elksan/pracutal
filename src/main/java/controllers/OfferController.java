package controllers;

import java.util.List;

import com.google.inject.Singleton;
import models.Career;
import models.OfferType;
import ninja.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import models.Offer;
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
		
	
	public Result offers(Session session){

		List<Offer> offers = offerService.getAllOffers();

		logger.debug(String.valueOf(offers.size()));

		if(!offers.isEmpty()) {
			logger.debug(String.valueOf(offers.get(0).getDescription()));

		}
		List<OfferType> offerTypes = offerService.getOfferTypes();

		Result result = Results.html();
		result.render("offers", offers);
		result.render("offerTypes", offerTypes);

		return result;
	}

	public Result offerDetails(Session session, @PathParam("offerId") int offerId){

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

	public Result saveOffer(Session session, OfferVO offer){

        logger.debug("OFERTYPE>>>>>> " + offer.getOfferType());

		offer.setOrganizationId(Integer.parseInt(session.get("userId")));

		offerService.saveOffer(offer);

		return Results.redirect("/offers");
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


}
