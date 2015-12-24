package services;

import models.User;

public interface UserService {

	public boolean isUserAndPasswordValid(String username, String password);
	public User getUserById(String username);
}
