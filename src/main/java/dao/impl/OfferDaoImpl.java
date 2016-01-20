package dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;

import dao.OfferDao;
import models.Offer;
import ninja.jpa.UnitOfWork;

@UnitOfWork
public class OfferDaoImpl implements OfferDao{

	Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
    
    @Inject
    Provider<EntityManager> entityManagerProvider;
    
	public List<Offer> getAllOffers() {
		
		
		List<Offer> offers = new ArrayList<Offer>();
		
		EntityManager entityManager = entityManagerProvider.get();
        
        Query q = entityManager.createQuery("SELECT x FROM Offer x where disabled = false");
        
        Offer offer= null;
        try{
        	
        	offers = q.getResultList();  
        }
        catch(NoResultException nrex){
        	
        	
        }
		return offers;
	}

	
}
