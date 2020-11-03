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
import com.pes.web.dao.ProjectDAO;
import com.pes.web.model.Project;
import com.pes.web.model.exception.PesWebException;

//import annotations.com.pes.web.model.Project_;

/**
 *
 */
@Repository
public class ProjectDAOImpl extends AbstractDAO implements ProjectDAO {
	
	private static final String QUERY_SELECT = "SELECT p FROM Project p WHERE p.projectId = :projectId";
	private static final String PARAMETER_NAME_PROJECT_ID = "projectId";

	/**
	 * 
	 * @see com.pes.web.dao.ProjectDAO{s}save(com.pes.web.model.Project)
	 */
	@Override
	public void save(Project project) throws PesWebException {
		super.save(project);
	}

	/**
	 * 
	 * @see com.pes.web.dao.ProjectDAO{s}update(com.pes.web.model.Project)
	 */
	@Override
	public void update(Project project) throws PesWebException {
		super.update(project);
	}

	/**
	 * 
	 * @see com.pes.web.dao.ProjectDAO{s}delete(com.pes.web.model.Project)
	 */
	@Override
	public void delete(Project project) throws PesWebException {
		super.delete(project);
	}

	/**
	 * 
	 * @see com.pes.web.dao.ProjectDAO{s}getProjectById(int)
	 */
	@Override
	public Project getProjectById(int projectId) throws PesWebException {
		TypedQuery<Project> query = entityManager.createQuery(QUERY_SELECT, Project.class);
		query.setParameter(PARAMETER_NAME_PROJECT_ID, projectId);
		Project project = query.getSingleResult();
		return project;
	}

	/**
	 * 
	 * @see com.pes.web.dao.ProjectDAO{s}listProject()
	 */
	@Override
	public List<Project> listProject() throws PesWebException {
		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Project> criteria = builder.createQuery(Project.class);
			Root<Project> root = criteria.from(Project.class);
			criteria.select(root);
			return entityManager.createQuery(criteria).getResultList();
		} catch (Exception e) {
			throw new PesWebException(e);
		}		
	}
}

