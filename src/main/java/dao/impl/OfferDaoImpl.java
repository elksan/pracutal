package dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.*;

import com.google.inject.multibindings.StringMapKey;
import models.*;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;

import dao.OfferDao;

public class OfferDaoImpl implements OfferDao{

	Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
    
    @Inject
    Provider<EntityManager> entityManagerProvider;
    

	public List<Offer> getAllOffers() {
		
		
		List<Offer> offers = new ArrayList<Offer>();
		
		EntityManager entityManager = entityManagerProvider.get();
        
        Query q = entityManager.createQuery("SELECT x FROM Offer x ");

        try{
        	offers = q.getResultList();
        }
        catch(NoResultException nrex) {
			logger.warn("No result in getAllOffers() ");
			logger.warn(nrex.getMessage());
		}

		return offers;
	}

	public List<Offer> getOffers(int organizationId) {

		List<Offer> offers = new ArrayList<>();

		EntityManager entityManager = entityManagerProvider.get();
		EntityGraph graph = entityManager.getEntityGraph("offer.organization+offertype");

		Query q = entityManager.createQuery("SELECT X FROM Offer X where X.organization.id = :organizationId");
		q.setParameter("organizationId", organizationId);
		q.setHint("javax.persistence.loadgraph", graph);

		try{
			offers = q.getResultList();

		}
		catch(NoResultException nrex){
			logger.warn("No result in getOffers(int organizationId) ");
			logger.warn(nrex.getMessage());
		}

		return offers;
	}

	public Offer findOfferById(int offerId){
		
		EntityManager entityManager = entityManagerProvider.get();

		Offer offer = entityManager.find(Offer.class, offerId);
        logger.debug("OFFER: " + offer.toString());
		Hibernate.initialize(offer.getOfferType());
        Hibernate.initialize(offer.getCareers());

		return offer;
	}

	public Offer findOfferByIdWithApplications(int offerId){

		EntityManager entityManager = entityManagerProvider.get();
		EntityGraph graph = entityManager.getEntityGraph("offer.applications");
		Map<String, Object> hints = new HashMap<>();
		hints.put("javax.persistence.loadgraph",graph);

		Offer offer = entityManager.find(Offer.class, offerId, hints);

		return offer;
	}


	public void saveOffer(Offer offer) {
		try {
			EntityManager entityManager = entityManagerProvider.get();
			entityManager.persist(offer);
		}
        catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		
	}


	public List<Career> getCareers() {
		EntityManager entityManager = entityManagerProvider.get();

		List<Career> careers = new ArrayList<Career>();

		Query q = entityManager.createQuery("SELECT x FROM Career x");

		try{

			careers = q.getResultList();
		}
		catch(NoResultException nrex){

			logger.warn ("No careers were found");
		}
		return careers;

	}


	public List<OfferType> getOfferTypes() {

		EntityManager entityManager = entityManagerProvider.get();
		EntityGraph graph = entityManager.getEntityGraph("offerType.offers");

		List<OfferType> offerTypes = new ArrayList<OfferType>();
		Query q = entityManager.createQuery("SELECT distinct x FROM OfferType x");
		q.setHint("javax.persistence.loadgraph", graph);
		try{

			offerTypes = q.getResultList();
		}
		catch(NoResultException nrex){

			logger.warn("No offer types were found");
		}
		/*for(OfferType offerType : offerTypes){

			Hibernate.initialize(offerType.getOffers());
		}*/
		return offerTypes;

	}


	public OfferType getOfferType(int offerId) {
		EntityManager entityManager = entityManagerProvider.get();
		OfferType offerType = entityManager.find(OfferType.class, offerId);

		return offerType;
	}


	public void deleteOffer(int offerId) {

		EntityManager entityManager = entityManagerProvider.get();
		Offer offer = entityManager.find(Offer.class, offerId);
		entityManager.remove(offer);
	}


	public void updateOffer(Offer offer) {
		try {
			EntityManager entityManager = entityManagerProvider.get();
			entityManager.merge(offer);
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

	public List<Offer> getApprovedOffers() {

		List<Offer> approvedOffers = new ArrayList<Offer>();

		EntityManager entityManager = entityManagerProvider.get();
		EntityGraph graph = entityManager.getEntityGraph("offer.organization+offertype");

		Query q = entityManager.createQuery("SELECT X FROM Offer X where disabled = false and approved = true order by closed");
		q.setHint("javax.persistence.loadgraph", graph);
		try{
			approvedOffers = q.getResultList();

		}
		catch(NoResultException nrex){
			logger.warn("No result in getApprovedOffers");
			logger.warn(nrex.getMessage());
		}

		return approvedOffers;
	}

	public List<Offer> getAvailableOffers() {

		List<Offer> offers = new ArrayList<Offer>();

		EntityManager entityManager = entityManagerProvider.get();
		EntityGraph graph = entityManager.getEntityGraph("offer.organization+offertype");

		Query q = entityManager.createQuery("SELECT X FROM Offer X where disabled = false and approved = true and available = true");
		q.setHint("javax.persistence.loadgraph", graph);

		try{
			offers = q.getResultList();

		}
		catch(NoResultException nrex){
			logger.warn("No result in getApprovedOffers");
			logger.warn(nrex.getMessage());
		}

		return offers;
	}

	@Override
	public void saveApplication(Application application) {

		EntityManager entityManager = entityManagerProvider.get();
		entityManager.persist(application);
	}

	public List<Application> getApplicationsByOfferId(int offerId) {
		List<Application> applicationList = new ArrayList<>();

		EntityManager entityManager = entityManagerProvider.get();
		Query q = entityManager.createQuery("SELECT x FROM Application x WHERE offer.id = :offerId");
		q.setParameter("offerId", offerId);

		applicationList = q.getResultList();

		return applicationList;
	}

	public List<Student> getCandidatesByOfferId(int offerId) {
		List<Student> studentList = new ArrayList<>();

		EntityManager entityManager = entityManagerProvider.get();
		Query q = entityManager.createQuery("SELECT s FROM Application a INNER JOIN a.student s WHERE a.offer.id = :offerId");
		q.setParameter("offerId", offerId);

		studentList = q.getResultList();

		return studentList;
	}

	@Override
	public Offer findOfferByIdWithOrganization(int offerId) {
		EntityManager entityManager = entityManagerProvider.get();
		EntityGraph graph = entityManager.getEntityGraph("offer.organization");

		Map<String, Object> hints = new HashMap<>();
		hints.put("javax.persistence.loadgraph", graph);

		Offer offer = entityManager.find(Offer.class, offerId, hints);
		logger.debug("OFFER: " + offer.toString());
		return offer;
	}

	@Override
	public Application findApplicationById(int applicationId) {

		EntityManager em = entityManagerProvider.get();
		return em.find(Application.class, applicationId);

	}

	public Application findApplicationByIdWithOfferAndUser(int applicationId) {

		EntityManager em = entityManagerProvider.get();
		EntityGraph graph = em.getEntityGraph("applicationWithOfferAndStudent");

		Map<String, Object> hints = new HashMap<>();
		hints.put("javax.persistence.loadgraph", graph);

		return em.find(Application.class, applicationId, hints);

	}

	@Override
	public void updateApplication(Application application) {

		EntityManager em = entityManagerProvider.get();
		em.merge(application);
	}

	public List<Career> getCareers(List<Integer> careerIds) {
		EntityManager entityManager = entityManagerProvider.get();

		List<Career> careers = new ArrayList<Career>();

		Query q = entityManager.createQuery("SELECT x FROM Career x WHERE careerId in (:careerIds)");
		q.setParameter("careerIds", careerIds);

		try{

			careers = q.getResultList();
		}
		catch(NoResultException nrex){

			logger.warn("No careers were found");
		}
		return careers;

	}

}
