package com.pes.web.controller;

import static com.pes.web.model.constant.PesWebConstants.SUBTASK_ERROR_CREATE;
import static com.pes.web.model.constant.PesWebConstants.SUBTASK_ERROR_DELETE;
import static com.pes.web.model.constant.PesWebConstants.SUBTASK_ERROR_UPDATE;
import static com.pes.web.model.constant.PesWebConstants.SUBTASK_SUCCESS_CREATE;
import static com.pes.web.model.constant.PesWebConstants.SUBTASK_SUCCESS_DELETE;
import static com.pes.web.model.constant.PesWebConstants.SUBTASK_SUCCESS_UPDATE;
import static com.pes.web.model.constant.PesWebConstants.VIEW_SUBTASK_LIST;

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

import com.pes.web.bo.SubtaskBO;
import com.pes.web.bo.SubtaskTypeBO;
import com.pes.web.bo.SubtaskTypeCategoryBO;
import com.pes.web.form.SubtaskForm;
import com.pes.web.model.SubtaskType;
import com.pes.web.model.SubtaskTypeCategory;
import com.pes.web.model.constant.FormAction;
import com.pes.web.model.exception.PesWebException;
import com.pes.web.validator.SubtaskValidator;

@Controller
public class SubtaskController extends BasicController {
	
	private static Log log = LogFactory.getLog(SubtaskController.class);
	
	@Autowired
	private SubtaskBO subtaskBO;

	@Autowired
	private SubtaskTypeCategoryBO subtaskTypeCategoryBO;
	
	@Autowired
	private SubtaskValidator subtaskValidator;	
		
	@RequestMapping(value="/**/web/listSubtask.do", method = RequestMethod.GET)
	public ModelAndView listSubtaskGet(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("command") SubtaskForm command) throws Exception {
		log.info("### START listSubtask.do ###");
		ModelAndView listModelView = this.nextModelView(VIEW_SUBTASK_LIST, command, FormAction.LIST);
		log.info("### END listSubtask.do ###");
		return listModelView;
	}
	
	@RequestMapping(value="/**/web/listSubtask.do", method = RequestMethod.POST)
	public ModelAndView listSubtaskPost(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("command") SubtaskForm command, BindingResult result) throws Exception {
		log.info("### START listSubtask.do POST###");
		ModelAndView nextView = this.postMethodProcess(command, result);
		log.info("### END listSubtask.do POST###");
		return nextView;
	}
	
	/**
	 * Process the request according the action, validates the data and process the information into the database
	 * @param command
	 * @param result
	 * @return
	 */
	private ModelAndView postMethodProcess(SubtaskForm command, BindingResult result) {
		boolean errorOnPost=false;
		String nextView = VIEW_SUBTASK_LIST;
		String message = "";
		command.getMessageAndRedirect().cleanMsgValue(); //Cleans message and redirect info
		this.subtaskValidator.validate(command, result); //validates the form
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
					logEntryOnError = "Error trying to insert new Subtask Page";
					errorPropertyKey = SUBTASK_ERROR_CREATE;
					successPropertyKey = SUBTASK_SUCCESS_CREATE;
					this.subtaskBO.save(command.getValidatedSubtask());
				}		
				//Process update
				if (formAction == FormAction.UPDATE){
					logEntryOnError = "Error trying to update the Subtask Page";
					errorPropertyKey = SUBTASK_ERROR_UPDATE;
					successPropertyKey = SUBTASK_SUCCESS_UPDATE;
					this.subtaskBO.update(command.getValidatedSubtask()); 
				}
				//Process Delete
				if (formAction == FormAction.DELETE){
					logEntryOnError = "Error trying to delete the Subtask Page";
					errorPropertyKey = SUBTASK_ERROR_DELETE;
					successPropertyKey = SUBTASK_SUCCESS_DELETE;
					this.subtaskBO.delete(command.getValidatedSubtask()); 
				}
			}catch(Exception e){
				log.error(logEntryOnError, e);
				errorOnPost = true;
				this.subtaskValidator.setErrorAtException(result, errorPropertyKey);
			}

			if (!errorOnPost){
				command.setSubtaskId("");
				command.setDescription("");
				command.setSubtaskTypeId(0);
				command.setAutoCalculation("false");
				command.setReferenceMode("");
				command.setTime("");

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
	private ModelAndView nextModelView(String viewName, SubtaskForm command, FormAction actionType){
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
		
		List<SubtaskType> subtaskTypeList;

		subtaskTypeList = new ArrayList<SubtaskType>();
		SubtaskType selectLabel = new SubtaskType();
		selectLabel.setSubtaskTypeId(0);
		selectLabel.setDescription("--- Select ---");
		subtaskTypeList.add(selectLabel);

		formModelView.addObject("subtaskypeCategoryList", subtaskypeCategoryList);
		formModelView.addObject("subtaskTypeList", subtaskTypeList);
	}
}

