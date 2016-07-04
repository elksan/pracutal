package controllers;

import com.google.inject.Inject;
import com.google.inject.Provider;
import etc.LoggedInUser;
import freemarker.template.Configuration;
import freemarker.template.Template;
import models.Organization;
import ninja.Context;
import ninja.postoffice.Mail;
import ninja.postoffice.Postoffice;
import org.apache.commons.mail.EmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.UserService;
import vo.VerificationToken;

import javax.mail.internet.AddressException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Diego on 03-07-2016.
 */
public class MailController {

	@Inject
	Provider<Mail> mailProvider;

	@Inject
	Postoffice postoffice;

	@Inject
	UserService userService;

	Logger logger = LoggerFactory.getLogger(MailController.class);

	public void sendMail(Context context, Organization organization) {

		Mail mail = mailProvider.get();

		// fill the mail with content:
		mail.setSubject("Tu cuenta ha sido creada");
		mail.setFrom("donotreply@utalca.cl");
		mail.setCharset("utf-8");
		mail.addTo(organization.getEmail());



		VerificationToken lastToken = organization.getTokens().get(0);
		if(lastToken.isVerified())
			logger.error("Token already verified" + lastToken.getToken());

		Map<String, Object> params = new HashMap<>();
		params.put("organization", organization);

		String url = context.getHostname()+context.getContextPath()+"/registerUser/"+ lastToken.getToken();
		params.put("url", url);

		mail.setBodyHtml(setBodyHtmlFromTemplate("newOrganization.ftl.html", params));

		// finally send the mail
		try {
			postoffice.send(mail);
		} catch (EmailException | AddressException e) {
			// ...
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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