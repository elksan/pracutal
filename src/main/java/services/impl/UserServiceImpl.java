package services.impl;

import com.google.inject.Inject;

import dao.UserDao;
import models.User;
import services.UserService;

public class UserServiceImpl implements UserService {

	@Inject
	UserDao userDao;
	
	public boolean isUserAndPasswordValid(String username, String password) {

		return userDao.isUserAndPasswordValid(username, password);
		
	}

	@Override
	public User getUserById(String username) {
		
		return userDao.getUserById(username);
	}

}
