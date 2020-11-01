package com.pes.web.bo;

import java.util.List;

import com.pes.web.model.SubtaskTypeCategory;
import com.pes.web.model.exception.PesWebException;

/**
 * @author mchavarria
 *
 */
public interface SubtaskTypeCategoryBO {
	
	/**
	 * Creates a new subtaskCategoryType 
	 * @param subtaskCategoryType
	 * @throws PesWebException
	 */
	public void save(SubtaskTypeCategory subtaskCategoryType) throws PesWebException;
	
	/**
	 * Updates the subtaskCategoryType
	 * @param subtaskCategoryType
	 * @throws PesWebException
	 */
	public void update(SubtaskTypeCategory subtaskCategoryType) throws PesWebException;
	
	/**
	 * Delete subtaskCategoryType
	 * @param subtaskCategoryType
	 * @throws PesWebException
	 */
	public void delete(SubtaskTypeCategory subtaskCategoryType) throws PesWebException;
	
	/**
	 * Gets subtaskCategoryType by Id
	 *  	 
	 * @param id
	 * @return
	 * @throws PesWebException
	 */
	public SubtaskTypeCategory getSubtaskTypeCategoryById(int subtaskCategoryTypeId) throws PesWebException;
	
	/**
	 *Gets subtaskCategoryType list of the DB
	 * @return
	 * @throws PesWebException
	 */
	public List<SubtaskTypeCategory> listSubtaskTypeCategory() throws PesWebException;
}
