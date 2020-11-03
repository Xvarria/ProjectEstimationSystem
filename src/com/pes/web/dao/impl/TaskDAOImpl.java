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
import com.pes.web.dao.TaskDAO;
import com.pes.web.model.Task;
import com.pes.web.model.exception.PesWebException;

//import annotations.com.pes.web.model.Task_;

/**
 *
 */
@Repository
public class TaskDAOImpl extends AbstractDAO implements TaskDAO {
	
	private static final String QUERY_SELECT = "SELECT c FROM Task c WHERE c.taskId = :taskId";
	private static final String QUERY_SELECT_BY_PROJECT = "SELECT c FROM Task c WHERE c.project.projectId = :projectId";
	private static final String PARAMETER_NAME_TASK_ID = "taskId";
	private static final String PARAMETER_NAME_PROJECT_ID = "projectId";

	/**
	 * 
	 * @see com.pes.web.dao.TaskDAO{s}save(com.pes.web.model.Task)
	 */
	@Override
	public void save(Task task) throws PesWebException {
		super.save(task);
	}

	/**
	 * 
	 * @see com.pes.web.dao.TaskDAO{s}update(com.pes.web.model.Task)
	 */
	@Override
	public void update(Task task) throws PesWebException {
		super.update(task);
	}

	/**
	 * 
	 * @see com.pes.web.dao.TaskDAO{s}delete(com.pes.web.model.Task)
	 */
	@Override
	public void delete(Task task) throws PesWebException {
		super.delete(task);
	}

	/**
	 * 
	 * @see com.pes.web.dao.TaskDAO{s}getTaskById(int)
	 */
	@Override
	public Task getTaskById(int taskId) throws PesWebException {
		TypedQuery<Task> query = entityManager.createQuery(QUERY_SELECT, Task.class);
		query.setParameter(PARAMETER_NAME_TASK_ID, taskId);
		Task task = query.getSingleResult();
		return task;
	}
	
	public List<Task> listTaskByProjectId(int projectId){
		TypedQuery<Task> query = entityManager.createQuery(QUERY_SELECT_BY_PROJECT, Task.class);
		query.setParameter(PARAMETER_NAME_PROJECT_ID, projectId);
		return query.getResultList();		
	}

	/**
	 * 
	 * @see com.pes.web.dao.TaskDAO{s}listTask()
	 */
	@Override
	public List<Task> listTask() throws PesWebException {
		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Task> criteria = builder.createQuery(Task.class);
			Root<Task> root = criteria.from(Task.class);
			criteria.select(root);
			return entityManager.createQuery(criteria).getResultList();
		} catch (Exception e) {
			throw new PesWebException(e);
		}		
	}
}

