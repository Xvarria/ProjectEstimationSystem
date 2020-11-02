package com.pes.web.controller;

import java.util.ArrayList;
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
import com.pes.web.bo.SubtaskBO;
import com.pes.web.bo.SubtaskTypeBO;
import com.pes.web.bo.SubtaskTypeCategoryBO;
import com.pes.web.form.ajax.ComplexityAjaxResponse;
import com.pes.web.form.ajax.SubtaskAjaxResponse;
import com.pes.web.form.ajax.SubtaskTypeAjaxResponse;
import com.pes.web.form.ajax.SubtaskTypeCategoryAjaxResponse;
import com.pes.web.model.Complexity;
import com.pes.web.model.Subtask;
import com.pes.web.model.SubtaskType;
import com.pes.web.model.SubtaskTypeCategory;
import com.pes.web.model.constant.ResponseStatus;
import com.pes.web.model.exception.PesWebException;

@Controller
public class AjaxController extends BasicController{
	
	@Autowired
	private ComplexityBO complexityBo;
		
	@Autowired
	private SubtaskTypeCategoryBO subtaskTypeCategoryBO;
	
	@Autowired
	private SubtaskTypeBO subtaskTypeBo;
	
	@Autowired
	private SubtaskBO subtaskBo;
	
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
	public @ResponseBody String getComplexityById(@RequestParam String complexityIdStr) throws PesWebException{
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

	@RequestMapping("ajax/getSubtaskTypeCategoryList")
	public @ResponseBody String getSubtaskTypeCategoryList() throws PesWebException{
		log.debug("Ajax method to get SubtaskTypeCategory");
		ObjectMapper mapper = new ObjectMapper();
		try {
			Collection<SubtaskTypeCategory> subtaskTypeCategoryList = this.subtaskTypeCategoryBO.listSubtaskTypeCategory();
			return mapper.writeValueAsString(subtaskTypeCategoryList);
		} catch (PesWebException e) {
			log.error("Error getting the subtaskTypeCategory List", e);
			throw e;
		} catch (JsonProcessingException e) {
			log.error("Error on ajax call to get the complexities", e);
			throw new PesWebException(e);
		}
	}

	@RequestMapping("ajax/getSubtaskTypeCategoryById")
	public @ResponseBody String getSubtaskTypeCategoryById(@RequestParam String subtaskTypeCategoryIdStr) throws PesWebException{
		log.debug("Ajax method to get SubtaskTypeCategory");
		ObjectMapper mapper = new ObjectMapper();
		SubtaskTypeCategoryAjaxResponse response = new SubtaskTypeCategoryAjaxResponse();
		response.setResponseStatus(ResponseStatus.SUCCESS);
		try {
			int numericValue = 0;
			SubtaskTypeCategory subtaskTypeCategory = new SubtaskTypeCategory();
			try {
				numericValue = Integer.parseInt(subtaskTypeCategoryIdStr);
				subtaskTypeCategory.setSubtaskTypeCategoryId(numericValue);
			} catch (Exception e) {
				String errorMessage = this.propertyMessageBO.getMessageFromProperties("subtaskTypeCategory.form.error.subtaskTypeCategoryid-nan", "Error");
				response.setResponseStatus(ResponseStatus.ERROR);
				response.setErrorMessage(errorMessage);
			}
			
			if (ResponseStatus.SUCCESS.equals(response.getResponseStatus())) {
				try {
					subtaskTypeCategory = this.subtaskTypeCategoryBO.getSubtaskTypeCategoryById(numericValue);
					response.setSubtaskTypeCategory(subtaskTypeCategory);
				} catch (Exception e) {
					String errorMessage = this.propertyMessageBO.getMessageFromProperties("subtaskTypeCategory.form.error.subtaskTypeCategory-not-exist", "Error");
					response.setResponseStatus(ResponseStatus.ERROR);
					response.setErrorMessage(errorMessage);					
				}				
			}
			return mapper.writeValueAsString(response);
		} catch (JsonProcessingException e) {
			log.error("Error on ajax call to get the SubtaskTypeCategory List", e);
			throw new PesWebException(e);
		}
	}
	
	@RequestMapping("ajax/getSubtaskTypeList")
	public @ResponseBody String getSubtaskTypeList() throws PesWebException{
		log.debug("Ajax method to get SubtaskType");
		ObjectMapper mapper = new ObjectMapper();
		try {
			Collection<SubtaskType> subtaskTypeList = this.subtaskTypeBo.listSubtaskType();
			return mapper.writeValueAsString(subtaskTypeList);
		} catch (PesWebException e) {
			log.error("Error getting the subtaskType List", e);
			throw e;
		} catch (JsonProcessingException e) {
			log.error("Error on ajax call to get the subtaskType", e);
			throw new PesWebException(e);
		}
	}

	@RequestMapping("ajax/getSubtaskTypeByCategoryId")
	public @ResponseBody String getSubtaskTypeByCategoryId(@RequestParam(name = "subtaskTypeCategoryId") String subtaskTypeCategoryIdStr) throws PesWebException{
		log.debug("Ajax method to get getSubtaskTypeByCategoryId");
		ObjectMapper mapper = new ObjectMapper();
		Collection<SubtaskType> subtaskTypeList = new ArrayList<>();
		try {
			int numericValue = 0;	
			numericValue = Integer.parseInt(subtaskTypeCategoryIdStr);
			subtaskTypeList.addAll(this.subtaskTypeBo.listSubtaskTypeByCategoryId(numericValue));
			return mapper.writeValueAsString(subtaskTypeList);
		} catch (PesWebException e) {
			log.error("Error getting the subtaskType List", e);
			throw e;
		} catch (JsonProcessingException e) {
			log.error("Error on ajax call to get the subtaskType", e);
			throw new PesWebException(e);
		}
	}
	
	@RequestMapping("ajax/getSubtaskTypeById")
	public @ResponseBody String getSubtaskTypeById(@RequestParam String subtaskTypeIdStr) throws PesWebException{
		log.debug("Ajax method to get SubtaskType");
		ObjectMapper mapper = new ObjectMapper();
		SubtaskTypeAjaxResponse response = new SubtaskTypeAjaxResponse();
		response.setResponseStatus(ResponseStatus.SUCCESS);
		try {
			int numericValue = 0;
			SubtaskType subtaskType = new SubtaskType();
			try {
				numericValue = Integer.parseInt(subtaskTypeIdStr);
				subtaskType.setSubtaskTypeId(numericValue);
			} catch (Exception e) {
				String errorMessage = this.propertyMessageBO.getMessageFromProperties("subtaskType.form.error.subtaskTypeid-nan", "Error");
				response.setResponseStatus(ResponseStatus.ERROR);
				response.setErrorMessage(errorMessage);
			}
			
			if (ResponseStatus.SUCCESS.equals(response.getResponseStatus())) {
				try {
					subtaskType = this.subtaskTypeBo.getSubtaskTypeById(numericValue);
					response.setSubtaskType(subtaskType);
				} catch (Exception e) {
					String errorMessage = this.propertyMessageBO.getMessageFromProperties("subtaskType.form.error.subtaskType-not-exist", "Error");
					response.setResponseStatus(ResponseStatus.ERROR);
					response.setErrorMessage(errorMessage);					
				}				
			}
			return mapper.writeValueAsString(response);
		} catch (JsonProcessingException e) {
			log.error("Error on ajax call to get the subtaskType", e);
			throw new PesWebException(e);
		}
	}
	
	@RequestMapping("ajax/getSubtaskList")
	public @ResponseBody String getSubtaskList() throws PesWebException{
		log.debug("Ajax method to get Subtask");
		ObjectMapper mapper = new ObjectMapper();
		try {
			Collection<Subtask> subtaskList = this.subtaskBo.listSubtask();
			return mapper.writeValueAsString(subtaskList);
		} catch (PesWebException e) {
			log.error("Error getting the subtask List", e);
			throw e;
		} catch (JsonProcessingException e) {
			log.error("Error on ajax call to get the subtask", e);
			throw new PesWebException(e);
		}
	}

	@RequestMapping("ajax/getSubtaskById")
	public @ResponseBody String getSubtaskById(@RequestParam String subtaskIdStr) throws PesWebException{
		log.debug("Ajax method to get Subtask");
		ObjectMapper mapper = new ObjectMapper();
		SubtaskAjaxResponse response = new SubtaskAjaxResponse();
		response.setResponseStatus(ResponseStatus.SUCCESS);
		try {
			int numericValue = 0;
			Subtask subtask = new Subtask();
			try {
				numericValue = Integer.parseInt(subtaskIdStr);
				subtask.setSubtaskId(numericValue);
			} catch (Exception e) {
				String errorMessage = this.propertyMessageBO.getMessageFromProperties("subtask.form.error.subtaskid-nan", "Error");
				response.setResponseStatus(ResponseStatus.ERROR);
				response.setErrorMessage(errorMessage);
			}
			
			if (ResponseStatus.SUCCESS.equals(response.getResponseStatus())) {
				try {
					subtask = this.subtaskBo.getSubtaskById(numericValue);
					response.setSubtask(subtask);
				} catch (Exception e) {
					String errorMessage = this.propertyMessageBO.getMessageFromProperties("subtask.form.error.subtask-not-exist", "Error");
					response.setResponseStatus(ResponseStatus.ERROR);
					response.setErrorMessage(errorMessage);					
				}				
			}
			return mapper.writeValueAsString(response);
		} catch (JsonProcessingException e) {
			log.error("Error on ajax call to get the subtask", e);
			throw new PesWebException(e);
		}
	}

}
