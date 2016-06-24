package dao;

import models.Application;
import models.Organization;
import models.Role;
import models.User;

import java.util.List;

public interface UserDao {

	boolean isUserAndPasswordValid(String username, String password);
	User getUserById(String userId);
	public User getUserById(int userId);
	Organization getOrganizationById(int organizationId);
	List<Application> getApplicationsByUserId(int userId);
	Role findRoleById(int roleId);
}
