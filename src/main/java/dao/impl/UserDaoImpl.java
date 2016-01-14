/**
 * Copyright (C) 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import models.User;
import ninja.jpa.UnitOfWork;

import com.google.inject.Inject;
import com.google.inject.Provider;

import dao.UserDao;

public class UserDaoImpl implements UserDao {
	
	Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
    
    @Inject
    Provider<EntityManager> entityManagerProvider;
    
    @UnitOfWork
    public boolean isUserAndPasswordValid(String username, String password) {
        
        if (username != null && password != null) {
            
        	
            EntityManager entityManager = entityManagerProvider.get();
            
            Query q = entityManager.createQuery("SELECT x FROM User x WHERE email = :usernameParam");
            
            User user= null;
            try{
            	
            	user = (User) q.setParameter("usernameParam", username).getSingleResult();  
            }
            catch(NoResultException nrex){
            	
            	
            }
             

            
            if (user != null) {
                
                if (user.getPassword().equals(password)) {

                    return true;
                }
                
            }

        }
        
        return false;
 
    }
    
    @UnitOfWork
    public User getUserById(String userId){
		
    	if (userId == null){
    		
    		return null;
    	}
    	
    	EntityManager entityManager = entityManagerProvider.get();
    	Query query = entityManager.createQuery("Select x from User x where email = :userId");
    	
    	User user = null;
    	
    	try{
    		
    		user = (User) query.setParameter("userId", userId).getSingleResult();
    		
    		logger.debug("user logged = " + user.getEmail());
    		logger.debug("careerName = " + user.getStudent().getCareer().getCareerName());
    		
    	}catch(NoResultException nre){
    		
    		
    	}
    	
    	return user;
    	
    }

}
