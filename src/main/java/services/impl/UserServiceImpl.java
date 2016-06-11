package services.impl;

import com.google.inject.Inject;

import controllers.OfferController;
import dao.UserDao;
import models.Application;
import models.Organization;
import models.User;
import ninja.jpa.UnitOfWork;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

	@Inject
	UserDao userDao;

	Logger logger = LoggerFactory.getLogger(OfferController.class);

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

}
