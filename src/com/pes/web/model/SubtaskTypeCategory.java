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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + subtaskTypeCategoryId;
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
		SubtaskTypeCategory other = (SubtaskTypeCategory) obj;
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (subtaskTypeCategoryId != other.subtaskTypeCategoryId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SubtaskTypeCategory [subtaskTypeCategoryId=");
		builder.append(subtaskTypeCategoryId);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}	
}
