package services.impl;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import dao.UserDao;
import models.Internship;
import models.User;
import services.InternshipService;

/**
 * Created by Diego on 02-06-2016.
 */
public class InternshipServiceImpl implements InternshipService{

	@Inject
	InternshipDao internshipDao;

	@Inject
	UserDao userDao;

	@Transactional
	public void assignInternship(int studentId) {

		User user = userDao.getUserById(studentId);

		Internship internship = new Internship();
		internship.setStudent(user.getStudent());

		internshipDao.saveInternship(internship);
	}
}
