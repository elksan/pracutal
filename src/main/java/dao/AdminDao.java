package dao;

import models.Organization;
import models.User;
import vo.VerificationToken;

import java.util.List;

/**
 * Created by Diego on 19-06-2016.
 */
public interface AdminDao {



	VerificationToken getVerificationToken(String token);

	void updateOrganization(User user);

}
