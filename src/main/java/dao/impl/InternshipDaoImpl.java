package dao.impl;

import com.google.inject.Inject;
import com.google.inject.Provider;
import models.Internship;
import services.impl.InternshipDao;

import javax.persistence.EntityManager;

/**
 * Created by Diego on 02-06-2016.
 */
public class InternshipDaoImpl implements InternshipDao{

	@Inject
	Provider<EntityManager> entityManagerProvider;

	public void saveInternship(Internship internship) {

		EntityManager entityManager = entityManagerProvider.get();

		entityManager.persist(internship);
	}
}
