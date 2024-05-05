package com.xyz.repos.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
/**
 * 
 */
public class BaseDAOImpl  implements BaseDAO {
	
	private Logger logger = LoggerFactory.getLogger(BaseDAOImpl.class);
	@PersistenceContext
    private EntityManager entityManager;
	 @Override
	    public <T> T update(final T entity) { 
	    	
	        return entityManager.merge(entity);
	    }

	    @Override
	    public Boolean deleteEntity(Object entity) {
	    	 
	        entityManager.remove(entity);
	    	return true;
	    }

	    @Override
	   public  <T> T saveEntity(final T entity){
	        entityManager.persist(entity);
	        return entity;
	    }
	    
	    public EntityManager getEntityManager() {
	        return entityManager;
	    }

	    public void setEntityManager(EntityManager entityManager) {
	        this.entityManager = entityManager;
	    }
}
