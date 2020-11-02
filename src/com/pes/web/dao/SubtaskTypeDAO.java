package com.pes.web.dao;

import java.util.List;

import com.pes.web.model.SubtaskType;
import com.pes.web.model.exception.PesWebException;

/**
 * @author mchavarria
 *
 */
public interface SubtaskTypeDAO {
	
	/**
	 * Creates a new subtaskType record
	 * @param subtaskType
	 * @throws PesWebException
	 */
	public void save(SubtaskType subtaskType) throws PesWebException;
	
	/**
	 * Updates the subtaskType record on the database
	 * @param subtaskType
	 * @throws PesWebException
	 */
	public void update(SubtaskType subtaskType) throws PesWebException;
	
	/**
	 * Delete a subtaskType record
	 * @param subtaskType
	 * @throws PesWebException
	 */
	public void delete(SubtaskType subtaskType) throws PesWebException;
	
	/**
	 * Get SubtaskType by Id
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
	public SubtaskType getSubtaskTypeById(int subtaskTypeId) throws PesWebException;
	
	/**
	 * Get the subtaskType list form database
	 * @return
	 * @throws PesWebException
	 */
	public List<SubtaskType> listSubtaskType() throws PesWebException;
	
	/**
	 * 
	 * @param subtaskTypeCategoryId
	 * @return
	 * @throws PesWebException
	 */
	public List<SubtaskType> listSubtaskTypeByCategoryId(int subtaskTypeCategoryId) throws PesWebException;
}

