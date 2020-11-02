package com.pes.web.form;

import com.pes.web.form.base.BaseForm;
import com.pes.web.model.SubtaskType;

public class SubtaskTypeForm extends BaseForm {
	
	private String subtaskTypeId;
	private String description;
	private String calculation;
	private int subtaskCategoryTypeId;
	
	private SubtaskType validatedSubtaskType;
	
	public SubtaskTypeForm () {
		this.subtaskTypeId = "";
		this.description = "";
		this.calculation = "";
		this.subtaskCategoryTypeId = 0;
	}
	
	/**
	 * @return the subtaskTypeId
	 */
	public String getSubtaskTypeId() {
		return subtaskTypeId;
	}
	
	/**
	 * @param subtaskTypeId the subtaskTypeId to set
	 */
	public void setSubtaskTypeId(String subtaskTypeId) {
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
	 * @return the calculation
	 */
	public String getCalculation() {
		return calculation;
	}

	/**
	 * @param calculation the calculation to set
	 */
	public void setCalculation(String calculation) {
		this.calculation = calculation;
	}

	/**
	 * @return the subtaskCategoryTypeId
	 */
	public int getSubtaskCategoryTypeId() {
		return subtaskCategoryTypeId;
	}

	/**
	 * @param subtaskCategoryTypeId the subtaskCategoryTypeId to set
	 */
	public void setSubtaskCategoryTypeId(int subtaskCategoryTypeId) {
		this.subtaskCategoryTypeId = subtaskCategoryTypeId;
	}

	/**
	 * @return the validatedSubtaskType
	 */
	public SubtaskType getValidatedSubtaskType() {
		return validatedSubtaskType;
	}

	/**
	 * @param validatedSubtaskType the validatedSubtaskType to set
	 */
	public void setValidatedSubtaskType(SubtaskType validatedSubtaskType) {
		this.validatedSubtaskType = validatedSubtaskType;
	}
}

