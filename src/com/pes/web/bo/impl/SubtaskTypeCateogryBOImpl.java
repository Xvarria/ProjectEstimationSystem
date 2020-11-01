package com.pes.web.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pes.web.bo.SubtaskTypeCategoryBO;
import com.pes.web.dao.SubtaskTypeCategoryDAO;
import com.pes.web.model.SubtaskTypeCategory;
import com.pes.web.model.exception.PesWebException;

@Service
public class SubtaskTypeCateogryBOImpl implements SubtaskTypeCategoryBO {

	@Autowired
	private SubtaskTypeCategoryDAO substaskTypeCategoryDAOImpl;

	/**
	 * @param subtaskTypeCategory
	 * @throws PesWebException
	 * @see com.pes.web.dao.SubtaskTypeCategoryDAO#save(com.pes.web.model.SubtaskTypeCategory)
	 */
	@Override
	@Transactional	
	public void save(SubtaskTypeCategory subtaskTypeCategory) throws PesWebException {
		substaskTypeCategoryDAOImpl.save(subtaskTypeCategory);
	}

	/**
	 * @param subtaskTypeCategory
	 * @throws PesWebException
	 * @see com.pes.web.dao.SubtaskTypeCategoryDAO#update(com.pes.web.model.SubtaskTypeCategory)
	 */
	@Override
	@Transactional	
	public void update(SubtaskTypeCategory subtaskTypeCategory) throws PesWebException {
		substaskTypeCategoryDAOImpl.update(subtaskTypeCategory);
	}

	/**
	 * @param subtaskTypeCategory
	 * @throws PesWebException
	 * @see com.pes.web.dao.SubtaskTypeCategoryDAO#delete(com.pes.web.model.SubtaskTypeCategory)
	 */
	@Override
	@Transactional	
	public void delete(SubtaskTypeCategory subtaskTypeCategory) throws PesWebException {
		substaskTypeCategoryDAOImpl.delete(subtaskTypeCategory);
	}

	/**
	 * @param subtaskTypeCategoryId
	 * @return
	 * @throws PesWebException
	 * @see com.pes.web.dao.SubtaskTypeCategoryDAO#getSubtaskTypeCategoryById(int)
	 */
	@Override
	@Transactional(readOnly = true)
	public SubtaskTypeCategory getSubtaskTypeCategoryById(int subtaskTypeCategoryId) throws PesWebException {
		return substaskTypeCategoryDAOImpl.getSubtaskTypeCategoryById(subtaskTypeCategoryId);
	}

	/**
	 * @return
	 * @throws PesWebException
	 * @see com.pes.web.dao.SubtaskTypeCategoryDAO#listSubtaskTypeCategory()
	 */
	@Override
	@Transactional(readOnly = true)
	public List<SubtaskTypeCategory> listSubtaskTypeCategory() throws PesWebException {
		return substaskTypeCategoryDAOImpl.listSubtaskTypeCategory();
	}
	

	
}
