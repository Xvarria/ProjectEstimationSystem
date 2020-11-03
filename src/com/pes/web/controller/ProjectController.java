package com.pes.web.controller;

import static com.pes.web.model.constant.PesWebConstants.PROJECT_ERROR_CREATE;
import static com.pes.web.model.constant.PesWebConstants.PROJECT_ERROR_DELETE;
import static com.pes.web.model.constant.PesWebConstants.PROJECT_ERROR_UPDATE;
import static com.pes.web.model.constant.PesWebConstants.PROJECT_SUCCESS_CREATE;
import static com.pes.web.model.constant.PesWebConstants.PROJECT_SUCCESS_DELETE;
import static com.pes.web.model.constant.PesWebConstants.PROJECT_SUCCESS_UPDATE;
import static com.pes.web.model.constant.PesWebConstants.VIEW_PROJECT_LIST;

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
import org.springframework.web.servlet.ModelAndView;

import com.pes.web.bo.ProjectBO;
import com.pes.web.form.ProjectForm;
import com.pes.web.model.constant.FormAction;
import com.pes.web.validator.ProjectValidator;

@Controller
public class ProjectController extends BasicController {
	
	private static Log log = LogFactory.getLog(ProjectController.class);
	
	@Autowired
	private ProjectBO projectBO;
	
	@Autowired
	private ProjectValidator projectValidator;	
		
	@RequestMapping(value="/**/web/listProject.do", method = RequestMethod.GET)
	public ModelAndView listProjectGet(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("command") ProjectForm command) throws Exception {
		log.info("### START listProject.do ###");
		ModelAndView listModelView = this.nextModelView(VIEW_PROJECT_LIST, command, FormAction.LIST);
		log.info("### END listProject.do ###");
		return listModelView;
	}
	
	@RequestMapping(value="/**/web/listProject.do", method = RequestMethod.POST)
	public ModelAndView listProjectPost(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("command") ProjectForm command, BindingResult result) throws Exception {
		log.info("### START listProject.do POST###");
		ModelAndView nextView = this.postMethodProcess(command, result);
		log.info("### END listProject.do POST###");
		return nextView;
	}
	
	/**
	 * Process the request according the action, validates the data and process the information into the database
	 * @param command
	 * @param result
	 * @return
	 */
	private ModelAndView postMethodProcess(ProjectForm command, BindingResult result) {
		boolean errorOnPost=false;
		String nextView = VIEW_PROJECT_LIST;
		String message = "";
		command.getMessageAndRedirect().cleanMsgValue(); //Cleans message and redirect info
		this.projectValidator.validate(command, result); //validates the form
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
					logEntryOnError = "Error trying to insert new Project Page";
					errorPropertyKey = PROJECT_ERROR_CREATE;
					successPropertyKey = PROJECT_SUCCESS_CREATE;
					this.projectBO.save(command.getValidatedProject());
				}		
				//Process update
				if (formAction == FormAction.UPDATE){
					logEntryOnError = "Error trying to update the Project Page";
					errorPropertyKey = PROJECT_ERROR_UPDATE;
					successPropertyKey = PROJECT_SUCCESS_UPDATE;
					this.projectBO.update(command.getValidatedProject()); 
				}
				//Process Delete
				if (formAction == FormAction.DELETE){
					logEntryOnError = "Error trying to delete the Project Page";
					errorPropertyKey = PROJECT_ERROR_DELETE;
					successPropertyKey = PROJECT_SUCCESS_DELETE;
					this.projectBO.delete(command.getValidatedProject()); 
				}
			}catch(Exception e){
				log.error(logEntryOnError, e);
				errorOnPost = true;
				this.projectValidator.setErrorAtException(result, errorPropertyKey);
			}

			if (!errorOnPost){
				command.setProjectId("");
				command.setName("");
				command.setProjectNumber("");
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
	private ModelAndView nextModelView(String viewName, ProjectForm command, FormAction actionType){
		ModelAndView formModelView = new ModelAndView(viewName);		
		command.setAction(actionType.toString());
		formModelView.setViewName(viewName);
		formModelView.addObject("command", command);
		return formModelView;
	}
}

