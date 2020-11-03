/**
 * 
 */
package com.pes.web.bo.impl;

import static com.pes.web.model.constant.PesWebConstants.PROJECT_ERROR_GET;
import static com.pes.web.model.constant.PesWebConstants.PROJECT_ERROR_NOT_FOUND;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pes.web.bo.ProjectBO;
import com.pes.web.dao.ProjectDAO;
import com.pes.web.model.Project;
import com.pes.web.model.exception.ControllerException;
import com.pes.web.model.exception.PesWebException;

/**
 * @author mchavarria
 *
 */
@Service
public class ProjectBOImpl implements ProjectBO {

	@Autowired
	private ProjectDAO projectDAO;
	
	/**
	 * Get Project for database and handle it if null
	 * @param projectIdStr
	 * @return
	 * @throws ControllerException
	 */
	public Project getProjectFormParamater(int id) throws ControllerException  {
		try {
			Project project = this.getProjectById(id);
			if (project == null) {
				throw new ControllerException(PROJECT_ERROR_NOT_FOUND);
			}
			return project;
		} catch (PesWebException e) {
			throw new ControllerException(PROJECT_ERROR_GET);
		}
	}
	
	/**
	 * @see com.pes.web.service.ProjectBO{s}save(com.pes.web.model.Project)
	 */
	@Override
	@Transactional
	public void save(Project project) throws PesWebException {
		this.projectDAO.save(project);
	}

	/**
	 * @see com.pes.web.service.ProjectBO{s}update(com.pes.web.model.Project)
	 */
	@Override
	@Transactional
	public void update(Project project) throws PesWebException {
		this.projectDAO.update(project);
	}

	/**
	 * @see com.pes.web.service.ProjectBO{s}delete(com.pes.web.model.Project)
	 */
	@Override
	@Transactional
	public void delete(Project project) throws PesWebException {
		this.projectDAO.delete(project);
	}

	/**
	 * @see com.pes.web.service.ProjectBO{s}getProjectById(int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Project getProjectById(int projectId) throws PesWebException {
		return this.projectDAO.getProjectById(projectId);
	}

	/**
	 * @see com.pes.web.service.ProjectBO{s}listProject()
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Project> listProject() throws PesWebException {
		return this.projectDAO.listProject();
	}
}

