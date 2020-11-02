/**
 * 
 */
package com.pes.web.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.pes.web.controller.AjaxController;
import com.pes.web.dao.AbstractDAO;
import com.pes.web.dao.SubtaskDAO;
import com.pes.web.model.Subtask;
import com.pes.web.model.exception.PesWebException;

//import annotations.com.pes.web.model.Subtask_;

/**
 *
 */
@Repository
public class SubtaskDAOImpl extends AbstractDAO implements SubtaskDAO {

	final static Logger log = Logger.getLogger(SubtaskDAOImpl.class);
	
	private static final String QUERY_SELECT = "SELECT c FROM Subtask c WHERE c.subtaskId = :subtaskId";
	private static final String PARAMETER_NAME_SUBTASK_ID = "subtaskId";

	/**
	 * 
	 * @see com.pes.web.dao.SubtaskDAO{s}save(com.pes.web.model.Subtask)
	 */
	@Override
	public void save(Subtask subtask) throws PesWebException {
		super.save(subtask);
	}

	/**
	 * 
	 * @see com.pes.web.dao.SubtaskDAO{s}update(com.pes.web.model.Subtask)
	 */
	@Override
	public void update(Subtask subtask) throws PesWebException {
		super.update(subtask);
	}

	/**
	 * 
	 * @see com.pes.web.dao.SubtaskDAO{s}delete(com.pes.web.model.Subtask)
	 */
	@Override
	public void delete(Subtask subtask) throws PesWebException {
		super.delete(subtask);
	}

	/**
	 * 
	 * @see com.pes.web.dao.SubtaskDAO{s}getSubtaskById(int)
	 */
	@Override
	public Subtask getSubtaskById(int subtaskId) throws PesWebException {
		TypedQuery<Subtask> query = entityManager.createQuery(QUERY_SELECT, Subtask.class);
		query.setParameter(PARAMETER_NAME_SUBTASK_ID, subtaskId);
		Subtask subtask = query.getSingleResult();
		return subtask;
	}

	/**
	 * 
	 * @see com.pes.web.dao.SubtaskDAO{s}listSubtask()
	 */
	@Override
	public List<Subtask> listSubtask() throws PesWebException {
		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Subtask> criteria = builder.createQuery(Subtask.class);
			Root<Subtask> root = criteria.from(Subtask.class);
			criteria.select(root);
			return entityManager.createQuery(criteria).getResultList();
		} catch (Exception e) {
			log.error("Error", e);
			throw new PesWebException(e);
		}		
	}
}

