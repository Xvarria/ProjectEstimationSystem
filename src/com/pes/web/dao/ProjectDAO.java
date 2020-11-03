package com.pes.web.dao;

import java.util.List;

import com.pes.web.model.Project;
import com.pes.web.model.exception.PesWebException;

/**
 * @author mchavarria
 *
 */
public interface ProjectDAO {
	
	/**
	 * Creates a new project record
	 * @param project
	 * @throws PesWebException
	 */
	public void save(Project project) throws PesWebException;
	
	/**
	 * Updates the project record on the database
	 * @param project
	 * @throws PesWebException
	 */
	public void update(Project project) throws PesWebException;
	
	/**
	 * Delete a project record
	 * @param project
	 * @throws PesWebException
	 */
	public void delete(Project project) throws PesWebException;
	
	/**
	 * Get Project by Id
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
	public Project getProjectById(int projectId) throws PesWebException;
	
	/**
	 * Get the project list form database
	 * @return
	 * @throws PesWebException
	 */
	public List<Project> listProject() throws PesWebException;
}

