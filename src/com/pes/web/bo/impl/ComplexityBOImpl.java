/**
 * 
 */
package com.pes.web.bo.impl;

import static com.pes.web.model.constant.PesWebConstants.COMPLEXITY_ERROR_GET;
import static com.pes.web.model.constant.PesWebConstants.COMPLEXITY_ERROR_NOT_FOUND;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pes.web.bo.ComplexityBO;
import com.pes.web.dao.ComplexityDAO;
import com.pes.web.model.Complexity;
import com.pes.web.model.exception.ControllerException;
import com.pes.web.model.exception.PesWebException;

/**
 * @author mchavarria
 *
 */
@Service
public class ComplexityBOImpl implements ComplexityBO {

	@Autowired
	private ComplexityDAO complexityDAO;
	
	/**
	 * Get Complexity for database and handle it if null
	 * @param complexityIdStr
	 * @return
	 * @throws ControllerException
	 */
	public Complexity getComplexityFormParamater(int id) throws ControllerException  {
		try {
			Complexity complexity = this.getComplexityById(id);
			if (complexity == null) {
				throw new ControllerException(COMPLEXITY_ERROR_NOT_FOUND);
			}
			return complexity;
		} catch (PesWebException e) {
			throw new ControllerException(COMPLEXITY_ERROR_GET);
		}
	}
	
	/**
	 * @see com.pes.web.service.ComplexityBO{s}save(com.pes.web.model.Complexity)
	 */
	@Override
	@Transactional
	public void save(Complexity complexity) throws PesWebException {
		this.complexityDAO.save(complexity);
	}

	/**
	 * @see com.pes.web.service.ComplexityBO{s}update(com.pes.web.model.Complexity)
	 */
	@Override
	@Transactional
	public void update(Complexity complexity) throws PesWebException {
		this.complexityDAO.update(complexity);
	}

	/**
	 * @see com.pes.web.service.ComplexityBO{s}delete(com.pes.web.model.Complexity)
	 */
	@Override
	@Transactional
	public void delete(Complexity complexity) throws PesWebException {
		this.complexityDAO.delete(complexity);
	}

	/**
	 * @see com.pes.web.service.ComplexityBO{s}getComplexityById(int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Complexity getComplexityById(int complexityId) throws PesWebException {
		return this.complexityDAO.getComplexityById(complexityId);
	}

	/**
	 * @see com.pes.web.service.ComplexityBO{s}listComplexity()
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Complexity> listComplexity() throws PesWebException {
		return this.complexityDAO.listComplexity();
	}

}

