package dao;

import models.User;

public interface UserDao {

	public boolean isUserAndPasswordValid(String username, String password);
	public User getUserById(String userId);
}
