package controllers;

import java.util.List;

import models.Career;
import models.OfferType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import dto.OffersDto;
import models.Offer;
import ninja.Result;
import ninja.Results;
import ninja.params.PathParam;
import ninja.session.Session;
import services.OfferService;
import vo.OfferVO;

public class OfferController {

	@Inject
	OfferService offerService;
	
	Logger logger = LoggerFactory.getLogger(OfferController.class);
		
	
	public Result offers(Session session){
		
		OffersDto offersDto = new OffersDto();
		
		offersDto.offers = offerService.getAllOffers();
		List<Offer> offers = offersDto.offers;

		logger.debug(String.valueOf(offersDto.offers.size()));

		if(!offersDto.offers.isEmpty())
			logger.debug(String.valueOf(offersDto.offers.get(0).getDescription()));

		List<OfferType> offerTypes = offerService.getOfferTypes();

		Result result = Results.html();
		result.render("offers", offers);
		result.render("offerTypes", offerTypes);

		return result;
	}

	public Result offerDetails(Session session, @PathParam("offerId") int offerId){

		Offer selectedOffer = offerService.findOfferById(offerId);
		return Results.html().render(selectedOffer);
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

		offerService.saveOffer(offer);

		List<Offer> offers = offerService.getAllOffers();
        List<OfferType> offerTypes = offerService.getOfferTypes();

        Result result = Results.html();
        result.render("offers", offers);
        result.render("offerTypes", offerTypes);

		return result.template("/views/OfferController/offers.ftl.html");
	}
}
