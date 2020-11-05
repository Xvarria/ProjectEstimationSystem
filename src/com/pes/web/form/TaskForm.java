package com.pes.web.form;

import com.pes.web.form.base.BaseForm;
import com.pes.web.model.Project;
import com.pes.web.model.Task;

public class TaskForm extends BaseForm {

	private String taskId;
	private int projectId;
	private String code;
	private int sequence;
	private String name;
	//Parent Object
	private Project project;
	//Validated Object
	private Task validatedTask;
	
	public TaskForm () {
		this.taskId = "";	
		this.projectId = 0;	
		this.code = "";	
		this.sequence = 0;	
		this.name = "";	
		this.project = new Project();	
	}

	/**
	 * @return the taskId
	 */
	public String getTaskId() {
		return taskId;
	}

	/**
	 * @param taskId the taskId to set
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	/**
	 * @return the projectId
	 */
	public int getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the sequence
	 */
	public int getSequence() {
		return sequence;
	}

	/**
	 * @param sequence the sequence to set
	 */
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * @param project the project to set
	 */
	public void setProject(Project project) {
		this.project = project;
	}

	/**
	 * @return the validatedTask
	 */
	public Task getValidatedTask() {
		return validatedTask;
	}

	/**
	 * @param validatedTask the validatedTask to set
	 */
	public void setValidatedTask(Task validatedTask) {
		this.validatedTask = validatedTask;
	}
}

