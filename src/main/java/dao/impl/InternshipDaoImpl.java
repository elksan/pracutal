package dao.impl;

import com.google.inject.Inject;
import com.google.inject.Provider;
import dao.InternshipDao;
import models.Internship;
import models.LogbookEntry;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Subgraph;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Diego on 02-06-2016.
 */
public class InternshipDaoImpl implements InternshipDao {

	@Inject
	Provider<EntityManager> entityManagerProvider;

	public void saveInternship(Internship internship) {

		EntityManager entityManager = entityManagerProvider.get();

		entityManager.persist(internship);
	}

	public List<Internship> getinternships(int userId) {

		List<Internship> internshipList;

		EntityManager entityManager = entityManagerProvider.get();
		EntityGraph graph = entityManager.getEntityGraph("graph.Internship.offer.organization");

		Query query = entityManager.createQuery(" SELECT x FROM Internship x WHERE student.id = :userId ");

		query.setParameter("userId", userId);
		query.setHint("javax.persistence.fetchgraph", graph);

		internshipList = query.getResultList();


		return internshipList;
	}


	public Internship findInternshipById(int internshipId) {

		EntityManager entityManager = entityManagerProvider.get();
		EntityGraph graph = entityManager.getEntityGraph("graph.Internship.entries");

		Map hints = new HashMap();
		hints.put("javax.persistence.fetchgraph", graph);

		Internship internship = entityManager.find(Internship.class, internshipId, hints);

		return internship;
	}

	public void updateinternship(Internship internship) {

		EntityManager entityManager = entityManagerProvider.get();
		entityManager.merge(internship);

	}

	public void createLogbookEntry(LogbookEntry logbookEntry) {

		EntityManager entityManager = entityManagerProvider.get();
		entityManager.merge(logbookEntry);

	}
}
