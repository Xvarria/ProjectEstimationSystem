package com.pes.web.controller;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pes.web.bo.ComplexityBO;
import com.pes.web.form.ajax.ComplexityAjaxResponse;
import com.pes.web.model.Complexity;
import com.pes.web.model.constant.ResponseStatus;
import com.pes.web.model.exception.PesWebException;

@Controller
public class AjaxController extends BasicController{
	
	@Autowired
	private ComplexityBO complexityBo;
		
	final static Logger log = Logger.getLogger(AjaxController.class);
	
	@RequestMapping("ajax/getComplexityList")
	public @ResponseBody String ajaxComplexityList() throws PesWebException{
		log.debug("Ajax method to get Complexity");
		ObjectMapper mapper = new ObjectMapper();
		try {
			Collection<Complexity> complexityList = this.complexityBo.listComplexity();
			return mapper.writeValueAsString(complexityList);
		} catch (PesWebException e) {
			log.error("Error getting the complexity List", e);
			throw e;
		} catch (JsonProcessingException e) {
			log.error("Error on ajax call to get the complexities", e);
			throw new PesWebException(e);
		}
	}

	@RequestMapping("ajax/getComplexityById")
	public @ResponseBody String ajaxComplexityList(@RequestParam String complexityIdStr) throws PesWebException{
		log.debug("Ajax method to get Complexity");
		ObjectMapper mapper = new ObjectMapper();
		ComplexityAjaxResponse response = new ComplexityAjaxResponse();
		response.setResponseStatus(ResponseStatus.SUCCESS);
		try {
			int numericValue = 0;
			Complexity complexity = new Complexity();
			try {
				numericValue = Integer.parseInt(complexityIdStr);
				complexity.setComplexityId(numericValue);
			} catch (Exception e) {
				String errorMessage = this.propertyMessageBO.getMessageFromProperties("complexity.form.error.complexityid-nan", "Error");
				response.setResponseStatus(ResponseStatus.ERROR);
				response.setErrorMessage(errorMessage);
			}
			
			if (ResponseStatus.SUCCESS.equals(response.getResponseStatus())) {
				try {
					complexity = this.complexityBo.getComplexityById(numericValue);
					response.setComplexity(complexity);
				} catch (Exception e) {
					String errorMessage = this.propertyMessageBO.getMessageFromProperties("complexity.form.error.complexity-not-exist", "Error");
					response.setResponseStatus(ResponseStatus.ERROR);
					response.setErrorMessage(errorMessage);					
				}				
			}
			return mapper.writeValueAsString(response);
		} catch (JsonProcessingException e) {
			log.error("Error on ajax call to get the complexities", e);
			throw new PesWebException(e);
		}
	}

	
}
