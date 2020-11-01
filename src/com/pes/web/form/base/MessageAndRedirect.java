package com.pes.web.form.base;

public class MessageAndRedirect {

	private final String typeError = MsgType.ERROR.toString(); 
	private final String typeSucces = MsgType.SUCCESS.toString();
	private final String typeWarning = MsgType.WARNING.toString();
	private final String typeInit = MsgType.INIT.toString();

	private boolean showMessage;
	private String message = "";
	private String messageType = typeInit;
	private String redirectUrl = "";
	
	public boolean isShowMessage() {
		return showMessage;
	}
	
	public void setShowMessage(boolean showMessage) {
		this.showMessage = showMessage;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	private void setAllMsgValues(String message, String messageType, String redirectUrl) {
		this.message = message;
		this.showMessage = true;
		this.messageType = messageType;
		this.redirectUrl = redirectUrl;
	}
	
	public void cleanMsgValue(){
		this.message = "";
		this.showMessage = false;
		this.messageType = typeInit;
		this.redirectUrl = "";
		
	}
	
	public void setErrorMessage(String errorMessage){
		this.setAllMsgValues(errorMessage, typeError, "");
	}
	
	public void setErrorMessageAndRedirect(String errorMessage, String redirectUrl){
		this.setAllMsgValues(errorMessage, typeError, redirectUrl);
	}

	public void setSuccessMessage(String sucessMessage){
		this.setAllMsgValues(sucessMessage, typeSucces, "");
	}
	
	public void setSuccessMessageAndRedirect(String sucessMessage, String redirectUrl){
		this.setAllMsgValues(sucessMessage, typeSucces, redirectUrl);
	}
	
	/*Read only porperties*/
	public String getTypeError() {
		return typeError;
	}

	public String getTypeSucces() {
		return typeSucces;
	}

	public String getTypeWarning() {
		return typeWarning;
	}

	public String getTypeInit() {
		return typeInit;
	}
}
