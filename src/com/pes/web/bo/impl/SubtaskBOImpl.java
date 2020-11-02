/**
 * 
 */
package com.pes.web.bo.impl;

import static com.pes.web.model.constant.PesWebConstants.SUBTASK_ERROR_GET;
import static com.pes.web.model.constant.PesWebConstants.SUBTASK_ERROR_NOT_FOUND;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pes.web.bo.SubtaskBO;
import com.pes.web.dao.SubtaskDAO;
import com.pes.web.model.Subtask;
import com.pes.web.model.exception.ControllerException;
import com.pes.web.model.exception.PesWebException;

/**
 * @author mchavarria
 *
 */
@Service
public class SubtaskBOImpl implements SubtaskBO {

	@Autowired
	private SubtaskDAO subtaskDAO;
	
	/**
	 * Get Subtask for database and handle it if null
	 * @param subtaskIdStr
	 * @return
	 * @throws ControllerException
	 */
	public Subtask getSubtaskFormParamater(int id) throws ControllerException  {
		try {
			Subtask subtask = this.getSubtaskById(id);
			if (subtask == null) {
				throw new ControllerException(SUBTASK_ERROR_NOT_FOUND);
			}
			return subtask;
		} catch (PesWebException e) {
			throw new ControllerException(SUBTASK_ERROR_GET);
		}
	}
	
	/**
	 * @see com.pes.web.service.SubtaskBO{s}save(com.pes.web.model.Subtask)
	 */
	@Override
	@Transactional
	public void save(Subtask subtask) throws PesWebException {
		this.subtaskDAO.save(subtask);
	}

	/**
	 * @see com.pes.web.service.SubtaskBO{s}update(com.pes.web.model.Subtask)
	 */
	@Override
	@Transactional
	public void update(Subtask subtask) throws PesWebException {
		this.subtaskDAO.update(subtask);
	}

	/**
	 * @see com.pes.web.service.SubtaskBO{s}delete(com.pes.web.model.Subtask)
	 */
	@Override
	@Transactional
	public void delete(Subtask subtask) throws PesWebException {
		this.subtaskDAO.delete(subtask);
	}

	/**
	 * @see com.pes.web.service.SubtaskBO{s}getSubtaskById(int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Subtask getSubtaskById(int subtaskId) throws PesWebException {
		return this.subtaskDAO.getSubtaskById(subtaskId);
	}

	/**
	 * @see com.pes.web.service.SubtaskBO{s}listSubtask()
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Subtask> listSubtask() throws PesWebException {
		return this.subtaskDAO.listSubtask();
	}
}

