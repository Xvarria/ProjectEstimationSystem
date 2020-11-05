package com.pes.web.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.springframework.context.annotation.Lazy;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "task")
public class Task {

	private int taskId;
	private int projectId;
	private String code;
	private int sequence;
	private String name;
	
	private Project project;
	
	@JsonBackReference
	private Collection<Subtask> subtaskList;

	/**
	 * @return the taskId
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TASK_ID")	
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
	 * @return the projectId
	 */
	@Column(name = "PROJECT_ID", updatable = false, insertable = false)
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
	 * @return the code
	 */
	@Column(name = "CODE")
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the sequence
	 */
	@Column(name = "SEQUENCE")
	public int getSequence() {
		return sequence;
	}

	/**
	 * @param sequence the sequence to set
	 */
	public void setSequence(int sequence) {
		this.sequence = sequence;
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
	 * @return the project
	 */
	@ManyToOne
	@JoinColumn(name = "PROJECT_ID")
	@Lazy(true)
	public Project getProject() {
		return project;
	}

	/**
	 * @param project the project to set
	 */
	public void setProject(Project project) {
		this.project = project;
	}

	/**
	 * @return the subtaskList
	 */
	@OneToMany(mappedBy = "taskId", cascade = CascadeType.ALL)
	@OrderBy("subtaskId ASC")
	@Lazy(true)
	public Collection<Subtask> getSubtaskList() {
		return subtaskList;
	}

	/**
	 * @param subtaskList the subtaskList to set
	 */
	public void setSubtaskList(Collection<Subtask> subtaskList) {
		this.subtaskList = subtaskList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((project == null) ? 0 : project.hashCode());
		result = prime * result + projectId;
		result = prime * result + sequence;
		result = prime * result + ((subtaskList == null) ? 0 : subtaskList.hashCode());
		result = prime * result + taskId;
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
		Task other = (Task) obj;
		if (code == null) {
			if (other.code != null) {
				return false;
			}
		} else if (!code.equals(other.code)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (project == null) {
			if (other.project != null) {
				return false;
			}
		} else if (!project.equals(other.project)) {
			return false;
		}
		if (projectId != other.projectId) {
			return false;
		}
		if (sequence != other.sequence) {
			return false;
		}
		if (subtaskList == null) {
			if (other.subtaskList != null) {
				return false;
			}
		} else if (!subtaskList.equals(other.subtaskList)) {
			return false;
		}
		if (taskId != other.taskId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Task [taskId=");
		builder.append(taskId);
		builder.append(", projectId=");
		builder.append(projectId);
		builder.append(", code=");
		builder.append(code);
		builder.append(", sequence=");
		builder.append(sequence);
		builder.append(", name=");
		builder.append(name);
		builder.append(", project=");
		builder.append(project);
		builder.append(", subtaskList=");
		builder.append(subtaskList);
		builder.append("]");
		return builder.toString();
	}
}
