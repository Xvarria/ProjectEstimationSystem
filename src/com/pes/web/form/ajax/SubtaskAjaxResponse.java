package com.pes.web.form.ajax;

import com.pes.web.model.Subtask;
import com.pes.web.model.constant.ResponseStatus;

public class SubtaskAjaxResponse {
	
	private ResponseStatus responseStatus;
	private String errorMessage;
	private Subtask subtask;
	/**
	 * @return the responseStatus
	 */
	public ResponseStatus getResponseStatus() {
		return responseStatus;
	}
	/**
	 * @param responseStatus the responseStatus to set
	 */
	public void setResponseStatus(ResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}
	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	/**
	 * @return the subtask
	 */
	public Subtask getSubtask() {
		return subtask;
	}
	/**
	 * @param subtask the subtask to set
	 */
	public void setSubtask(Subtask subtask) {
		this.subtask = subtask;
	}
	
	
}

