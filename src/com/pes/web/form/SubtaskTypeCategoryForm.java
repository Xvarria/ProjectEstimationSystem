package com.pes.web.form;

import com.pes.web.form.base.BaseForm;
import com.pes.web.model.SubtaskTypeCategory;

public class SubtaskTypeCategoryForm extends BaseForm {
	
	private String subtaskTypeCategoryId;
	private String description;
	
	private SubtaskTypeCategory subtaskTypeCategory;
	
	public SubtaskTypeCategoryForm () {
		this.subtaskTypeCategoryId = "";
		this.description = "";
	}
		
	/**
	 * @return the subtaskTypeCategoryId
	 */
	public String getSubtaskTypeCategoryId() {
		return subtaskTypeCategoryId;
	}

	/**
	 * @param subtaskTypeCategoryId the subtaskTypeCategoryId to set
	 */
	public void setSubtaskTypeCategoryId(String subtaskTypeCategoryId) {
		this.subtaskTypeCategoryId = subtaskTypeCategoryId;
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
	 * @return the subtaskTypeCategory
	 */
	public SubtaskTypeCategory getValidatedSubtaskTypeCategory() {
		return subtaskTypeCategory;
	}

	/**
	 * @param subtaskTypeCategory the subtaskTypeCategory to set
	 */
	public void setValidatedSubtaskTypeCategory(SubtaskTypeCategory subtaskTypeCategory) {
		this.subtaskTypeCategory = subtaskTypeCategory;
	}
}
