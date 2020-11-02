package com.pes.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.pes.web.bo.SubtaskBO;
import com.pes.web.bo.SubtaskTypeBO;
import com.pes.web.form.SubtaskForm;
import com.pes.web.model.Subtask;
import com.pes.web.model.SubtaskType;
import com.pes.web.model.constant.FormAction;

@Component
public class SubtaskValidator implements Validator {
	
	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		 return SubtaskForm.class.isAssignableFrom(clazz);
	}
	
	@Autowired
	private SubtaskBO subtaskBO;
	
	@Autowired
	private SubtaskTypeBO subtaskTypeBO;

	/*
	 * (non-Javadoc)
	 * @see org.springframework.validation.Validator\#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object target, Errors errors) {
		SubtaskForm command = (SubtaskForm) target;
		Subtask subtask = new Subtask();
		FormAction formAction = command.getFormAction();
		try {
			//Process Delete
			if (formAction == FormAction.DELETE || formAction == FormAction.UPDATE){
				int numericValue = 0;
				try {
					numericValue = Integer.parseInt(command.getSubtaskId());
				} catch (Exception e) {
					errors.rejectValue("subtaskId", "subtask.form.error.subtaskid-nan");
				}
				
				try {
					subtask = this.subtaskBO.getSubtaskById(numericValue);
				} catch (Exception e) {
					errors.rejectValue("subtaskId", "subtask.form.error.subtask-not-exist");
				}
			}				
			if (formAction == FormAction.CREATE || formAction == FormAction.UPDATE){
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "subtask.form.error.description-empty");
				subtask.setDescription(command.getDescription());
				try {
					SubtaskType subtaskType = this.subtaskTypeBO.getSubtaskTypeById(command.getSubtaskTypeId());
					subtask.setSubtaskType(subtaskType);
				} catch (Exception e) {
					errors.rejectValue("subtaskTypeId", "subtask.form.error.invalid-type");
				}

				try {
					float time = Float.parseFloat(command.getTime());
					subtask.setTime(time);
				} catch (Exception e) {
					errors.rejectValue("time", "subtask.form.error.time-nan");
				}				
				subtask.setAutoCalculation("TRUE".equalsIgnoreCase(command.getAutoCalculation()));
				subtask.setReferenceMode(command.getReferenceMode());
			}				
			command.setValidatedSubtask(subtask);
	
		}catch (Exception e) {
			errors.rejectValue("subtaskId", "subtask.form.error.unknow");
		}
	}
	
	public void setErrorAtException(Errors errors, String messageKey){
		errors.rejectValue("subtaskId", messageKey);
	}
}
