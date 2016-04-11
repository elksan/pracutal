package controllers;

import java.util.List;

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

public class OfferController {

	@Inject
	OfferService offerService;
	
	Logger logger = LoggerFactory.getLogger(OfferController.class);
		
	
	public Result offers(Session session){
		
		OffersDto offersDto = new OffersDto();
		
		offersDto.offers = offerService.getAllOffers();
	
		List<Offer> offers = offersDto.offers;
		
		logger.debug(String.valueOf(offersDto.offers.size()));
		logger.debug(String.valueOf(offersDto.offers.get(0).getDescription()));
		
		return Results.html().render("offers",offers);
	}
	
	public Result offerDetails(Session session, @PathParam("offerId") int offerId){
		
		Offer selectedOffer = offerService.findOfferById(offerId);
		return Results.html().render(selectedOffer);
	}
	
	public Result newOffer(Session session){
		
		return Results.html();
	}

	public Result saveOffer(Session session, Offer offer){
		
		return Results.html();
	}
}
