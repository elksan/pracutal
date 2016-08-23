package services;

import models.*;
import ninja.Context;

/**
 * Created by Diego on 12-07-2016.
 */
public interface MailService {

	void sendMailForNewOrganization(Context context, User user);
	void newOfferNotification(Offer offer, User admin);
	void notifyFinalCandidate(Application application);
}
