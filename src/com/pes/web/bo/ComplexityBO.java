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
	 * Crea un nuevo registro de complexity en la base de datos
	 * @param complexity
	 * @throws PesWebException
	 */
	public void save(Complexity complexity) throws PesWebException;
	
	/**
	 * Actualiza el objeto complexity en la base de datos
	 * @param complexity
	 * @throws PesWebException
	 */
	public void update(Complexity complexity) throws PesWebException;
	
	/**
	 * Elimina el objeto complexity de la base de datos
	 * @param complexity
	 * @throws PesWebException
	 */
	public void delete(Complexity complexity) throws PesWebException;
	
	/**
	 * Obtiene complexity por Id
	 *  	 
	 * @param id
	 * @return
	 * @throws PesWebException
	 */
	public Complexity getComplexityById(int complexityId) throws PesWebException;
	
	/**
	 * Obtiene la lista de complexity de la base de datos
	 * @return
	 * @throws PesWebException
	 */
	public List<Complexity> listComplexity() throws PesWebException;
}
