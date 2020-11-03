/**
 * 
 */
package com.pes.web.bo.impl;

import static com.pes.web.model.constant.PesWebConstants.TASK_ERROR_GET;
import static com.pes.web.model.constant.PesWebConstants.TASK_ERROR_NOT_FOUND;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pes.web.bo.TaskBO;
import com.pes.web.dao.TaskDAO;
import com.pes.web.model.Task;
import com.pes.web.model.exception.ControllerException;
import com.pes.web.model.exception.PesWebException;

/**
 * @author mchavarria
 *
 */
@Service
public class TaskBOImpl implements TaskBO {

	@Autowired
	private TaskDAO taskDAO;
	
	/**
	 * Get Task for database and handle it if null
	 * @param taskIdStr
	 * @return
	 * @throws ControllerException
	 */
	public Task getTaskFormParamater(int id) throws ControllerException  {
		try {
			Task task = this.getTaskById(id);
			if (task == null) {
				throw new ControllerException(TASK_ERROR_NOT_FOUND);
			}
			return task;
		} catch (PesWebException e) {
			throw new ControllerException(TASK_ERROR_GET);
		}
	}
	
	/**
	 * @see com.pes.web.service.TaskBO{s}save(com.pes.web.model.Task)
	 */
	@Override
	@Transactional
	public void save(Task task) throws PesWebException {
		this.taskDAO.save(task);
	}

	/**
	 * @see com.pes.web.service.TaskBO{s}update(com.pes.web.model.Task)
	 */
	@Override
	@Transactional
	public void update(Task task) throws PesWebException {
		this.taskDAO.update(task);
	}

	/**
	 * @see com.pes.web.service.TaskBO{s}delete(com.pes.web.model.Task)
	 */
	@Override
	@Transactional
	public void delete(Task task) throws PesWebException {
		this.taskDAO.delete(task);
	}

	/**
	 * @see com.pes.web.service.TaskBO{s}getTaskById(int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Task getTaskById(int taskId) throws PesWebException {
		return this.taskDAO.getTaskById(taskId);
	}

	
	
	/**
	 * @param projectId
	 * @return
	 * @throws PesWebException
	 * @see com.pes.web.dao.TaskDAO#listTaskByProjectId(int)
	 */
	public List<Task> listTaskByProjectId(int projectId) throws PesWebException {
		return taskDAO.listTaskByProjectId(projectId);
	}

	/**
	 * @see com.pes.web.service.TaskBO{s}listTask()
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Task> listTask() throws PesWebException {
		return this.taskDAO.listTask();
	}
}

