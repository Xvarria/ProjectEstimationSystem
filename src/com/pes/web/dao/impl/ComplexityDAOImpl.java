/**
 * 
 */
package com.pes.web.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.pes.web.dao.AbstractDAO;
import com.pes.web.dao.ComplexityDAO;
import com.pes.web.model.Complexity;
import com.pes.web.model.exception.PesWebException;

//import annotations.com.pes.web.model.Complexity_;

/**
 *
 */
@Repository
public class ComplexityDAOImpl extends AbstractDAO implements ComplexityDAO {
	
	private static final String QUERY_SELECT = "SELECT c FROM Complexity c WHERE c.complexityId = :complexityId";
	private static final String PARAMETER_NAME_COMPLEXITY_ID = "complexityId";

	/**
	 * 
	 * @see com.pes.web.dao.ComplexityDAO{s}save(com.pes.web.model.Complexity)
	 */
	@Override
	public void save(Complexity complexity) throws PesWebException {
		super.save(complexity);
	}

	/**
	 * 
	 * @see com.pes.web.dao.ComplexityDAO{s}update(com.pes.web.model.Complexity)
	 */
	@Override
	public void update(Complexity complexity) throws PesWebException {
		super.update(complexity);
	}

	/**
	 * 
	 * @see com.pes.web.dao.ComplexityDAO{s}delete(com.pes.web.model.Complexity)
	 */
	@Override
	public void delete(Complexity complexity) throws PesWebException {
		super.delete(complexity);
	}

	/**
	 * 
	 * @see com.pes.web.dao.ComplexityDAO{s}getComplexityById(int)
	 */
	@Override
	public Complexity getComplexityById(int complexityId) throws PesWebException {
		TypedQuery<Complexity> query = entityManager.createQuery(QUERY_SELECT, Complexity.class);
		query.setParameter(PARAMETER_NAME_COMPLEXITY_ID, complexityId);
		Complexity complexity = query.getSingleResult();
		return complexity;
	}

	/**
	 * 
	 * @see com.pes.web.dao.ComplexityDAO{s}listComplexity()
	 */
	@Override
	public List<Complexity> listComplexity() throws PesWebException {
		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Complexity> criteria = builder.createQuery(Complexity.class);
			Root<Complexity> root = criteria.from(Complexity.class);
			criteria.select(root);
			return entityManager.createQuery(criteria).getResultList();
		} catch (Exception e) {
			throw new PesWebException(e);
		}		
	}
}
