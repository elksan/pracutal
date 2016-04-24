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
	
	public boolean isUserAndPasswordValid(String username, String password) {

		return userDao.isUserAndPasswordValid(username, password);
		
	}


	public User getUserById(String username) {
		
		return userDao.getUserById(username);
	}

	public Organization getOrganizationById(int organizationId) {
		return userDao.getOrganizationById(organizationId);
	}

}
