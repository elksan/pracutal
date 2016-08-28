package services;

import models.*;
import ninja.Context;

import java.util.List;

/**
 * Created by Diego on 12-07-2016.
 */
public interface MailService {

	void sendMailForNewUser(Context context, User user);
	void newOfferNotification(Offer offer, User admin);
	void notifyFinalCandidate(Application application);
	void notifyUnselectedStudents(List<Application> applicationList);
}
