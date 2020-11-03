package com.pes.web.bo;

import java.util.List;

import com.pes.web.model.Task;
import com.pes.web.model.exception.PesWebException;

/**
 * @author mchavarria
 *
 */
public interface TaskBO {
	
	/**
	 * Creates a new Task 
	 * @param task
	 * @throws PesWebException
	 */
	public void save(Task task) throws PesWebException;
	
	/**
	 * Updates the Task
	 * @param task
	 * @throws PesWebException
	 */
	public void update(Task task) throws PesWebException;
	
	/**
	 * Deletes the Task
	 * @param task
	 * @throws PesWebException
	 */
	public void delete(Task task) throws PesWebException;
	
	/**
	 * Gets task by Id
	 *  	 
	 * @param id
	 * @return
	 * @throws PesWebException
	 */
	public Task getTaskById(int taskId) throws PesWebException;
	
	public List<Task> listTaskByProjectId(int projectId) throws PesWebException;
	
	
	/**
	 * Get Task List
	 * @return
	 * @throws PesWebException
	 */
	public List<Task> listTask() throws PesWebException;
}

