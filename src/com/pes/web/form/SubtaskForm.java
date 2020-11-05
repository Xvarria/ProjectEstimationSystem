package com.pes.web.form;

import com.pes.web.form.base.BaseForm;
import com.pes.web.model.Subtask;
import com.pes.web.model.Task;

public class SubtaskForm extends BaseForm {

	private String subtaskId;
	private int subtaskTypeId;
	private String description;
	private String autoCalculation;
	private String referenceMode;
	private String time;
	private String subtaskType;
	private int taskId;
	private int complexityId;
	
	//Parent Object
	private Task task;
	//Validated Object
	private Subtask validatedSubtask;
	
	public SubtaskForm () {
		this.subtaskId = "";	
		this.subtaskTypeId = 0;
		this.complexityId = 0;	
		this.description = "";	
		this.autoCalculation = "";	
		this.referenceMode = "";	
		this.time = "";	
		this.subtaskType = "";
		this.taskId = 0;
		this.task = new Task();
	}

	/**
	 * @return the subtaskId
	 */
	public String getSubtaskId() {
		return subtaskId;
	}

	/**
	 * @param subtaskId the subtaskId to set
	 */
	public void setSubtaskId(String subtaskId) {
		this.subtaskId = subtaskId;
	}

	/**
	 * @return the subtaskTypeId
	 */
	public int getSubtaskTypeId() {
		return subtaskTypeId;
	}

	/**
	 * @param subtaskTypeId the subtaskTypeId to set
	 */
	public void setSubtaskTypeId(int subtaskTypeId) {
		this.subtaskTypeId = subtaskTypeId;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the autoCalculation
	 */
	public String getAutoCalculation() {
		return autoCalculation;
	}

	/**
	 * @param autoCalculation the autoCalculation to set
	 */
	public void setAutoCalculation(String autoCalculation) {
		this.autoCalculation = autoCalculation;
	}

	/**
	 * @return the referenceMode
	 */
	public String getReferenceMode() {
		return referenceMode;
	}

	/**
	 * @param referenceMode the referenceMode to set
	 */
	public void setReferenceMode(String referenceMode) {
		this.referenceMode = referenceMode;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the subtaskType
	 */
	public String getSubtaskType() {
		return subtaskType;
	}

	/**
	 * @param subtaskType the subtaskType to set
	 */
	public void setSubtaskType(String subtaskType) {
		this.subtaskType = subtaskType;
	}

	/**
	 * @return the validatedSubtask
	 */
	public Subtask getValidatedSubtask() {
		return validatedSubtask;
	}

	/**
	 * @param validatedSubtask the validatedSubtask to set
	 */
	public void setValidatedSubtask(Subtask validatedSubtask) {
		this.validatedSubtask = validatedSubtask;
	}

	/**
	 * @return the task
	 */
	public Task getTask() {
		return task;
	}

	/**
	 * @param task the task to set
	 */
	public void setTask(Task task) {
		this.task = task;
	}

	/**
	 * @return the taskId
	 */
	public int getTaskId() {
		return taskId;
	}

	/**
	 * @param taskId the taskId to set
	 */
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	/**
	 * @return the complexityId
	 */
	public int getComplexityId() {
		return complexityId;
	}

	/**
	 * @param complexityId the complexityId to set
	 */
	public void setComplexityId(int complexityId) {
		this.complexityId = complexityId;
	}
	
	
	
}

