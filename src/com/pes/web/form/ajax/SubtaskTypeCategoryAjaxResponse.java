package com.pes.web.form.ajax;

import com.pes.web.model.SubtaskTypeCategory;
import com.pes.web.model.constant.ResponseStatus;

public class SubtaskTypeCategoryAjaxResponse {
	
	private ResponseStatus responseStatus;
	private String errorMessage;
	private SubtaskTypeCategory subtaskTypeCategory;
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
	 * @return the subtaskTypeCategory
	 */
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
