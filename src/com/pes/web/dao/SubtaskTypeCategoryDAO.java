package com.pes.web.dao;

import java.util.List;

import com.pes.web.model.SubtaskTypeCategory;
import com.pes.web.model.exception.PesWebException;

/**
 * @author mchavarria
 *
 */
public interface SubtaskTypeCategoryDAO {
	
	/**
	 * Creates a new subtaskTypeCategory record
	 * @param subtaskTypeCategory
	 * @throws PesWebException
	 */
	public void save(SubtaskTypeCategory subtaskTypeCategory) throws PesWebException;
	
	/**
	 * Updates the subtaskTypeCategory record on the database
	 * @param subtaskTypeCategory
	 * @throws PesWebException
	 */
	public void update(SubtaskTypeCategory subtaskTypeCategory) throws PesWebException;
	
	/**
	 * Delete a subtaskTypeCategory record
	 * @param subtaskTypeCategory
	 * @throws PesWebException
	 */
	public void delete(SubtaskTypeCategory subtaskTypeCategory) throws PesWebException;
	
	/**
	 * Get SubtaskTypeCategory by Id
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
	public SubtaskTypeCategory getSubtaskTypeCategoryById(int subtaskTypeCategoryId) throws PesWebException;
	
	/**
	 * Get the subtaskTypeCategory list form database
	 * @return
	 * @throws PesWebException
	 */
	public List<SubtaskTypeCategory> listSubtaskTypeCategory() throws PesWebException;
}
