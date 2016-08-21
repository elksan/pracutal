package services;

import models.Application;
import models.Organization;
import models.Student;
import models.User;
import vo.OrganizationVO;

import java.util.List;

public interface UserService {

	boolean isUserAndPasswordValid(String username, String password);
	User getUserById(String username);
	Organization getOrganizationById(int organizationId);
	List<Application> getApplicationsByUserId(int userId);

	void addUsersFromExcel();

	void saveStudentList(List<Student> studentList);

	Organization saveOrganization(OrganizationVO organizationVO);
	List<OrganizationVO> getOrganizations();

	List<Application> getStudentsWithInternshipAssigned();
}
