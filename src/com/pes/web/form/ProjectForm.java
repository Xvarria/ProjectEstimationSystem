package com.pes.web.form;

import com.pes.web.form.base.BaseForm;
import com.pes.web.model.Project;

public class ProjectForm extends BaseForm {

	private String projectId;
	private String projectNumber;
	private String name;
	
	private Project validatedProject;
	
	public ProjectForm () {
		this.projectId = "";	
		this.projectNumber = "";	
		this.name = "";	
	}

	/**
	 * @return the projectId
	 */
	public String getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	/**
	 * @return the projectNumber
	 */
	public String getProjectNumber() {
		return projectNumber;
	}

	/**
	 * @param projectNumber the projectNumber to set
	 */
	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
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
	 * @return the validatedProject
	 */
	public Project getValidatedProject() {
		return validatedProject;
	}

	/**
	 * @param validatedProject the validatedProject to set
	 */
	public void setValidatedProject(Project validatedProject) {
		this.validatedProject = validatedProject;
	}
}

