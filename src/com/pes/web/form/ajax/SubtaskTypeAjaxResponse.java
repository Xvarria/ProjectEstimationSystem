package com.pes.web.form.ajax;

import com.pes.web.model.SubtaskType;
import com.pes.web.model.constant.ResponseStatus;

public class SubtaskTypeAjaxResponse {
	
	private ResponseStatus responseStatus;
	private String errorMessage;
	private SubtaskType subtaskType;
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
	 * @return the subtaskType
	 */
	public SubtaskType getSubtaskType() {
		return subtaskType;
	}
	/**
	 * @param subtaskType the subtaskType to set
	 */
	public void setSubtaskType(SubtaskType subtaskType) {
		this.subtaskType = subtaskType;
	}
	
	
}

