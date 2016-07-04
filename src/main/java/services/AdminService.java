package services;

import models.Organization;
import vo.ActivationFormVO;
import vo.OrganizationVO;

import java.util.List;

/**
 * Created by Diego on 19-06-2016.
 */
public interface AdminService {

	Organization saveOrganization(OrganizationVO organizationVO);

	boolean verifyToken(String token) throws Exception;

	void activateUser(ActivationFormVO activationFormVO) throws Exception;

	List<OrganizationVO> getOrganizations();
}
