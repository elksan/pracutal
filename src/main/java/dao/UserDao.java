package dao;

import models.*;

import java.util.List;

public interface UserDao {

	boolean isUserAndPasswordValid(String username, String password);
	User getUserById(String userId);
	User getUserById(int userId);
	User getAdminUser();
	Organization getOrganizationById(int organizationId);
	List<Application> getApplicationsByUserId(int userId);
	Role findRoleById(int roleId);
	void saveStudentList(List<Student> studentList);
	void saveUser(User user);
	Career findCareerById(Integer careerId);
	Organization saveOrganization(Organization organization);
	List<Organization> getOrganizations();
	List<Application> getStudentsWithInternshipAssigned();
	List<Student> getAllStudents();
	Organization updateOrganization(Organization organization);
	/*Organization findOrganizationWithAddress(Integer userId);*/
	Student findStudentWithAddress(Integer userId);
	User findUserById(int userId);
	void updateUser(User user);

	User findUserWithAddress(Integer userId);

	Student updateStudent(Student student);
}
