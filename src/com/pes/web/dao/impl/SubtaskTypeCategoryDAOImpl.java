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
import com.pes.web.dao.SubtaskTypeCategoryDAO;
import com.pes.web.model.SubtaskTypeCategory;
import com.pes.web.model.exception.PesWebException;

/**
 *
 */
@Repository
public class SubtaskTypeCategoryDAOImpl extends AbstractDAO implements SubtaskTypeCategoryDAO {
	
	private static final String QUERY_SELECT = "SELECT stc FROM SubtaskTypeCategory stc WHERE stc.subtaskTypeCategoryId = :subtaskTypeCategoryId";
	private static final String PARAMETER_NAME_SUBTASK_TYPE_CATEGORY_ID = "subtaskTypeCategoryId";

	/**
	 * 
	 * @see com.pes.web.dao.SubtaskTypeCategoryDAO{s}save(com.pes.web.model.SubtaskTypeCategory)
	 */
	@Override
	public void save(SubtaskTypeCategory subtaskTypeCategory) throws PesWebException {
		super.save(subtaskTypeCategory);
	}

	/**
	 * 
	 * @see com.pes.web.dao.SubtaskTypeCategoryDAO{s}update(com.pes.web.model.SubtaskTypeCategory)
	 */
	@Override
	public void update(SubtaskTypeCategory subtaskTypeCategory) throws PesWebException {
		super.update(subtaskTypeCategory);
	}

	/**
	 * 
	 * @see com.pes.web.dao.SubtaskTypeCategoryDAO{s}delete(com.pes.web.model.SubtaskTypeCategory)
	 */
	@Override
	public void delete(SubtaskTypeCategory subtaskTypeCategory) throws PesWebException {
		super.delete(subtaskTypeCategory);
	}

	/**
	 * 
	 * @see com.pes.web.dao.SubtaskTypeCategoryDAO{s}getSubtaskTypeCategoryById(int)
	 */
	@Override
	public SubtaskTypeCategory getSubtaskTypeCategoryById(int subtaskTypeCategoryId) throws PesWebException {
		TypedQuery<SubtaskTypeCategory> query = entityManager.createQuery(QUERY_SELECT, SubtaskTypeCategory.class);
		query.setParameter(PARAMETER_NAME_SUBTASK_TYPE_CATEGORY_ID, subtaskTypeCategoryId);
		SubtaskTypeCategory subtaskTypeCategory = query.getSingleResult();
		return subtaskTypeCategory;
	}

	/**
	 * 
	 * @see com.pes.web.dao.SubtaskTypeCategoryDAO{s}listSubtaskTypeCategory()
	 */
	@Override
	public List<SubtaskTypeCategory> listSubtaskTypeCategory() throws PesWebException {
		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<SubtaskTypeCategory> criteria = builder.createQuery(SubtaskTypeCategory.class);
			Root<SubtaskTypeCategory> root = criteria.from(SubtaskTypeCategory.class);
			criteria.select(root);
			return entityManager.createQuery(criteria).getResultList();
		} catch (Exception e) {
			throw new PesWebException(e);
		}		
	}
}
