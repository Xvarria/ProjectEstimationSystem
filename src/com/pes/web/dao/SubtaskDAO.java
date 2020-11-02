package com.pes.web.dao;

import java.util.List;

import com.pes.web.model.Subtask;
import com.pes.web.model.exception.PesWebException;

/**
 * @author mchavarria
 *
 */
public interface SubtaskDAO {
	
	/**
	 * Creates a new subtask record
	 * @param subtask
	 * @throws PesWebException
	 */
	public void save(Subtask subtask) throws PesWebException;
	
	/**
	 * Updates the subtask record on the database
	 * @param subtask
	 * @throws PesWebException
	 */
	public void update(Subtask subtask) throws PesWebException;
	
	/**
	 * Delete a subtask record
	 * @param subtask
	 * @throws PesWebException
	 */
	public void delete(Subtask subtask) throws PesWebException;
	
	/**
	 * Get Subtask by Id
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
	public Subtask getSubtaskById(int subtaskId) throws PesWebException;
	
	/**
	 * Get the subtask list form database
	 * @return
	 * @throws PesWebException
	 */
	public List<Subtask> listSubtask() throws PesWebException;
}

