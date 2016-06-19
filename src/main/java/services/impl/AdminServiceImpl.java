package services.impl;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import dao.AdminDao;
import models.Organization;
import services.AdminService;
import vo.OrganizationVO;

/**
 * Created by Diego on 19-06-2016.
 */
public class AdminServiceImpl implements AdminService {

	@Inject
	AdminDao adminDao;

	@Transactional
	public void saveOrganization(OrganizationVO organizationVO) {

		Organization organization = new Organization(organizationVO);
		adminDao.saveOrganization(organization);
	}
}
