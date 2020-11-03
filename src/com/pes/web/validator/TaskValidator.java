package com.pes.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.pes.web.bo.TaskBO;
import com.pes.web.form.TaskForm;
import com.pes.web.model.Project;
import com.pes.web.model.Task;
import com.pes.web.model.constant.FormAction;

@Component
public class TaskValidator implements Validator {
	
	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		 return TaskForm.class.isAssignableFrom(clazz);
	}
	
	@Autowired
	private TaskBO taskBO;

	/*
	 * (non-Javadoc)
	 * @see org.springframework.validation.Validator\#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object target, Errors errors) {
		TaskForm command = (TaskForm) target;
		Task task = new Task();
		FormAction formAction = command.getFormAction();
		try {
			//Process Delete
			if (formAction == FormAction.DELETE || formAction == FormAction.UPDATE){
				int numericValue = 0;
				try {
					numericValue = Integer.parseInt(command.getTaskId());
				} catch (Exception e) {
					errors.rejectValue("taskId", "task.form.error.taskid-nan");
				}
				
				try {
					task = this.taskBO.getTaskById(numericValue);
				} catch (Exception e) {
					errors.rejectValue("taskId", "task.form.error.task-not-exist");
				}
			}				
			if (formAction == FormAction.CREATE || formAction == FormAction.UPDATE){
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "task.form.error.name-empty");
				task.setName(command.getName());
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "task.form.error.code-empty");
				task.setCode(command.getCode());
				Project project = command.getProject();
				task.setProject(project);
				task.setSequence(command.getSequence());
			}				
			command.setValidatedTask(task);
	
		}catch (Exception e) {
			errors.rejectValue("taskId", "task.form.error.unknow");
		}
	}
	
	public void setErrorAtException(Errors errors, String messageKey){
		errors.rejectValue("taskId", messageKey);
	}
}
