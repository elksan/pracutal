package controllers;

import com.google.inject.Singleton;
import ninja.Result;
import ninja.Results;
import ninja.validation.FieldViolation;
import ninja.validation.JSR303Validation;
import ninja.validation.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vo.OfferVO;

import java.util.List;

/**
 * Created by Diego on 18-05-2016.
 */
@Singleton
public class ValidationController {

	Logger logger = LoggerFactory.getLogger(ValidationController.class);

	public Result validateOffer(@JSR303Validation OfferVO offer, Validation validation){

		try {
			Thread.sleep(1000);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}

		if (validation.hasViolations()) {

			logger.error("El formulario no cumple con las validaciones");

			List<FieldViolation> violations = validation.getBeanViolations();
			Result result = Results.json().status(500).render("violations", violations);
			return result;
		}
		logger.debug("Sin error de validaciones");

		return Results.json();
	}
}
