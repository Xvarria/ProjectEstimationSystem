package com.pes.web.controller;

import static com.pes.web.model.constant.PesWebConstants.SUBTASK_TYPE_ERROR_CREATE;
import static com.pes.web.model.constant.PesWebConstants.SUBTASK_TYPE_ERROR_DELETE;
import static com.pes.web.model.constant.PesWebConstants.SUBTASK_TYPE_ERROR_UPDATE;
import static com.pes.web.model.constant.PesWebConstants.SUBTASK_TYPE_SUCCESS_CREATE;
import static com.pes.web.model.constant.PesWebConstants.SUBTASK_TYPE_SUCCESS_DELETE;
import static com.pes.web.model.constant.PesWebConstants.SUBTASK_TYPE_SUCCESS_UPDATE;
import static com.pes.web.model.constant.PesWebConstants.VIEW_SUBTASK_TYPE_LIST;

import java.util.ArrayList;
import java.util.List;

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

import com.pes.web.bo.SubtaskTypeBO;
import com.pes.web.bo.SubtaskTypeCategoryBO;
import com.pes.web.form.SubtaskTypeForm;
import com.pes.web.model.SubtaskTypeCategory;
import com.pes.web.model.constant.FormAction;
import com.pes.web.model.exception.PesWebException;
import com.pes.web.validator.SubtaskTypeValidator;

@Controller
public class SubtaskTypeController extends BasicController {
	
	private static Log log = LogFactory.getLog(SubtaskTypeController.class);
	
	@Autowired
	private SubtaskTypeBO subtaskTypeBO;

	@Autowired
	private SubtaskTypeCategoryBO subtaskTypeCategoryBO;
	
	@Autowired
	private SubtaskTypeValidator subtaskTypeValidator;	
		
	@RequestMapping(value="/**/web/listSubtaskType.do", method = RequestMethod.GET)
	public ModelAndView listSubtaskTypeGet(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("command") SubtaskTypeForm command) throws Exception {
		log.info("### START listarSubtaskType.do ###");
		ModelAndView listModelView = this.nextModelView(VIEW_SUBTASK_TYPE_LIST, command, FormAction.LIST);
		log.info("### END listarSubtaskType.do ###");
		return listModelView;
	}
	
	@RequestMapping(value="/**/web/listSubtaskType.do", method = RequestMethod.POST)
	public ModelAndView listSubtaskTypePost(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("command") SubtaskTypeForm command, BindingResult result) throws Exception {
		log.info("### START eliminarSubtaskType.do POST###");
		ModelAndView nextView = this.postMethodProcess(command, result);
		log.info("### END eliminarSubtaskType.do POST###");
		return nextView;
	}
	
	/**
	 * Process the request according the action, validates the data and process the information into the database
	 * @param command
	 * @param result
	 * @return
	 */
	private ModelAndView postMethodProcess(SubtaskTypeForm command, BindingResult result) {
		boolean errorOnPost=false;
		String nextView = VIEW_SUBTASK_TYPE_LIST;
		String message = "";
		command.getMessageAndRedirect().cleanMsgValue(); //Cleans message and redirect info
		this.subtaskTypeValidator.validate(command, result); //validates the form
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
					logEntryOnError = "Error trying to insert new SubtaskType Page";
					errorPropertyKey = SUBTASK_TYPE_ERROR_CREATE;
					successPropertyKey = SUBTASK_TYPE_SUCCESS_CREATE;
					this.subtaskTypeBO.save(command.getValidatedSubtaskType());
				}		
				//Process update
				if (formAction == FormAction.UPDATE){
					logEntryOnError = "Error trying to update the SubtaskType Page";
					errorPropertyKey = SUBTASK_TYPE_ERROR_UPDATE;
					successPropertyKey = SUBTASK_TYPE_SUCCESS_UPDATE;
					this.subtaskTypeBO.update(command.getValidatedSubtaskType()); 
				}
				//Process Delete
				if (formAction == FormAction.DELETE){
					logEntryOnError = "Error trying to delete the SubtaskType Page";
					errorPropertyKey = SUBTASK_TYPE_ERROR_DELETE;
					successPropertyKey = SUBTASK_TYPE_SUCCESS_DELETE;
					this.subtaskTypeBO.delete(command.getValidatedSubtaskType()); 
				}
			}catch(Exception e){
				log.error(logEntryOnError, e);
				errorOnPost = true;
				this.subtaskTypeValidator.setErrorAtException(result, errorPropertyKey);
			}

			if (!errorOnPost){
				command.setCalculation("");
				command.setSubtaskCategoryTypeId(0);
				command.setSubtaskTypeId("");
				command.setDescription("");
				message = this.propertyMessageBO.getMessageFromProperties(successPropertyKey);
				command.getMessageAndRedirect().setSuccessMessage(message);//Set success message
			}			
		}			
		
		ModelAndView formModelView = new ModelAndView();		
		formModelView.setViewName(nextView);
		formModelView.addObject("command", command);
		setOptionsOnCommand(formModelView);
		return formModelView;		
	}
	
	/**
	 * Redirect to the indicated view and loads the command
	 * @param viewName
	 * @param command
	 * @param actionType
	 * @return
	 */
	private ModelAndView nextModelView(String viewName, SubtaskTypeForm command, FormAction actionType){
		ModelAndView formModelView = new ModelAndView(viewName);		
		command.setAction(actionType.toString());
		formModelView.setViewName(viewName);
		formModelView.addObject("command", command);
		setOptionsOnCommand(formModelView);
		return formModelView;
	}

	private void setOptionsOnCommand(ModelAndView formModelView) {
		List<SubtaskTypeCategory> subtaskypeCategoryList;
		try {
			subtaskypeCategoryList = new ArrayList<SubtaskTypeCategory>();
			SubtaskTypeCategory selectLabel = new SubtaskTypeCategory();
			selectLabel.setSubtaskTypeCategoryId(0);
			selectLabel.setDescription("--- Select ---");
			subtaskypeCategoryList.add(selectLabel);
			subtaskypeCategoryList.addAll(this.subtaskTypeCategoryBO.listSubtaskTypeCategory());
		} catch (PesWebException e) {
			subtaskypeCategoryList = new ArrayList<>();
		}
		formModelView.addObject("subtaskypeCategoryList", subtaskypeCategoryList);
	}
}
