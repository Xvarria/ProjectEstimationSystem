package com.pes.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "subtask_type_category")
public class SubtaskTypeCategory {
	
	private int subtaskTypeCategoryId;
	private String description;
	
	/**
	 * @return the subtaskCategoryId
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SUBTASK_TYPE_CATEGORY_ID")	
	public int getSubtaskTypeCategoryId() {
		return subtaskTypeCategoryId;
	}

	/**
	 * @param subtaskTypeCategoryId the subtaskTypeCategoryId to set
	 */
	public void setSubtaskTypeCategoryId(int subtaskTypeCategoryId) {
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
	
	
	
}
