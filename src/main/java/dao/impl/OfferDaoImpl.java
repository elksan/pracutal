package dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

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

		Query q = entityManager.createQuery("SELECT X FROM Offer X where X.organization.id = :organizationId");
		q.setParameter("organizationId", organizationId);

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

			logger.warn("No careers were found");
		}
		return careers;

	}


	public List<OfferType> getOfferTypes() {

		EntityManager entityManager = entityManagerProvider.get();

		List<OfferType> offerTypes = new ArrayList<OfferType>();

		Query q = entityManager.createQuery("SELECT x FROM OfferType x");

		try{

			offerTypes = q.getResultList();
		}
		catch(NoResultException nrex){

			logger.warn("No offer types were found");
		}
		for(OfferType offerType : offerTypes){

			Hibernate.initialize(offerType.getOffers());
		}
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

		Query q = entityManager.createQuery("SELECT X FROM Offer X where disabled = false and approved = true");

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

		Query q = entityManager.createQuery("SELECT X FROM Offer X where disabled = false and approved = true and available = true");

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
