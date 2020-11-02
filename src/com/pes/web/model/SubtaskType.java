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
	private String reference;
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
	 * @return the reference
	 */
	@Column(name = "REFERENCE")
	public String getReference() {
		return reference;
	}

	/**
	 * @param reference the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((calculation == null) ? 0 : calculation.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((reference == null) ? 0 : reference.hashCode());
		result = prime * result + ((subtaskTypeCategory == null) ? 0 : subtaskTypeCategory.hashCode());
		result = prime * result + subtaskTypeCategoryId;
		result = prime * result + subtaskTypeId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		SubtaskType other = (SubtaskType) obj;
		if (calculation == null) {
			if (other.calculation != null) {
				return false;
			}
		} else if (!calculation.equals(other.calculation)) {
			return false;
		}
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (reference == null) {
			if (other.reference != null) {
				return false;
			}
		} else if (!reference.equals(other.reference)) {
			return false;
		}
		if (subtaskTypeCategory == null) {
			if (other.subtaskTypeCategory != null) {
				return false;
			}
		} else if (!subtaskTypeCategory.equals(other.subtaskTypeCategory)) {
			return false;
		}
		if (subtaskTypeCategoryId != other.subtaskTypeCategoryId) {
			return false;
		}
		if (subtaskTypeId != other.subtaskTypeId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SubtaskType [subtaskTypeId=");
		builder.append(subtaskTypeId);
		builder.append(", subtaskTypeCategoryId=");
		builder.append(subtaskTypeCategoryId);
		builder.append(", description=");
		builder.append(description);
		builder.append(", calculation=");
		builder.append(calculation);
		builder.append(", reference=");
		builder.append(reference);
		builder.append(", subtaskTypeCategory=");
		builder.append(subtaskTypeCategory);
		builder.append("]");
		return builder.toString();
	}
}
