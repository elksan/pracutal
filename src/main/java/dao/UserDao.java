package dao;

import models.Organization;
import models.User;

public interface UserDao {

	boolean isUserAndPasswordValid(String username, String password);
	User getUserById(String userId);

	Organization getOrganizationById(int organizationId);
}
