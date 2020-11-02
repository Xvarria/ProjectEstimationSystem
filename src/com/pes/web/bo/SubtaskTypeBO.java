package com.pes.web.bo;

import java.util.List;

import com.pes.web.model.SubtaskType;
import com.pes.web.model.exception.PesWebException;

/**
 * @author mchavarria
 *
 */
public interface SubtaskTypeBO {
	
	/**
	 * Creates a new SubtaskType 
	 * @param subtaskType
	 * @throws PesWebException
	 */
	public void save(SubtaskType subtaskType) throws PesWebException;
	
	/**
	 * Updates the SubtaskType
	 * @param subtaskType
	 * @throws PesWebException
	 */
	public void update(SubtaskType subtaskType) throws PesWebException;
	
	/**
	 * Deletes the SubtaskType
	 * @param subtaskType
	 * @throws PesWebException
	 */
	public void delete(SubtaskType subtaskType) throws PesWebException;
	
	/**
	 * Gets subtaskType by Id
	 *  	 
	 * @param id
	 * @return
	 * @throws PesWebException
	 */
	public SubtaskType getSubtaskTypeById(int subtaskTypeId) throws PesWebException;
	
	/**
	 * Get SubtaskType List
	 * @return
	 * @throws PesWebException
	 */
	public List<SubtaskType> listSubtaskType() throws PesWebException;
}

