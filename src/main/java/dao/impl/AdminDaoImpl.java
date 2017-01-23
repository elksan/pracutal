package dao.impl;

import com.google.inject.Inject;
import com.google.inject.Provider;
import dao.AdminDao;
import models.Career;
import models.Organization;
import models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vo.CareerVO;
import vo.VerificationToken;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Diego on 19-06-2016.
 */
public class AdminDaoImpl implements AdminDao {
	Logger logger = LoggerFactory.getLogger(AdminDaoImpl.class);

	@Inject
	Provider<EntityManager> entityManagerProvider;

	@Override
	public VerificationToken getVerificationToken(String token) {
		EntityManager entityManager = entityManagerProvider.get();

		Query query = entityManager.createQuery("Select x FROM VerificationToken x where token = :token");
		query.setParameter("token", token);

		return (VerificationToken) query.getSingleResult();


	}

	@Override
	public void updateOrganization(User user) {

		EntityManager entityManager = entityManagerProvider.get();
		entityManager.merge(user);

	}

    @Override
    public List<Career> getCareers() {
		EntityManager entityManager = entityManagerProvider.get();
		Query query = entityManager.createQuery("Select x from Career x");
		return  query.getResultList();

    }

    @Override
    public void saveCareer(Career career) {
		EntityManager entityManager = entityManagerProvider.get();
		entityManager.persist(career);
    }

	@Override
	public Career findCareerById(Integer careerId) {
		EntityManager entityManager = entityManagerProvider.get();
		return  entityManager.find(Career.class, careerId);
	}

	@Override
	public void updateCareer(Career career) {
		EntityManager entityManager = entityManagerProvider.get();
		entityManager.merge(career);
	}
}
