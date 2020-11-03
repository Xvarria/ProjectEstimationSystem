package com.pes.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.pes.web.bo.ProjectBO;
import com.pes.web.form.ProjectForm;
import com.pes.web.model.Project;
import com.pes.web.model.constant.FormAction;

@Component
public class ProjectValidator implements Validator {
	
	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		 return ProjectForm.class.isAssignableFrom(clazz);
	}
	
	@Autowired
	private ProjectBO projectBO;

	/*
	 * (non-Javadoc)
	 * @see org.springframework.validation.Validator\#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object target, Errors errors) {
		ProjectForm command = (ProjectForm) target;
		Project project = new Project();
		FormAction formAction = command.getFormAction();
		try {
			//Process Delete
			if (formAction == FormAction.DELETE || formAction == FormAction.UPDATE){
				int numericValue = 0;
				try {
					numericValue = Integer.parseInt(command.getProjectId());
				} catch (Exception e) {
					errors.rejectValue("projectId", "project.form.error.projectid-nan");
				}
				
				try {
					project = this.projectBO.getProjectById(numericValue);
				} catch (Exception e) {
					errors.rejectValue("projectId", "project.form.error.project-not-exist");
				}
			}				
			if (formAction == FormAction.CREATE || formAction == FormAction.UPDATE){
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "project.form.error.name-empty");
				project.setName(command.getName());
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "projectNumber", "project.form.error.project-number-empty");
				project.setProjectNumber(command.getProjectNumber());
			}				
			command.setValidatedProject(project);
	
		}catch (Exception e) {
			errors.rejectValue("projectId", "project.form.error.unknow");
		}
	}
	
	public void setErrorAtException(Errors errors, String messageKey){
		errors.rejectValue("projectId", messageKey);
	}
}
