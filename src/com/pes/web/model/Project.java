package com.pes.web.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.springframework.context.annotation.Lazy;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "project")
public class Project {
	
	private int projectId;
	private String projectNumber;
	private String name;
	
	@JsonBackReference
	private Collection<Task> taskList;
		
	/**
	 * @return the projectId
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PROJECT_ID")		
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
	 * @return the projectNumber
	 */
	@Column(name = "PROJECT_NUMBER")	
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
	@Column(name = "NAME")
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
	 * @return the taskList
	 */
	@OneToMany(mappedBy = "taskId", cascade = CascadeType.ALL)
	@OrderBy("sequence ASC")
	@Lazy(true)
	public Collection<Task> getTaskList() {
		return taskList;
	}

	/**
	 * @param taskList the taskList to set
	 */
	public void setTaskList(Collection<Task> taskList) {
		this.taskList = taskList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + projectId;
		result = prime * result + ((projectNumber == null) ? 0 : projectNumber.hashCode());
		result = prime * result + ((taskList == null) ? 0 : taskList.hashCode());
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
		Project other = (Project) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (projectId != other.projectId) {
			return false;
		}
		if (projectNumber == null) {
			if (other.projectNumber != null) {
				return false;
			}
		} else if (!projectNumber.equals(other.projectNumber)) {
			return false;
		}
		if (taskList == null) {
			if (other.taskList != null) {
				return false;
			}
		} else if (!taskList.equals(other.taskList)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Project [projectId=");
		builder.append(projectId);
		builder.append(", projectNumber=");
		builder.append(projectNumber);
		builder.append(", name=");
		builder.append(name);
		builder.append(", taskList=");
		builder.append(taskList);
		builder.append("]");
		return builder.toString();
	}
}
