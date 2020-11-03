package com.pes.web.bo;

import java.util.List;

import com.pes.web.model.Project;
import com.pes.web.model.exception.PesWebException;

/**
 * @author mchavarria
 *
 */
public interface ProjectBO {
	
	/**
	 * Creates a new Project 
	 * @param project
	 * @throws PesWebException
	 */
	public void save(Project project) throws PesWebException;
	
	/**
	 * Updates the Project
	 * @param project
	 * @throws PesWebException
	 */
	public void update(Project project) throws PesWebException;
	
	/**
	 * Deletes the Project
	 * @param project
	 * @throws PesWebException
	 */
	public void delete(Project project) throws PesWebException;
	
	/**
	 * Gets project by Id
	 *  	 
	 * @param id
	 * @return
	 * @throws PesWebException
	 */
	public Project getProjectById(int projectId) throws PesWebException;
	
	/**
	 * Get Project List
	 * @return
	 * @throws PesWebException
	 */
	public List<Project> listProject() throws PesWebException;
}

