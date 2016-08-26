package services.impl;

import com.google.inject.Inject;

import com.google.inject.persist.Transactional;
import controllers.OfferController;
import dao.UserDao;
import etc.SessionIdentifierGenerator;
import models.*;
import ninja.jpa.UnitOfWork;
import ninja.utils.NinjaProperties;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.UserService;
import vo.OrganizationVO;
import vo.VerificationToken;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

	@Inject
	UserDao userDao;

	@Inject
	NinjaProperties ninjaProperties;

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@UnitOfWork
	public boolean isUserAndPasswordValid(String username, String password) {

		return userDao.isUserAndPasswordValid(username, password);
		
	}

	@UnitOfWork
	public User getUserById(String username) {
		
		return userDao.getUserById(username);
	}

	@UnitOfWork
	public Organization getOrganizationById(int organizationId) {
		return userDao.getOrganizationById(organizationId);
	}

	@UnitOfWork
	public List<Application> getApplicationsByUserId(int userId) {

		List<Application> applicationList = userDao.getApplicationsByUserId(userId);

		for(Application app : applicationList){

			Hibernate.initialize(app.getOffer().getOrganization());
		}
		return applicationList;
	}

	@Override
	public void addUsersFromExcel() {

	}

	@Transactional
	public void saveStudentList(List<Student> studentList) {

		for(Student student : studentList){

			if(userAlreadyExists(student.getEmail())) {
				logger.warn("User " + student.getEmail() + " already exists in the DB");

				continue;
			}
			createNewTokenForUser(student);
			Integer careerId = Integer.parseInt(student.getRegistrationNumber().toString().substring(4,8));
			student.setCareer(userDao.findCareerById(careerId));
			student.setRoles(new ArrayList<Role>());
			student.getRoles().add(userDao.findRoleById(3));
			userDao.saveUser(student);
		}

	}

	/**
	 *
	 * @param userEmail The user email
	 * @return true if the user exists in DB, otherwise return false
	 */
	private boolean userAlreadyExists(String userEmail){

		User user = userDao.getUserById(userEmail);

		return user != null;

	}

	@Transactional
	public Organization saveOrganization(OrganizationVO organizationVO) {

		Organization organization = new Organization(organizationVO);
		createNewTokenForUser(organization);
		Role role = userDao.findRoleById(2);
		organization.getRoles().add(role);
		return userDao.saveOrganization(organization);


	}
	@UnitOfWork
	public List<OrganizationVO> getOrganizations() {
		List<OrganizationVO> organizationVOs = new ArrayList<>();

		List<Organization> organizations = userDao.getOrganizations();

		for(Organization organization : organizations){
			OrganizationVO organizationVO = new OrganizationVO(organization);
			organizationVOs.add(organizationVO);
		}

		return organizationVOs;
	}

	@UnitOfWork
	public List<Application> getStudentsWithInternshipAssigned() {

		return userDao.getStudentsWithInternshipAssigned();
	}

	@UnitOfWork
	public List<Student> getAllStudents() {
		return userDao.getAllStudents();
	}

	@Transactional
	public Organization updateOrganization(OrganizationVO organizationVO) {
		Organization organization = userDao.getOrganizationById(organizationVO.getId());

		organization.setName(organizationVO.getName());
		organization.setArea(organizationVO.getArea());
		organization.setDescription(organizationVO.getDescription());
		organization.setEmail(organizationVO.getEmail());
		organization.setWebpage(organizationVO.getWebpage());
		organization.setPhoneNumber(organizationVO.getPhoneNumber());

		return userDao.updateOrganization(organization);
	}

	private void createNewTokenForUser(User user){

		SessionIdentifierGenerator sig = new SessionIdentifierGenerator();
		String generatedId = sig.nextSessionId();
		VerificationToken token = new VerificationToken(user, VerificationToken.VeriticationTokenType.EMAIL_REGISTRATION, generatedId,
				Integer.parseInt(ninjaProperties.get("token.expiry.date")));

		List<VerificationToken> tokens = new ArrayList<>();
		tokens.add(token);
		user.setTokens(tokens);
	}

}
