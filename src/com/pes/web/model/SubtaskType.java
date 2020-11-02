package com.pes.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "subtask_type")
public class SubtaskType {
	
	private int subtaskTypeId;
	private int subtaskTypeCategoryId;
	private String description;
	private String calculation;
	private SubtaskTypeCategory subtaskTypeCategory;
	
	/**
	 * 
	 * @return the subtaskTypeId
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SUBTASK_TYPE_ID")		
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
	 * @return the subtaskTypeCategoryId
	 */
	@Column(name = "SUBTASK_TYPE_CATEGORY_ID", insertable = false, updatable = false)	
	public int getSubtaskTypeCategoryId() {
		return subtaskTypeCategoryId;
	}
	
	/**
	 * @param subtaskTypeCategoryId the subtaskTTypeCategoryId to set
	 */
	public void setSubtaskTypeCategoryId(int subtaskTypeCategoryId) {
		this.subtaskTypeCategoryId = subtaskTypeCategoryId;
	}
	
	/**
	 * @return the description
	 */
	@Column(name = "DESCRIPTION")	
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
	@Column(name = "CALCULATION")	
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
	 * @return the subtaskTypeCategory
	 */
    @ManyToOne
    @JoinColumn(name = "SUBTASK_TYPE_CATEGORY_ID")
	public SubtaskTypeCategory getSubtaskTypeCategory() {
		return subtaskTypeCategory;
	}
	
	/**
	 * @param subtaskTypeCategory the subtaskTypeCategory to set
	 */
	public void setSubtaskTypeCategory(SubtaskTypeCategory subtaskTypeCategory) {
		this.subtaskTypeCategory = subtaskTypeCategory;
	}
}
