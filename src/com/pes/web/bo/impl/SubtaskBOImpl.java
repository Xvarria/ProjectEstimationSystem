/**
 * 
 */
package com.pes.web.bo.impl;

import static com.pes.web.model.constant.PesWebConstants.SUBTASK_ERROR_GET;
import static com.pes.web.model.constant.PesWebConstants.SUBTASK_ERROR_NOT_FOUND;

import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pes.web.bo.SubtaskBO;
import com.pes.web.dao.SubtaskDAO;
import com.pes.web.model.Subtask;
import com.pes.web.model.exception.ControllerException;
import com.pes.web.model.exception.PesWebException;

/**
 * @author mchavarria
 *
 */
@Service
public class SubtaskBOImpl implements SubtaskBO {

	@Autowired
	private SubtaskDAO subtaskDAO;
	
	/**
	 * Get Subtask for database and handle it if null
	 * @param subtaskIdStr
	 * @return
	 * @throws ControllerException
	 */
	public Subtask getSubtaskFormParamater(int id) throws ControllerException  {
		try {
			Subtask subtask = this.getSubtaskById(id);
			if (subtask == null) {
				throw new ControllerException(SUBTASK_ERROR_NOT_FOUND);
			}
			return subtask;
		} catch (PesWebException e) {
			throw new ControllerException(SUBTASK_ERROR_GET);
		}
	}
	
	/**
	 * @see com.pes.web.service.SubtaskBO{s}save(com.pes.web.model.Subtask)
	 */
	@Override
	@Transactional
	public void save(Subtask subtask) throws PesWebException {
		this.subtaskDAO.save(subtask);
	}

	/**
	 * @see com.pes.web.service.SubtaskBO{s}update(com.pes.web.model.Subtask)
	 */
	@Override
	@Transactional
	public void update(Subtask subtask) throws PesWebException {
		this.subtaskDAO.update(subtask);
	}

	/**
	 * @see com.pes.web.service.SubtaskBO{s}delete(com.pes.web.model.Subtask)
	 */
	@Override
	@Transactional
	public void delete(Subtask subtask) throws PesWebException {
		this.subtaskDAO.delete(subtask);
	}

	/**
	 * @see com.pes.web.service.SubtaskBO{s}getSubtaskById(int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Subtask getSubtaskById(int subtaskId) throws PesWebException {
		return this.subtaskDAO.getSubtaskById(subtaskId);
	}

	/**
	 * @see com.pes.web.service.SubtaskBO{s}listSubtask()
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Subtask> listSubtask() throws PesWebException {
		return this.subtaskDAO.listSubtask();
	}

	/**
	 * @param taskId
	 * @return
	 * @throws PesWebException
	 * @see com.pes.web.dao.SubtaskDAO#listSubtask(int)
	 */
	public List<Subtask> listSubtask(int taskId) throws PesWebException {
		return subtaskDAO.listSubtask(taskId);
	}

	@Override
	public void calculateTime(Subtask subtask) throws PesWebException {
		if (subtask.isAutoCalculation()) {
			//CaluclateTime
			try {
				ScriptEngineManager mgr = new ScriptEngineManager();
				ScriptEngine engine = mgr.getEngineByName("JavaScript");
				String basicEvaluation = "var object='${object}';var BASE_HOUR = Number(${bh});var COEF = Number(${ch});var model= JSON.parse(object);${calculation}";
				basicEvaluation = basicEvaluation.replace("${object}", subtask.getReferenceMode());
				basicEvaluation = basicEvaluation.replace("${bh}", String.valueOf(subtask.getComplexity().getBaseHour()));
				basicEvaluation = basicEvaluation.replace("${ch}", String.valueOf(subtask.getComplexity().getMultiplexor()));
				basicEvaluation = basicEvaluation.replace("${calculation}", subtask.getSubtaskType().getCalculation());
				basicEvaluation = basicEvaluation.replaceAll("\n","");
				Object returningVal = engine.eval(basicEvaluation);
				subtask.setCalculatedTime(Float.parseFloat(returningVal.toString()));
			} catch (ScriptException|NumberFormatException e) {
				throw new PesWebException("Invalid calculation", e);
			}
		}else {
			subtask.setCalculatedTime(subtask.getTime());
		}
		
		
	}
	

}

