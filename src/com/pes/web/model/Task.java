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
	
	
}
