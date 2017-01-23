package services;

import models.Career;
import models.Organization;
import vo.ActivationFormVO;
import vo.CareerVO;
import vo.OrganizationVO;
import vo.UserVO;

import java.util.List;

/**
 * Created by Diego on 19-06-2016.
 */
public interface AdminService {


	boolean verifyToken(String token) throws Exception;
	void activateUser(ActivationFormVO activationFormVO) throws Exception;
	void generateAdminAccount(UserVO userVO);
    List<Career> getCareers();
    void saveCareer(CareerVO careerVO);
	void updateCareer(CareerVO careerVO);
	Career findCareerById(Integer careerId);
}
