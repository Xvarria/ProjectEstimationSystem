package com.pes.web.controller;

import static com.pes.web.model.constant.PesWebConstants.SUBTASK_TYPE_CATEGORY_ERROR_CREATE;
import static com.pes.web.model.constant.PesWebConstants.SUBTASK_TYPE_CATEGORY_ERROR_DELETE;
import static com.pes.web.model.constant.PesWebConstants.SUBTASK_TYPE_CATEGORY_ERROR_UPDATE;
import static com.pes.web.model.constant.PesWebConstants.SUBTASK_TYPE_CATEGORY_SUCCESS_CREATE;
import static com.pes.web.model.constant.PesWebConstants.SUBTASK_TYPE_CATEGORY_SUCCESS_DELETE;
import static com.pes.web.model.constant.PesWebConstants.SUBTASK_TYPE_CATEGORY_SUCCESS_UPDATE;
import static com.pes.web.model.constant.PesWebConstants.VIEW_SUBTASK_TYPE_CATEGORY_LIST;

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

import com.pes.web.bo.SubtaskTypeCategoryBO;
import com.pes.web.form.SubtaskTypeCategoryForm;
import com.pes.web.model.constant.FormAction;
import com.pes.web.validator.SubtaskTypeCategoryValidator;

@Controller
public class SubtaskTypeCatgoryController extends BasicController {
	
	private static Log log = LogFactory.getLog(SubtaskTypeCatgoryController.class);
	
	@Autowired
	private SubtaskTypeCategoryBO subtaskCategoryTypeBO;
	
	@Autowired
	private SubtaskTypeCategoryValidator subtaskCategoryTypeValidator;	
		
	@RequestMapping(value="/**/web/listSubtaskTypeCategory.do", method = RequestMethod.GET)
	public ModelAndView listSubtaskTypeCategoryGet(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("command") SubtaskTypeCategoryForm command) throws Exception {
		log.info("### START listSubtaskTypeCategory.do ###");
		ModelAndView listModelView = this.nextModelView(VIEW_SUBTASK_TYPE_CATEGORY_LIST, command, FormAction.LIST);
		log.info("### END listSubtaskTypeCategory.do ###");
		return listModelView;
	}
	
	@RequestMapping(value="/**/web/listSubtaskTypeCategory.do", method = RequestMethod.POST)
	public String listSubtaskTypeCategoryPost(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("command") SubtaskTypeCategoryForm command, BindingResult result) throws Exception {
		log.info("### START listSubtaskTypeCategory.do POST###");
		String nextView = this.postMethodProcess(command, result);
		log.info("### END listSubtaskTypeCategory.do POST###");
		return nextView;
	}
	
	/**
	 * Process the request according the action, validates the data and process the information into the database
	 * @param command
	 * @param result
	 * @return
	 */
	private String postMethodProcess(SubtaskTypeCategoryForm command, BindingResult result) {
		boolean errorOnPost=false;
		String nextView = VIEW_SUBTASK_TYPE_CATEGORY_LIST;
		String message = "";
		command.getMessageAndRedirect().cleanMsgValue(); //Cleans message and redirect info
		this.subtaskCategoryTypeValidator.validate(command, result); //validates the form
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
					logEntryOnError = "Error trying to insert new SubtaskTypeCategory Page";
					errorPropertyKey = SUBTASK_TYPE_CATEGORY_ERROR_CREATE;
					successPropertyKey = SUBTASK_TYPE_CATEGORY_SUCCESS_CREATE;
					this.subtaskCategoryTypeBO.save(command.getValidatedSubtaskTypeCategory());
				}		
				//Process update
				if (formAction == FormAction.UPDATE){
					logEntryOnError = "Error trying to update the SubtaskTypeCategory Page";
					errorPropertyKey = SUBTASK_TYPE_CATEGORY_ERROR_UPDATE;
					successPropertyKey = SUBTASK_TYPE_CATEGORY_SUCCESS_UPDATE;
					this.subtaskCategoryTypeBO.update(command.getValidatedSubtaskTypeCategory()); 
				}
				//Process Delete
				if (formAction == FormAction.DELETE){
					logEntryOnError = "Error trying to delete the SubtaskTypeCategory Page";
					errorPropertyKey = SUBTASK_TYPE_CATEGORY_ERROR_DELETE;
					successPropertyKey = SUBTASK_TYPE_CATEGORY_SUCCESS_DELETE;
					this.subtaskCategoryTypeBO.delete(command.getValidatedSubtaskTypeCategory()); 
				}
			}catch(Exception e){
				log.error(logEntryOnError, e);
				errorOnPost = true;
				this.subtaskCategoryTypeValidator.setErrorAtException(result, errorPropertyKey);
			}

			if (!errorOnPost){
				command.setSubtaskTypeCategoryId("");
				command.setDescription("");
				message = this.propertyMessageBO.getMessageFromProperties(successPropertyKey);
				command.getMessageAndRedirect().setSuccessMessage(message);//Set success message
			}			
		}			
		return nextView;
	}
	
	/**
	 * Redirect to the indicated view and loads the command
	 * @param viewName
	 * @param command
	 * @param actionType
	 * @return
	 */
	private ModelAndView nextModelView(String viewName, SubtaskTypeCategoryForm command, FormAction actionType){
		ModelAndView formModelView = new ModelAndView(viewName);		
		command.setAction(actionType.toString());
	
		formModelView.setViewName(viewName);
		formModelView.addObject("command", command);
		return formModelView;
	}
}

