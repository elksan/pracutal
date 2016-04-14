package dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import models.Career;
import models.OfferType;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;

import dao.OfferDao;
import models.Offer;
import ninja.jpa.UnitOfWork;

public class OfferDaoImpl implements OfferDao{

	Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
    
    @Inject
    Provider<EntityManager> entityManagerProvider;
    
    @UnitOfWork
	public List<Offer> getAllOffers() {
		
		
		List<Offer> offers = new ArrayList<Offer>();
		
		EntityManager entityManager = entityManagerProvider.get();
        
        Query q = entityManager.createQuery("SELECT x FROM Offer x where disabled = false");

        try{
        	
        	offers = q.getResultList();  
        }
        catch(NoResultException nrex){
        	
        	
        }

		Hibernate.initialize(offers.get(0).getOfferType().getOffers());
		return offers;
	}
	
    @UnitOfWork
	public Offer findOfferById(int offerId){
		
		EntityManager entityManager = entityManagerProvider.get();
		return entityManager.find(Offer.class, offerId);
	}

    @Transactional
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

	@UnitOfWork
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

	@UnitOfWork
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
		return offerTypes;

	}

	@UnitOfWork
	public OfferType getOfferType(int offerId) {
		EntityManager entityManager = entityManagerProvider.get();
		OfferType offerType = entityManager.find(OfferType.class, offerId);

		return offerType;
	}

}
