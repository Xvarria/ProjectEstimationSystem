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
import com.pes.web.bo.ProjectBO;
import com.pes.web.bo.SubtaskBO;
import com.pes.web.bo.SubtaskTypeBO;
import com.pes.web.bo.SubtaskTypeCategoryBO;
import com.pes.web.bo.TaskBO;
import com.pes.web.form.ajax.ComplexityAjaxResponse;
import com.pes.web.form.ajax.ProjectAjaxResponse;
import com.pes.web.form.ajax.SubtaskAjaxResponse;
import com.pes.web.form.ajax.SubtaskTypeAjaxResponse;
import com.pes.web.form.ajax.SubtaskTypeCategoryAjaxResponse;
import com.pes.web.form.ajax.TaskAjaxResponse;
import com.pes.web.model.Complexity;
import com.pes.web.model.Project;
import com.pes.web.model.Subtask;
import com.pes.web.model.SubtaskType;
import com.pes.web.model.SubtaskTypeCategory;
import com.pes.web.model.Task;
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

	@Autowired
	private ProjectBO projectBo;


	@Autowired
	private TaskBO taskBo;
	
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
	public @ResponseBody String getSubtaskList(@RequestParam(name="taskIdStr") String taskIdStr) throws PesWebException{
		log.debug("Ajax method to get Subtask");
		ObjectMapper mapper = new ObjectMapper();
		try {
			Collection<Subtask> subtaskList = this.subtaskBo.listSubtask(Integer.parseInt(taskIdStr));
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
	
	@RequestMapping("ajax/getProjectList")
	public @ResponseBody String ajaxProjectList() throws PesWebException{
		log.debug("Ajax method to get Project");
		ObjectMapper mapper = new ObjectMapper();
		try {
			Collection<Project> projectList = this.projectBo.listProject();
			return mapper.writeValueAsString(projectList);
		} catch (PesWebException e) {
			log.error("Error getting the project List", e);
			throw e;
		} catch (JsonProcessingException e) {
			log.error("Error on ajax call to get the project", e);
			throw new PesWebException(e);
		}
	}

	@RequestMapping("ajax/getProjectById")
	public @ResponseBody String ajaxProjectList(@RequestParam String projectIdStr) throws PesWebException{
		log.debug("Ajax method to get Project");
		ObjectMapper mapper = new ObjectMapper();
		ProjectAjaxResponse response = new ProjectAjaxResponse();
		response.setResponseStatus(ResponseStatus.SUCCESS);
		try {
			int numericValue = 0;
			Project project = new Project();
			try {
				numericValue = Integer.parseInt(projectIdStr);
				project.setProjectId(numericValue);
			} catch (Exception e) {
				String errorMessage = this.propertyMessageBO.getMessageFromProperties("project.form.error.projectid-nan", "Error");
				response.setResponseStatus(ResponseStatus.ERROR);
				response.setErrorMessage(errorMessage);
			}
			
			if (ResponseStatus.SUCCESS.equals(response.getResponseStatus())) {
				try {
					project = this.projectBo.getProjectById(numericValue);
					response.setProject(project);
				} catch (Exception e) {
					String errorMessage = this.propertyMessageBO.getMessageFromProperties("project.form.error.project-not-exist", "Error");
					response.setResponseStatus(ResponseStatus.ERROR);
					response.setErrorMessage(errorMessage);					
				}				
			}
			return mapper.writeValueAsString(response);
		} catch (JsonProcessingException e) {
			log.error("Error on ajax call to get the project", e);
			throw new PesWebException(e);
		}
	}
	
	@RequestMapping("ajax/getTaskList")
	public @ResponseBody String getTaskList(@RequestParam(name="projectIdStr") String projectIdStr) throws PesWebException{
		log.debug("Ajax method to get Task");
		ObjectMapper mapper = new ObjectMapper();
		try {
			Collection<Task> taskList = this.taskBo.listTaskByProjectId(Integer.parseInt(projectIdStr));
			return mapper.writeValueAsString(taskList);
		} catch (PesWebException e) {
			log.error("Error getting the task List", e);
			throw e;
		} catch (JsonProcessingException e) {
			log.error("Error on ajax call to get the task", e);
			throw new PesWebException(e);
		}
	}

	@RequestMapping("ajax/getTaskById")
	public @ResponseBody String getTaskById(@RequestParam String taskIdStr) throws PesWebException{
		log.debug("Ajax method to get Task");
		ObjectMapper mapper = new ObjectMapper();
		TaskAjaxResponse response = new TaskAjaxResponse();
		response.setResponseStatus(ResponseStatus.SUCCESS);
		try {
			int numericValue = 0;
			Task task = new Task();
			try {
				numericValue = Integer.parseInt(taskIdStr);
				task.setTaskId(numericValue);
			} catch (Exception e) {
				String errorMessage = this.propertyMessageBO.getMessageFromProperties("task.form.error.taskid-nan", "Error");
				response.setResponseStatus(ResponseStatus.ERROR);
				response.setErrorMessage(errorMessage);
			}
			
			if (ResponseStatus.SUCCESS.equals(response.getResponseStatus())) {
				try {
					task = this.taskBo.getTaskById(numericValue);
					response.setTask(task);
				} catch (Exception e) {
					String errorMessage = this.propertyMessageBO.getMessageFromProperties("task.form.error.task-not-exist", "Error");
					response.setResponseStatus(ResponseStatus.ERROR);
					response.setErrorMessage(errorMessage);					
				}				
			}
			return mapper.writeValueAsString(response);
		} catch (JsonProcessingException e) {
			log.error("Error on ajax call to get the task", e);
			throw new PesWebException(e);
		}
	}
}
