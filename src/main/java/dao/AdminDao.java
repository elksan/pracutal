package dao;

import models.Career;
import models.User;
import vo.CareerVO;
import vo.VerificationToken;

import java.util.List;

/**
 * Created by Diego on 19-06-2016.
 */
public interface AdminDao {



	VerificationToken getVerificationToken(String token);
	void updateOrganization(User user);
    List getCareers();
    void saveCareer(Career career);
    Career findCareerById(Integer careerId);
    void updateCareer(Career career);
}
