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
@Table(name = "subtask")
public class Subtask {

	private int subtaskId;
	private int subtaskTypeId;
	private String description;
	private boolean autoCalculation;
	private String referenceMode;
	private float time;
	private SubtaskType subtaskType;
	
	/**
	 * @return the subtaskId
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SUBTASK_ID")
	public int getSubtaskId() {
		return subtaskId;
	}
	
	/**
	 * @param subtaskId the subtaskId to set
	 */
	public void setSubtaskId(int subtaskId) {
		this.subtaskId = subtaskId;
	}
	
	/**
	 * @return the subtaskTypeId
	 */
	@Column(name = "SUBTASK_TYPE_ID", updatable = false, insertable = false)	
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
	 * @return the autoCalculation
	 */
	@Column(name = "AUTO_CALCULATION")
	public boolean isAutoCalculation() {
		return autoCalculation;
	}
	
	/**
	 * @param autoCalculation the autoCalculation to set
	 */
	public void setAutoCalculation(boolean autoCalculation) {
		this.autoCalculation = autoCalculation;
	}
	
	/**
	 * @return the referenceMode
	 */
	@Column(name = "REFERENCE_MODEL")
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
	@Column(name = "TIME")
	public float getTime() {
		return time;
	}
	
	/**
	 * @param time the time to set
	 */
	public void setTime(float time) {
		this.time = time;
	}
	
	/**
	 * @return the subtaskType
	 */
    @ManyToOne
    @JoinColumn(name = "SUBTASK_TYPE_ID")	
	public SubtaskType getSubtaskType() {
		return subtaskType;
	}
	
	/**
	 * @param subtaskType the subtaskType to set
	 */
	public void setSubtaskType(SubtaskType subtaskType) {
		this.subtaskType = subtaskType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (autoCalculation ? 1231 : 1237);
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((referenceMode == null) ? 0 : referenceMode.hashCode());
		result = prime * result + subtaskId;
		result = prime * result + ((subtaskType == null) ? 0 : subtaskType.hashCode());
		result = prime * result + subtaskTypeId;
		result = prime * result + Float.floatToIntBits(time);
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
		Subtask other = (Subtask) obj;
		if (autoCalculation != other.autoCalculation) {
			return false;
		}
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (referenceMode == null) {
			if (other.referenceMode != null) {
				return false;
			}
		} else if (!referenceMode.equals(other.referenceMode)) {
			return false;
		}
		if (subtaskId != other.subtaskId) {
			return false;
		}
		if (subtaskType == null) {
			if (other.subtaskType != null) {
				return false;
			}
		} else if (!subtaskType.equals(other.subtaskType)) {
			return false;
		}
		if (subtaskTypeId != other.subtaskTypeId) {
			return false;
		}
		if (Float.floatToIntBits(time) != Float.floatToIntBits(other.time)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Subtask [subtaskId=");
		builder.append(subtaskId);
		builder.append(", subtaskTypeId=");
		builder.append(subtaskTypeId);
		builder.append(", description=");
		builder.append(description);
		builder.append(", autoCalculation=");
		builder.append(autoCalculation);
		builder.append(", referenceMode=");
		builder.append(referenceMode);
		builder.append(", time=");
		builder.append(time);
		builder.append(", subtaskType=");
		builder.append(subtaskType);
		builder.append("]");
		return builder.toString();
	}
}
