package com.pes.web.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.pes.web.model.exception.PesWebException;
 
 /**
 * @author mchavarria
 *
 */
@Repository
public abstract class AbstractDAO {
 
	@PersistenceContext(name="persistenceUnit")
	protected EntityManager entityManager;

 
    protected void save(Object entity) throws PesWebException {
    	try {
			this.entityManager.persist(entity);
		} catch (Exception e) {
			new PesWebException("Error on save method", e);
		}
    }
 
    protected void delete(Object entity) throws PesWebException {
    	try {
        	this.entityManager.remove(this.entityManager.contains(entity) ? entity : this.entityManager.merge(entity));
		} catch (Exception e) {
			new PesWebException("Error on delete method", e);
		}
    }
    
    protected void update(Object entity) throws PesWebException {
    	try {
    		this.entityManager.merge(entity);
		} catch (Exception e) {
			new PesWebException("Error on update method", e);
		}    	
    }
}
