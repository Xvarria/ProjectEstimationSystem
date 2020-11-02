package com.pes.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.pes.web.bo.SubtaskTypeBO;
import com.pes.web.bo.SubtaskTypeCategoryBO;
import com.pes.web.form.SubtaskTypeForm;
import com.pes.web.model.SubtaskType;
import com.pes.web.model.SubtaskTypeCategory;
import com.pes.web.model.constant.FormAction;

@Component
public class SubtaskTypeValidator implements Validator {
	
	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		 return SubtaskTypeForm.class.isAssignableFrom(clazz);
	}
	
	@Autowired
	private SubtaskTypeBO subtaskTypeBO;

	@Autowired
	private SubtaskTypeCategoryBO subtaskTypeCategoryBO;

	/*
	 * (non-Javadoc)
	 * @see org.springframework.validation.Validator\#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object target, Errors errors) {
		SubtaskTypeForm command = (SubtaskTypeForm) target;
		SubtaskType subtaskType = new SubtaskType();
		FormAction formAction = command.getFormAction();
		try {
			//Process Delete
			if (formAction == FormAction.DELETE || formAction == FormAction.UPDATE){
				int numericValue = 0;
				try {
					numericValue = Integer.parseInt(command.getSubtaskTypeId());
				} catch (Exception e) {
					errors.rejectValue("subtaskTypeId", "subtaskType.form.error.subtaskTypeid-nan");
				}
				
				try {
					subtaskType = this.subtaskTypeBO.getSubtaskTypeById(numericValue);
				} catch (Exception e) {
					errors.rejectValue("subtaskTypeId", "subtaskType.form.error.subtaskType-not-exist");
				}
			}				
			if (formAction == FormAction.CREATE || formAction == FormAction.UPDATE){
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "subtaskType.form.error.description-empty");
				subtaskType.setDescription(command.getDescription());
				try {
					SubtaskTypeCategory subtaskTypeCategory = this.subtaskTypeCategoryBO.getSubtaskTypeCategoryById(command.getSubtaskCategoryTypeId());
					subtaskType.setSubtaskTypeCategory(subtaskTypeCategory);
				} catch (Exception e) {
					errors.rejectValue("subtaskTypeCategoryId", "subtaskType.form.error.invalid-category");
				}
				subtaskType.setCalculation(command.getCalculation());
				subtaskType.setReference(command.getReference());
				
			}		
			command.setValidatedSubtaskType(subtaskType);	
		}catch (Exception e) {
			errors.rejectValue("subtaskTypeId", "subtaskType.form.error.unknow");
		}
	}
	
	public void setErrorAtException(Errors errors, String messageKey){
		errors.rejectValue("subtaskTypeId", messageKey);
	}
}
