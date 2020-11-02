/**
 * 
 */
package com.pes.web.bo.impl;

import static com.pes.web.model.constant.PesWebConstants.SUBTASK_TYPE_ERROR_GET;
import static com.pes.web.model.constant.PesWebConstants.SUBTASK_TYPE_ERROR_NOT_FOUND;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pes.web.bo.SubtaskTypeBO;
import com.pes.web.dao.SubtaskTypeDAO;
import com.pes.web.model.SubtaskType;
import com.pes.web.model.exception.ControllerException;
import com.pes.web.model.exception.PesWebException;

/**
 * @author mchavarria
 *
 */
@Service
public class SubtaskTypeBOImpl implements SubtaskTypeBO {

	@Autowired
	private SubtaskTypeDAO subtaskTypeDAO;
	
	/**
	 * Get SubtaskType for database and handle it if null
	 * @param subtaskTypeIdStr
	 * @return
	 * @throws ControllerException
	 */
	public SubtaskType getSubtaskTypeFormParamater(int id) throws ControllerException  {
		try {
			SubtaskType subtaskType = this.getSubtaskTypeById(id);
			if (subtaskType == null) {
				throw new ControllerException(SUBTASK_TYPE_ERROR_NOT_FOUND);
			}
			return subtaskType;
		} catch (PesWebException e) {
			throw new ControllerException(SUBTASK_TYPE_ERROR_GET);
		}
	}
	
	/**
	 * @see com.pes.web.service.SubtaskTypeBO{s}save(com.pes.web.model.SubtaskType)
	 */
	@Override
	@Transactional
	public void save(SubtaskType subtaskType) throws PesWebException {
		this.subtaskTypeDAO.save(subtaskType);
	}

	/**
	 * @see com.pes.web.service.SubtaskTypeBO{s}update(com.pes.web.model.SubtaskType)
	 */
	@Override
	@Transactional
	public void update(SubtaskType subtaskType) throws PesWebException {
		this.subtaskTypeDAO.update(subtaskType);
	}

	/**
	 * @see com.pes.web.service.SubtaskTypeBO{s}delete(com.pes.web.model.SubtaskType)
	 */
	@Override
	@Transactional
	public void delete(SubtaskType subtaskType) throws PesWebException {
		this.subtaskTypeDAO.delete(subtaskType);
	}

	/**
	 * @see com.pes.web.service.SubtaskTypeBO{s}getSubtaskTypeById(int)
	 */
	@Override
	@Transactional(readOnly = true)
	public SubtaskType getSubtaskTypeById(int subtaskTypeId) throws PesWebException {
		return this.subtaskTypeDAO.getSubtaskTypeById(subtaskTypeId);
	}

	/**
	 * @see com.pes.web.service.SubtaskTypeBO{s}listSubtaskType()
	 */
	@Override
	@Transactional(readOnly = true)
	public List<SubtaskType> listSubtaskType() throws PesWebException {
		return this.subtaskTypeDAO.listSubtaskType();
	}
	
	/*
	 * 
	 * 
	 */
	@Override
	@Transactional(readOnly = true)
	public List<SubtaskType> listSubtaskTypeByCategoryId(int subtaskTypeCategoryId) throws PesWebException {
		return subtaskTypeDAO.listSubtaskTypeByCategoryId(subtaskTypeCategoryId);
	}

	
	
}

