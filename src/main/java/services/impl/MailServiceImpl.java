package services.impl;

import com.google.inject.Inject;
import com.google.inject.Provider;
import freemarker.template.Configuration;
import freemarker.template.Template;
import models.*;
import ninja.Context;
import ninja.postoffice.Mail;
import ninja.postoffice.Postoffice;
import ninja.utils.NinjaProperties;
import org.apache.commons.mail.EmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.MailService;
import services.UserService;
import vo.VerificationToken;

import javax.mail.internet.AddressException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Diego on 03-07-2016.
 */
public class MailServiceImpl implements MailService {

	@Inject
	Provider<Mail> mailProvider;

	@Inject
	Postoffice postoffice;

	@Inject
	UserService userService;

	@Inject
	NinjaProperties ninjaProperties;

	Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

	public void sendMailForNewOrganization(Context context, User user) {

		Mail mail = mailProvider.get();

		// fill the mail with content:
		mail.setSubject("Su cuenta en la Plataforma del Centro de Prácticas ha sido creada.");
		mail.setFrom(ninjaProperties.get("smtp.user"));
		mail.setCharset("utf-8");
		mail.addTo(user.getEmail());



		VerificationToken lastToken = user.getTokens().get(0);
		if(lastToken.isVerified())
			logger.error("Token already verified" + lastToken.getToken());

		Map<String, Object> params = new HashMap<>();
		params.put("user", user);

		String url = context.getHostname()+context.getContextPath()+"/registerUser/"+ lastToken.getToken();
		params.put("url", url);

		mail.setBodyHtml(setBodyHtmlFromTemplate("newUserEmail.ftl.html", params));

		// finally send the mail
		try {
			postoffice.send(mail);
		} catch (EmailException | AddressException e) {
			logger.error(e.getLocalizedMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void newOfferNotification(Offer offer, User admin) {

		logger.info("sending email notification:");
		logger.info("to: " + admin.getEmail());

		Mail mail = mailProvider.get();
		mail.setSubject("Una nueva oferta ha sido creda por " + offer.getOrganization().getName());
		mail.setFrom(ninjaProperties.get("smtp.user"));
		mail.setCharset("utf-8");
		mail.addTo(admin.getEmail());

		Map<String, Object> params = new HashMap<>();
		params.put("offer", offer);

		mail.setBodyHtml(setBodyHtmlFromTemplate("newOfferNotification.ftl.html", params));

		try {
			postoffice.send(mail);
		} catch (EmailException | AddressException e) {
			logger.error(e.getLocalizedMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void notifyFinalCandidate(Application application) {

		Mail mail = mailProvider.get();
		mail.setSubject("Has sido seleccionado para un trabajo práctico");
		mail.setFrom(ninjaProperties.get("smtp.user"));
		mail.setCharset("utf-8");
		mail.addTo(application.getStudent().getEmail());

		Map<String, Object> params = new HashMap<>();
		params.put("application", application);

		mail.setBodyHtml(setBodyHtmlFromTemplate("finalCandidate.ftl.html", params));

		try {
			postoffice.send(mail);
		} catch (EmailException | AddressException e) {
			logger.error(e.getLocalizedMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*public sendMailForNewStudents(List<Student> studentList){
		Mail mail = mailProvider.get();

		// fill the mail with content:
		mail.setSubject("Su cuenta en la Plataforma del Centro de Prácticas ha sido creada.");
		mail.setFrom("donotreply@utalca.cl");
		mail.setCharset("utf-8");
		mail.addTo(studentList.getEmail());
	}*/

	public String setBodyHtmlFromTemplate(String name, Map<String, Object> args){

		Writer writer = new StringWriter();
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
		configuration.setClassForTemplateLoading(this.getClass(), "/views/MailController/");
		try {
			Template template = configuration.getTemplate(name);
			template.process(args, writer);
		} catch (Exception e) {
			logger.error("Failed to create mail from template: " + name, e);
		}

		return writer.toString();
	}
}