package com.pes.web.bo;

import java.util.List;

import com.pes.web.model.Subtask;
import com.pes.web.model.exception.PesWebException;

/**
 * @author mchavarria
 *
 */
public interface SubtaskBO {
	
	/**
	 * Creates a new Subtask 
	 * @param subtask
	 * @throws PesWebException
	 */
	public void save(Subtask subtask) throws PesWebException;
	
	/**
	 * Updates the Subtask
	 * @param subtask
	 * @throws PesWebException
	 */
	public void update(Subtask subtask) throws PesWebException;
	
	/**
	 * Deletes the Subtask
	 * @param subtask
	 * @throws PesWebException
	 */
	public void delete(Subtask subtask) throws PesWebException;
	
	/**
	 * Gets subtask by Id
	 *  	 
	 * @param id
	 * @return
	 * @throws PesWebException
	 */
	public Subtask getSubtaskById(int subtaskId) throws PesWebException;
	
	/**
	 * Get Subtask List
	 * @return
	 * @throws PesWebException
	 */
	public List<Subtask> listSubtask() throws PesWebException;
	
	public List<Subtask> listSubtask(int taskId) throws PesWebException;
	
	public void calculateTime(Subtask subtask) throws PesWebException;
}

