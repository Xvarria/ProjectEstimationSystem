package com.pes.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.pes.web.bo.ComplexityBO;
import com.pes.web.form.ComplexityForm;
import com.pes.web.model.Complexity;
import com.pes.web.model.constant.FormAction;

@Component
public class ComplexityValidator implements Validator {
	
	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		 return ComplexityForm.class.isAssignableFrom(clazz);
	}
	
	@Autowired
	private ComplexityBO complexityBO;

	/*
	 * (non-Javadoc)
	 * @see org.springframework.validation.Validator\#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object target, Errors errors) {
		ComplexityForm command = (ComplexityForm) target;
		Complexity complexity = new Complexity();
		FormAction formAction = command.getFormAction();
		try {
			//Process Delete
			if (formAction == FormAction.DELETE || formAction == FormAction.UPDATE){
				int numericValue = 0;
				try {
					numericValue = Integer.parseInt(command.getComplexityId());
				} catch (Exception e) {
					errors.rejectValue("complexityId", "complexity.form.error.complexityid-nan");
				}
				
				try {
					complexity = this.complexityBO.getComplexityById(numericValue);
				} catch (Exception e) {
					errors.rejectValue("complexityId", "complexity.form.error.complexity-not-exist");
				}
			}				
			if (formAction == FormAction.CREATE || formAction == FormAction.UPDATE){
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "complexity.form.error.description-empty");
				complexity.setDescription(command.getDescription());
				try {
					float numericValue = Float.parseFloat(command.getBaseHour());
					complexity.setBaseHour(numericValue);
				} catch (Exception e) {
					errors.rejectValue("baseHour", "complexity.form.error.basehour-nan");
				}
				
				try {
					float numericValue = Float.parseFloat(command.getMultiplexor());
					complexity.setMultiplexor(numericValue);
				} catch (Exception e) {
					errors.rejectValue("multiplexor", "complexity.form.error.multiplexsor-nan");
				}
			}		
			command.setValidatedComplexity(complexity);	
		} //catch (PesWebException e1) {
		catch (Exception e1) {
			errors.rejectValue("complexityId", "complexity.form.error.unknow");
		}
	}
	
	public void setErrorAtException(Errors errors, String messageKey){
		errors.rejectValue("complexityId", messageKey);
	}
}

