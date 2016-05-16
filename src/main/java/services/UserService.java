package services;

import models.Application;
import models.Organization;
import models.User;

import java.util.List;

public interface UserService {

	boolean isUserAndPasswordValid(String username, String password);
	User getUserById(String username);
	Organization getOrganizationById(int organizationId);
	List<Application> getApplicationsByUserId(int userId);
}
