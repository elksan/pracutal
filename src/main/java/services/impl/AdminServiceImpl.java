package services.impl;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import dao.AdminDao;
import dao.UserDao;
import etc.SessionIdentifierGenerator;
import models.Organization;
import models.Role;
import models.User;
import ninja.jpa.UnitOfWork;
import ninja.utils.NinjaProperties;
import org.mindrot.jbcrypt.BCrypt;
import services.AdminService;
import vo.ActivationFormVO;
import vo.OrganizationVO;
import vo.VerificationToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diego on 19-06-2016.
 */
public class AdminServiceImpl implements AdminService {

	@Inject
	AdminDao adminDao;

	@Inject
	UserDao userDao;

	@Inject
	NinjaProperties ninjaProperties;

	@Transactional
	public Organization saveOrganization(OrganizationVO organizationVO) {

		Organization organization = new Organization(organizationVO);

		SessionIdentifierGenerator sig = new SessionIdentifierGenerator();
		String generatedId = sig.nextSessionId();
		VerificationToken token = new VerificationToken(organization, VerificationToken.VeriticationTokenType.EMAIL_REGISTRATION, generatedId,
				Integer.parseInt(ninjaProperties.get("token.expiry.date")));

		List<VerificationToken> tokens = new ArrayList<>();
		tokens.add(token);
		organization.setTokens(tokens);

		Role role = userDao.findRoleById(2);
		organization.getRoles().add(role);
		return adminDao.saveOrganization(organization);


	}

	@Transactional
	public boolean verifyToken(String token) throws Exception {


		VerificationToken verificationToken = adminDao.getVerificationToken(token);

		if(verificationToken.isVerified() || !verificationToken.getUser().getDisabled()){
			throw new Exception("ya se encuentra activado");
		}
		return true;
	}

	@Transactional
	public void activateUser(ActivationFormVO activationFormVO) throws Exception {

		VerificationToken verificationToken = adminDao.getVerificationToken(activationFormVO.getToken());
		if(verificationToken.getUser() == null)
			throw new Exception("token inválido");

		verificationToken.setVerified(true);
		verificationToken.getUser().setDisabled(false);

		String hashedPassword = BCrypt.hashpw(activationFormVO.getNewPassword(), BCrypt.gensalt());
		User user = verificationToken.getUser();
		user.setPassword(hashedPassword);
		adminDao.updateOrganization(user);

	}

	@UnitOfWork
	public List<OrganizationVO> getOrganizations() {
		List<OrganizationVO> organizationVOs = new ArrayList<>();

		List<Organization> organizations = adminDao.getOrganizations();

		for(Organization organization : organizations){
			OrganizationVO organizationVO = new OrganizationVO(organization);
			organizationVOs.add(organizationVO);
		}

		return organizationVOs;
	}

/*	public VerificationToken sendEmailRegistrationToken(String userId){

		User user = userDao.getUserById(userId);


	}*/


}
