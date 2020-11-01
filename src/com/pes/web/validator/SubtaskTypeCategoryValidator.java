package com.pes.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.pes.web.bo.SubtaskTypeCategoryBO;
import com.pes.web.form.SubtaskTypeCategoryForm;
import com.pes.web.model.SubtaskTypeCategory;
import com.pes.web.model.constant.FormAction;

@Component
public class SubtaskTypeCategoryValidator implements Validator {
	
	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		 return SubtaskTypeCategoryForm.class.isAssignableFrom(clazz);
	}
	
	@Autowired
	private SubtaskTypeCategoryBO subtaskTypeCategoryBO;

	/*
	 * (non-Javadoc)
	 * @see org.springframework.validation.Validator\#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object target, Errors errors) {
		SubtaskTypeCategoryForm command = (SubtaskTypeCategoryForm) target;
		SubtaskTypeCategory subtaskTypeCategory = new SubtaskTypeCategory();
		FormAction formAction = command.getFormAction();
		try {
			//Process Delete
			if (formAction == FormAction.DELETE || formAction == FormAction.UPDATE){
				int numericValue = 0;
				try {
					numericValue = Integer.parseInt(command.getSubtaskTypeCategoryId());
				} catch (Exception e) {
					errors.rejectValue("subtaskTypeCategoryId", "subtaskTypeCategory.form.error.subtaskTypeCategoryid-nan");
				}
				
				try {
					subtaskTypeCategory = this.subtaskTypeCategoryBO.getSubtaskTypeCategoryById(numericValue);
				} catch (Exception e) {
					errors.rejectValue("subtaskTypeCategoryId", "subtaskTypeCategory.form.error.subtaskTypeCategory-not-exist");
				}
			}				
			if (formAction == FormAction.CREATE || formAction == FormAction.UPDATE){
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "subtaskTypeCategory.form.error.description-empty");
				subtaskTypeCategory.setDescription(command.getDescription());
			}		
			command.setValidatedSubtaskTypeCategory(subtaskTypeCategory);	
		} //catch (PesWebException e1) {
		catch (Exception e1) {
			errors.rejectValue("subtaskTypeCategoryId", "subtaskTypeCategory.form.error.unknow");
		}
	}
	
	public void setErrorAtException(Errors errors, String messageKey){
		errors.rejectValue("subtaskTypeCategoryId", messageKey);
	}
}

