package controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import dto.OffersDto;
import models.Offer;
import ninja.Result;
import ninja.Results;
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
}
