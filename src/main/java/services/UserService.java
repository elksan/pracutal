package services;

import exceptions.UserAlreadyExistsException;
import models.Application;
import models.Organization;
import models.Student;
import models.User;
import vo.OrganizationVO;
import vo.StudentVO;

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
	List<Student> getAllStudents();
	Organization updateOrganization(OrganizationVO organizationVO);
	Student saveStudent(StudentVO studentVO) throws UserAlreadyExistsException;
	/*Organization findOrganizationWithAddress(Integer username);
	Student findStudentWithAddress(Integer username);*/
	void updateProfilePhoto(int userId, String filename);

	User findUserWithAddress(Integer userId);
}
