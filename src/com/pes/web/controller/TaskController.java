package com.pes.web.controller;

import static com.pes.web.model.constant.PesWebConstants.TASK_ERROR_CREATE;
import static com.pes.web.model.constant.PesWebConstants.TASK_ERROR_DELETE;
import static com.pes.web.model.constant.PesWebConstants.TASK_ERROR_UPDATE;
import static com.pes.web.model.constant.PesWebConstants.TASK_SUCCESS_CREATE;
import static com.pes.web.model.constant.PesWebConstants.TASK_SUCCESS_DELETE;
import static com.pes.web.model.constant.PesWebConstants.TASK_SUCCESS_UPDATE;
import static com.pes.web.model.constant.PesWebConstants.VIEW_TASK_LIST;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pes.web.bo.ProjectBO;
import com.pes.web.bo.TaskBO;
import com.pes.web.form.TaskForm;
import com.pes.web.model.Project;
import com.pes.web.model.constant.FormAction;
import com.pes.web.validator.TaskValidator;

@Controller
public class TaskController extends BasicController {
	
	private static Log log = LogFactory.getLog(TaskController.class);
	
	@Autowired
	private TaskBO taskBO;

	@Autowired
	private ProjectBO projectBO;

	@Autowired
	private TaskValidator taskValidator;	
		
	@RequestMapping(value="/**/web/listTask.do", method = RequestMethod.GET)
	public ModelAndView listTaskGet(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("command") TaskForm command, @RequestParam("projectId") String projectIdStr) throws Exception {
		log.info("### START listTask.do ###");
		int projectId = 0;
		try {
			projectId = Integer.parseInt(projectIdStr);
		}catch (Exception e) {
			log.error("Project Id not found",e);
		}
		Project project = projectBO.getProjectById(projectId);
		command.setProject(project);
		command.setProjectId(projectId);
		ModelAndView listModelView = this.nextModelView(VIEW_TASK_LIST, command, FormAction.LIST);
		log.info("### END listTask.do ###");
		return listModelView;
	}
	
	@RequestMapping(value="/**/web/listTask.do", method = RequestMethod.POST)
	public ModelAndView listTaskPost(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("command") TaskForm command, BindingResult result) throws Exception {
		log.info("### START listTask.do POST###");
		ModelAndView nextView = this.postMethodProcess(command, result);
		log.info("### END listTask.do POST###");
		return nextView;
	}
	
	/**
	 * Process the request according the action, validates the data and process the information into the database
	 * @param command
	 * @param result
	 * @return
	 */
	private ModelAndView postMethodProcess(TaskForm command, BindingResult result) {
		boolean errorOnPost=false;
		String nextView = VIEW_TASK_LIST;
		String message = "";
		command.getMessageAndRedirect().cleanMsgValue(); //Cleans message and redirect info
		this.taskValidator.validate(command, result); //validates the form
		if (result.hasErrors()) {
			errorOnPost = true;
		} else {
			String logEntryOnError = "";
			String errorPropertyKey = "";
			String successPropertyKey = "";
			FormAction formAction = command.getFormAction();
			try {				
				//Process insert
				if (formAction == FormAction.CREATE){
					logEntryOnError = "Error trying to insert new Task Page";
					errorPropertyKey = TASK_ERROR_CREATE;
					successPropertyKey = TASK_SUCCESS_CREATE;
					this.taskBO.save(command.getValidatedTask());
				}		
				//Process update
				if (formAction == FormAction.UPDATE){
					logEntryOnError = "Error trying to update the Task Page";
					errorPropertyKey = TASK_ERROR_UPDATE;
					successPropertyKey = TASK_SUCCESS_UPDATE;
					this.taskBO.update(command.getValidatedTask()); 
				}
				//Process Delete
				if (formAction == FormAction.DELETE){
					logEntryOnError = "Error trying to delete the Task Page";
					errorPropertyKey = TASK_ERROR_DELETE;
					successPropertyKey = TASK_SUCCESS_DELETE;
					this.taskBO.delete(command.getValidatedTask()); 
				}
			}catch(Exception e){
				log.error(logEntryOnError, e);
				errorOnPost = true;
				this.taskValidator.setErrorAtException(result, errorPropertyKey);
			}

			if (!errorOnPost){
				command.setTaskId("");
				command.setCode("");
				command.setName("");
				command.setSequence(0);
				message = this.propertyMessageBO.getMessageFromProperties(successPropertyKey);
				command.getMessageAndRedirect().setSuccessMessage(message);//Set success message
			}			
		}			
		
		ModelAndView formModelView = new ModelAndView();		
		formModelView.setViewName(nextView);
		formModelView.addObject("command", command);
		return formModelView;		
	}
	
	/**
	 * Redirect to the indicated view and loads the command
	 * @param viewName
	 * @param command
	 * @param actionType
	 * @return
	 */
	private ModelAndView nextModelView(String viewName, TaskForm command, FormAction actionType){
		ModelAndView formModelView = new ModelAndView(viewName);		
		command.setAction(actionType.toString());
		formModelView.setViewName(viewName);
		formModelView.addObject("command", command);
		return formModelView;
	}
}

