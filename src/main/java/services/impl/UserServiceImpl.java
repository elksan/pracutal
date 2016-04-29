package services.impl;

import com.google.inject.Inject;

import dao.UserDao;
import models.Organization;
import models.User;
import ninja.jpa.UnitOfWork;
import services.UserService;

public class UserServiceImpl implements UserService {

	@Inject
	UserDao userDao;

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

}
