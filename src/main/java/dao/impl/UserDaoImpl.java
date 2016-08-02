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

import models.*;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ninja.jpa.UnitOfWork;

import com.google.inject.Inject;
import com.google.inject.Provider;

import dao.UserDao;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
	
	Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
    
    @Inject
    Provider<EntityManager> entityManagerProvider;
    

    public boolean isUserAndPasswordValid(String username, String password) {
        
        if (username != null && password != null) {
            
        	
            EntityManager entityManager = entityManagerProvider.get();
            
            Query q = entityManager.createQuery("SELECT x FROM User x WHERE email = :usernameParam");
            
            User user= null;
            try{
            	user = (User) q.setParameter("usernameParam", username.toLowerCase()).getSingleResult();
            }
            catch(NoResultException nrex){

            }
            if (user != null) {

				if(BCrypt.checkpw(password, user.getPassword())) {
                	
                	user.setSignInCount(user.getSignInCount()+1);
                	entityManager.merge(user);
                    return true;
                }
            }

        }
        
        return false;
 
    }
    

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
    		/*if(user.getStudent() != null && user.getStudent().getCareer() != null)
    			logger.debug("careerName = " + user.getStudent().getCareer().getCareerName());*/

			logger.debug("user role = " + user.getRoles().get(0));
    		
    	}catch(NoResultException nre){
    		
    		
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	
    	return user;
    	
    }

	public User getUserById(int userId){

		if (userId == 0){

			return null;
		}

		EntityManager entityManager = entityManagerProvider.get();
		Query query = entityManager.createQuery("Select x from User x where id = :userId");

		User user = null;

		try{

			user = (User) query.setParameter("userId", userId).getSingleResult();

		}catch(NoResultException nre){


		}catch(Exception ex){
			ex.printStackTrace();
		}

		return user;

	}


	public Organization getOrganizationById(int organizationId) {

		EntityManager entityManager = entityManagerProvider.get();
		Organization organization = null;
		try{
			organization = entityManager.find(Organization.class, organizationId);


		}catch(NoResultException nre){


		}catch(Exception ex){
			ex.printStackTrace();
		}

		return organization;

	}

	public Organization getOrganizationWithInternshipsById(int organizationId) {

		EntityManager entityManager = entityManagerProvider.get();
		Organization organization = null;
		try{

			organization = entityManager.find(Organization.class, organizationId);


		}catch(NoResultException nre){


		}catch(Exception ex){
			ex.printStackTrace();
		}

		return organization;

	}

	public List<Application> getApplicationsByUserId(int userId) {

		EntityManager entityManager = entityManagerProvider.get();
		Query query = entityManager.createQuery("SELECT x FROM Application x WHERE student.id = :userId");
		query.setParameter("userId", userId);

		List<Application> applicationList = new ArrayList<>();
		try{

			applicationList = query.getResultList();
		}catch(NoResultException nrex){
			logger.info("No result in getApplicationsByUserId");
			logger.info(nrex.getMessage());
		}

		return  applicationList;

	}

	public Role findRoleById(int roleId){

		EntityManager entityManager = entityManagerProvider.get();
		return entityManager.find(Role.class, roleId);
	}

	@Override
	public void saveStudentList(List<Student> studentList) {
		EntityManager entityManager = entityManagerProvider.get();

		entityManager.persist(studentList);
	}

	@Override
	public void saveUser(User user) {

		EntityManager em = entityManagerProvider.get();

		em.persist(user);
	}

	@Override
	public Career findCareerById(Integer careerId) {
		EntityManager em = entityManagerProvider.get();
		return em.find(Career.class, careerId);
	}

	public Organization saveOrganization(Organization organization) {
		EntityManager entityManager = entityManagerProvider.get();

		entityManager.persist(organization);
		return organization;
	}

	@Override
	public List<Organization> getOrganizations() {

		EntityManager entityManager = entityManagerProvider.get();
		Query query = entityManager.createQuery("SELECT x FROM  Organization x ");

		return query.getResultList();
	}
}
