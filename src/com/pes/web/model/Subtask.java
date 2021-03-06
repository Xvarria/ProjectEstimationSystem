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
	private int complexityId;
	private int taskId;
	private String description;
	private boolean autoCalculation;
	private String referenceMode;
	private float time;
	
	private SubtaskType subtaskType;
	private Complexity complexity;
	
	private float calculatedTime;

	private Task task;
	
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
	
	/**
	 * @return the complexityId
	 */
	@Column(name = "COMPLEXITY_ID", updatable = false, insertable = false)
	public int getComplexityId() {
		return complexityId;
	}

	/**
	 * @param complexityId the complexityId to set
	 */
	public void setComplexityId(int complexityId) {
		this.complexityId = complexityId;
	}

	/**
	 * @return the complexity
	 */
    @ManyToOne
    @JoinColumn(name = "COMPLEXITY_ID")	
	public Complexity getComplexity() {
		return complexity;
	}

	/**
	 * @param complexity the complexity to set
	 */
	public void setComplexity(Complexity complexity) {
		this.complexity = complexity;
	}

	/**
	 * @return the taskId
	 */
	@Column(name="TASK_ID", updatable = false, insertable = false)
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
	 * @return the calculatedTime
	 */
	@Column(name="CALCULATED_TIME")
	public float getCalculatedTime() {
		return calculatedTime;
	}

	/**
	 * @param calculatedTime the calculatedTime to set
	 */
	public void setCalculatedTime(float calculatedTime) {
		this.calculatedTime = calculatedTime;
	}

	/**
	 * @return the task
	 */
	@ManyToOne
	@JoinColumn(name = "TASK_ID")
	public Task getTask() {
		return task;
	}

	/**
	 * @param task the task to set
	 */
	public void setTask(Task task) {
		this.task = task;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (autoCalculation ? 1231 : 1237);
		result = prime * result + Float.floatToIntBits(calculatedTime);
		result = prime * result + ((complexity == null) ? 0 : complexity.hashCode());
		result = prime * result + complexityId;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((referenceMode == null) ? 0 : referenceMode.hashCode());
		result = prime * result + subtaskId;
		result = prime * result + ((subtaskType == null) ? 0 : subtaskType.hashCode());
		result = prime * result + subtaskTypeId;
		result = prime * result + ((task == null) ? 0 : task.hashCode());
		result = prime * result + taskId;
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
		if (Float.floatToIntBits(calculatedTime) != Float.floatToIntBits(other.calculatedTime)) {
			return false;
		}
		if (complexity == null) {
			if (other.complexity != null) {
				return false;
			}
		} else if (!complexity.equals(other.complexity)) {
			return false;
		}
		if (complexityId != other.complexityId) {
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
		if (task == null) {
			if (other.task != null) {
				return false;
			}
		} else if (!task.equals(other.task)) {
			return false;
		}
		if (taskId != other.taskId) {
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
		builder.append(", complexityId=");
		builder.append(complexityId);
		builder.append(", taskId=");
		builder.append(taskId);
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
		builder.append(", complexity=");
		builder.append(complexity);
		builder.append(", calculatedTime=");
		builder.append(calculatedTime);
		builder.append(", task=");
		builder.append(task);
		builder.append("]");
		return builder.toString();
	}
}
