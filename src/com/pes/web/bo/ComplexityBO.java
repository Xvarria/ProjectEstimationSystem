package com.pes.web.bo;

import java.util.List;

import com.pes.web.model.Complexity;
import com.pes.web.model.exception.PesWebException;

/**
 * @author mchavarria
 *
 */
public interface ComplexityBO {
	
	/**
	 * Creates a new Complexity 
	 * @param complexity
	 * @throws PesWebException
	 */
	public void save(Complexity complexity) throws PesWebException;
	
	/**
	 * Updates the Complexity
	 * @param complexity
	 * @throws PesWebException
	 */
	public void update(Complexity complexity) throws PesWebException;
	
	/**
	 * Deletes the Complexity
	 * @param complexity
	 * @throws PesWebException
	 */
	public void delete(Complexity complexity) throws PesWebException;
	
	/**
	 * Gets complexity by Id
	 *  	 
	 * @param id
	 * @return
	 * @throws PesWebException
	 */
	public Complexity getComplexityById(int complexityId) throws PesWebException;
	
	/**
	 * Get Complexity List
	 * @return
	 * @throws PesWebException
	 */
	public List<Complexity> listComplexity() throws PesWebException;
}
