package services;

import models.Organization;
import models.User;

public interface UserService {

	boolean isUserAndPasswordValid(String username, String password);
	User getUserById(String username);
	Organization getOrganizationById(int organizationId);
}
