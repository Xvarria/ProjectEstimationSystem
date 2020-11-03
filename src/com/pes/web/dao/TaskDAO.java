package com.pes.web.dao;

import java.util.List;

import com.pes.web.model.Task;
import com.pes.web.model.exception.PesWebException;

/**
 * @author mchavarria
 *
 */
public interface TaskDAO {
	
	/**
	 * Creates a new task record
	 * @param task
	 * @throws PesWebException
	 */
	public void save(Task task) throws PesWebException;
	
	/**
	 * Updates the task record on the database
	 * @param task
	 * @throws PesWebException
	 */
	public void update(Task task) throws PesWebException;
	
	/**
	 * Delete a task record
	 * @param task
	 * @throws PesWebException
	 */
	public void delete(Task task) throws PesWebException;
	
	/**
	 * Get Task by Id
	 * 
	 * *Important* Hibernate 5 required the dependcy for the POM: hibernate-jpamodelgen and its respective plugin
	 * To remove errors on the UI it is required the complier option to allow annotation procesesing and add the option target/metamodel
	 * 
	 * https://docs.jboss.org/hibernate/orm/5.0/topical/html/metamodelgen/MetamodelGenerator.html 
	 * 	 
	 * @param id
	 * @return
	 * @throws PesWebException
	 */
	public Task getTaskById(int taskId) throws PesWebException;
	
	public List<Task> listTaskByProjectId(int projectId) throws PesWebException;
	
	/**
	 * Get the task list form database
	 * @return
	 * @throws PesWebException
	 */
	public List<Task> listTask() throws PesWebException;
}

