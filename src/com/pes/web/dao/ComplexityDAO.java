package com.pes.web.dao;

import java.util.List;

import com.pes.web.model.Complexity;
import com.pes.web.model.exception.PesWebException;

/**
 * @author mchavarria
 *
 */
public interface ComplexityDAO {
	
	/**
	 * Creates a new complexity record
	 * @param Complexity
	 * @throws PesWebException
	 */
	public void save(Complexity Complexity) throws PesWebException;
	
	/**
	 * Updates the complexity record on the database
	 * @param Complexity
	 * @throws PesWebException
	 */
	public void update(Complexity Complexity) throws PesWebException;
	
	/**
	 * Delete a complexity record
	 * @param Complexity
	 * @throws PesWebException
	 */
	public void delete(Complexity Complexity) throws PesWebException;
	
	/**
	 * Get Complexity by Id
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
	public Complexity getComplexityById(int ComplexityId) throws PesWebException;
	
	/**
	 * Get the complexity list form database
	 * @return
	 * @throws PesWebException
	 */
	public List<Complexity> listComplexity() throws PesWebException;
}
