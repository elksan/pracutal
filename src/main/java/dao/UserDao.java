package dao;

import models.*;

import java.util.List;

public interface UserDao {

	boolean isUserAndPasswordValid(String username, String password);
	User getUserById(String userId);
	public User getUserById(int userId);
	Organization getOrganizationById(int organizationId);
	List<Application> getApplicationsByUserId(int userId);
	Role findRoleById(int roleId);

	void saveStudentList(List<Student> studentList);

	void saveUser(User user);

	Career findCareerById(Integer careerId);
	Organization saveOrganization(Organization organization);

	List<Organization> getOrganizations();

	List<Application> getStudentsWithInternshipAssigned();
}
