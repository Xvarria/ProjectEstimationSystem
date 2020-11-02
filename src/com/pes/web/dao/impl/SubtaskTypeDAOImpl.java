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
import com.pes.web.dao.SubtaskTypeDAO;
import com.pes.web.model.SubtaskType;
import com.pes.web.model.exception.PesWebException;


/**
 *
 */
@Repository
public class SubtaskTypeDAOImpl extends AbstractDAO implements SubtaskTypeDAO {
	
	private static final String QUERY_SELECT = "SELECT c FROM SubtaskType c WHERE c.subtaskTypeId = :subtaskTypeId";
	private static final String QUERY_SELECT_BY_CATEGORY_ID = "SELECT c FROM SubtaskType c WHERE c.subtaskTypeCategory.subtaskTypeCategoryId = :subtaskTypeCategoryId";
	private static final String PARAMETER_NAME_SUBTASK_TYPE_ID = "subtaskTypeId";
	private static final String PARAMETER_NAME_SUBTASK_TYPE_CATEGORY_ID = "subtaskTypeCategoryId";

	/**
	 * 
	 * @see com.pes.web.dao.SubtaskTypeDAO{s}save(com.pes.web.model.SubtaskType)
	 */
	@Override
	public void save(SubtaskType subtaskType) throws PesWebException {
		super.save(subtaskType);
	}

	/**
	 * 
	 * @see com.pes.web.dao.SubtaskTypeDAO{s}update(com.pes.web.model.SubtaskType)
	 */
	@Override
	public void update(SubtaskType subtaskType) throws PesWebException {
		super.update(subtaskType);
	}

	/**
	 * 
	 * @see com.pes.web.dao.SubtaskTypeDAO{s}delete(com.pes.web.model.SubtaskType)
	 */
	@Override
	public void delete(SubtaskType subtaskType) throws PesWebException {
		super.delete(subtaskType);
	}

	/**
	 * 
	 * @see com.pes.web.dao.SubtaskTypeDAO{s}getSubtaskTypeById(int)
	 */
	@Override
	public SubtaskType getSubtaskTypeById(int subtaskTypeId) throws PesWebException {
		TypedQuery<SubtaskType> query = entityManager.createQuery(QUERY_SELECT, SubtaskType.class);
		query.setParameter(PARAMETER_NAME_SUBTASK_TYPE_ID, subtaskTypeId);
		SubtaskType subtaskType = query.getSingleResult();
		return subtaskType;
	}

	
	
	@Override
	public List<SubtaskType> listSubtaskTypeByCategoryId(int subtaskTypeCategoryId) throws PesWebException {
		TypedQuery<SubtaskType> query = entityManager.createQuery(QUERY_SELECT_BY_CATEGORY_ID, SubtaskType.class);
		query.setParameter(PARAMETER_NAME_SUBTASK_TYPE_CATEGORY_ID, subtaskTypeCategoryId);
		List<SubtaskType> subtaskTypeList = query.getResultList();
		return subtaskTypeList;
	}

	/**
	 * 
	 * @see com.pes.web.dao.SubtaskTypeDAO{s}listSubtaskType()
	 */
	@Override
	public List<SubtaskType> listSubtaskType() throws PesWebException {
		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<SubtaskType> criteria = builder.createQuery(SubtaskType.class);
			Root<SubtaskType> root = criteria.from(SubtaskType.class);
			criteria.select(root);
			return entityManager.createQuery(criteria).getResultList();
		} catch (Exception e) {
			throw new PesWebException(e);
		}		
	}
}

