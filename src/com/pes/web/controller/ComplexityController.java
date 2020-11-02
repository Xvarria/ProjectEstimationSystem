package com.pes.web.controller;

import static com.pes.web.model.constant.PesWebConstants.COMPLEXITY_ERROR_CREATE;
import static com.pes.web.model.constant.PesWebConstants.COMPLEXITY_ERROR_DELETE;
import static com.pes.web.model.constant.PesWebConstants.COMPLEXITY_ERROR_UPDATE;
import static com.pes.web.model.constant.PesWebConstants.COMPLEXITY_SUCCESS_CREATE;
import static com.pes.web.model.constant.PesWebConstants.COMPLEXITY_SUCCESS_DELETE;
import static com.pes.web.model.constant.PesWebConstants.COMPLEXITY_SUCCESS_UPDATE;
import static com.pes.web.model.constant.PesWebConstants.VIEW_COMPLEXITY_LIST;

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

import com.pes.web.bo.ComplexityBO;
import com.pes.web.form.ComplexityForm;
import com.pes.web.model.constant.FormAction;
import com.pes.web.validator.ComplexityValidator;

@Controller
public class ComplexityController extends BasicController {
	
	private static Log log = LogFactory.getLog(ComplexityController.class);
	
	@Autowired
	private ComplexityBO complexityBO;
	
	@Autowired
	private ComplexityValidator complexityValidator;	
		
	@RequestMapping(value="/**/web/listComplexity.do", method = RequestMethod.GET)
	public ModelAndView listComplexityGet(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("command") ComplexityForm command) throws Exception {
		log.info("### START listComplexity.do ###");
		ModelAndView listModelView = this.nextModelView(VIEW_COMPLEXITY_LIST, command, FormAction.LIST);
		log.info("### END listComplexity.do ###");
		return listModelView;
	}
	
	@RequestMapping(value="/**/web/listComplexity.do", method = RequestMethod.POST)
	public String listComplexityPost(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("command") ComplexityForm command, BindingResult result) throws Exception {
		log.info("### START listComplexity.do POST###");
		String nextView = this.postMethodProcess(command, result);
		log.info("### END listComplexity.do POST###");
		return nextView;
	}
	
	/**
	 * Process the request according the action, validates the data and process the information into the database
	 * @param command
	 * @param result
	 * @return
	 */
	private String postMethodProcess(ComplexityForm command, BindingResult result) {
		boolean errorOnPost=false;
		String nextView = VIEW_COMPLEXITY_LIST;
		String message = "";
		command.getMessageAndRedirect().cleanMsgValue(); //Cleans message and redirect info
		this.complexityValidator.validate(command, result); //validates the form
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
					logEntryOnError = "Error trying to insert new Complexity Page";
					errorPropertyKey = COMPLEXITY_ERROR_CREATE;
					successPropertyKey = COMPLEXITY_SUCCESS_CREATE;
					this.complexityBO.save(command.getValidatedComplexity());
				}		
				//Process update
				if (formAction == FormAction.UPDATE){
					logEntryOnError = "Error trying to update the Complexity Page";
					errorPropertyKey = COMPLEXITY_ERROR_UPDATE;
					successPropertyKey = COMPLEXITY_SUCCESS_UPDATE;
					this.complexityBO.update(command.getValidatedComplexity()); 
				}
				//Process Delete
				if (formAction == FormAction.DELETE){
					logEntryOnError = "Error trying to delete the Complexity Page";
					errorPropertyKey = COMPLEXITY_ERROR_DELETE;
					successPropertyKey = COMPLEXITY_SUCCESS_DELETE;
					this.complexityBO.delete(command.getValidatedComplexity()); 
				}
			}catch(Exception e){
				log.error(logEntryOnError, e);
				errorOnPost = true;
				this.complexityValidator.setErrorAtException(result, errorPropertyKey);
			}

			if (!errorOnPost){
				command.setBaseHour("");
				command.setMultiplexor("");
				command.setComplexityId("");
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
	private ModelAndView nextModelView(String viewName, ComplexityForm command, FormAction actionType){
		ModelAndView formModelView = new ModelAndView(viewName);		
		command.setAction(actionType.toString());
	
		formModelView.setViewName(viewName);
		formModelView.addObject("command", command);
		return formModelView;
	}
}

