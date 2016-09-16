package services.impl;

import com.google.inject.Inject;

import com.google.inject.persist.Transactional;
import dao.UserDao;
import etc.SessionIdentifierGenerator;
import etc.UserRole;
import exceptions.UserAlreadyExistsException;
import models.*;
import ninja.jpa.UnitOfWork;
import ninja.utils.NinjaProperties;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.UserService;
import vo.OrganizationVO;
import vo.StudentVO;
import vo.VerificationToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
		if(!organizationVO.getStreetName().isEmpty()){
			Address address = new Address(organizationVO);
			organization.setAddress(address);
		}

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
		Organization organization = (Organization) userDao.findUserWithAddress(organizationVO.getId());

		organization.setName(organizationVO.getName());
		organization.setArea(organizationVO.getArea());
		organization.setDescription(organizationVO.getDescription());
		organization.setEmail(organizationVO.getEmail());
		organization.setWebpage(organizationVO.getWebpage());
		organization.setPhoneNumber(organizationVO.getPhoneNumber());
		if(organization.getAddress() != null){
			Address address = organization.getAddress();

			address.setStreetName(organizationVO.getStreetName());
			address.setStreetNumber(organizationVO.getStreetNumber());
			address.setApartmentNumber(organizationVO.getApartmentNumber());
			address.setCommune(organizationVO.getCommune());
			address.setCity(organizationVO.getCity());

			address.setUser(organization);
			organization.setAddress(address);
		}
		else if (!organizationVO.getStreetName().isEmpty()){
			Address address = new Address(organizationVO);


			organization.setAddress(address);
			address.setUser(organization);
		}

		userDao.updateUser(organization);
		return organization;
	}

	@Transactional
	public Student saveStudent(StudentVO studentVO) throws UserAlreadyExistsException {

		Student student = new Student(studentVO);

		if(userAlreadyExists(student.getEmail())) {
			logger.warn("User " + student.getEmail() + " already exists in the DB");

			throw new UserAlreadyExistsException();
		}
		createNewTokenForUser(student);
		student.setCareer(userDao.findCareerById(Integer.parseInt(studentVO.getRegistrationNumber().toString().substring(4,8))));
		student.setBirthdate(parseStringToDate(studentVO.getBirthDate()));
		student.setRoles(new ArrayList<Role>());
		student.getRoles().add(userDao.findRoleById(UserRole.ESTUDIANTE.getValue()));
		userDao.saveUser(student);
		return student;
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

	private String parseDateToString(Date fecha) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("d MMMM, yyyy", Locale.forLanguageTag("es"));
			return sdf.format(fecha);
		}
		catch (Exception e){
			logger.debug("ERROR AL PARSEAR LA FECHA!");
			return "";
		}
	}

	private Date parseStringToDate(String fecha) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("d MMMM, yyyy", Locale.forLanguageTag("es"));
			return sdf.parse(fecha);
		}
		catch (Exception e){
			logger.debug("ERROR AL PARSEAR LA FECHA!");
			return null;
		}
	}

	@UnitOfWork
	public User findUserWithAddress(Integer userId) {
		User user = userDao.findUserWithAddress(userId);
		if (user instanceof Student){
			Hibernate.initialize(((Student) user).getCareer());
		}
		return user;
	}

	@Transactional
	public Student updateStudent(StudentVO studentVO) {
		Student student = (Student) userDao.findUserWithAddress(studentVO.getId());

		student.setName(studentVO.getName());
		student.setLastName(studentVO.getLastName());
		student.setMotherLastName(studentVO.getMotherLastName());
		student.setEmail(studentVO.getEmail());
		student.setPhoneNumber(studentVO.getPhoneNumber());
		student.setBirthdate(parseStringToDate(studentVO.getBirthDate()));
		student.setJobObjective(studentVO.getJobObjective());
		student.setEntryYear(Integer.parseInt(studentVO.getRegistrationNumber().toString().substring(0,4)));
		student.setRut(studentVO.getRut());
		student.setGender(studentVO.getGender());
		student.setRegistrationNumber(studentVO.getRegistrationNumber());

		if(student.getAddress() != null){
			Address address = student.getAddress();
			address.setStreetName(studentVO.getStreetName());
			address.setStreetNumber(studentVO.getStreetNumber());
			address.setApartmentNumber(studentVO.getApartmentNumber());
			address.setCommune(studentVO.getCommune());
			address.setCity(studentVO.getCity());
			student.setAddress(address);
			address.setUser(student);
		}
		else if (!studentVO.getStreetName().isEmpty()){
			Address address = new Address(studentVO);
			student.setAddress(address);
			address.setUser(student);
		}

		userDao.updateUser(student);
		return student;
	}

	/*@UnitOfWork
	public Student findStudentWithAddress(Integer userId) {

		return userDao.findStudentWithAddress(userId);
	}*/

	@Transactional
	public void updateProfilePhoto(int userId, String filename) {
		User user = userDao.findUserById(userId);
		user.setProfilePhotoPath(filename);
		userDao.updateUser(user);
	}
}
