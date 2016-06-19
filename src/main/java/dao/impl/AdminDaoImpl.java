package dao.impl;

import com.google.inject.Inject;
import com.google.inject.Provider;
import dao.AdminDao;
import models.Organization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;

/**
 * Created by Diego on 19-06-2016.
 */
public class AdminDaoImpl implements AdminDao {
	Logger logger = LoggerFactory.getLogger(AdminDaoImpl.class);

	@Inject
	Provider<EntityManager> entityManagerProvider;

	public void saveOrganization(Organization organization) {
		EntityManager entityManager = entityManagerProvider.get();

		entityManager.persist(organization);
	}
}
