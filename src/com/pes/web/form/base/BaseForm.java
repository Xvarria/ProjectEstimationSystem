package com.pes.web.form.base;

import com.pes.web.model.constant.FormAction;

public class BaseForm {

	private MessageAndRedirect messageAndRedirect = new MessageAndRedirect();
	private String action = "";
	
	private final String actionView = FormAction.VIEW.toString();
	private final String actionDelete = FormAction.DELETE.toString();
	private final String actionCreate = FormAction.CREATE.toString();
	private final String actionModify = FormAction.UPDATE.toString();
	private final String actionList = FormAction.LIST.toString();
		
	private LinkElement option1 = new LinkElement();
	private LinkElement option2 = new LinkElement();
	
	public String getAction() {
		return action;
	}
	public void setAction(String accion) {
		this.action = accion;
	}
	
	public MessageAndRedirect getMessageAndRedirect() {
		return messageAndRedirect;
	}
	
	public void setMessageAndRedirect(MessageAndRedirect messageAndRedirect) {
		this.messageAndRedirect = messageAndRedirect;
	}
	
	//ReadonlyMethods
	public String getActionCreate() {
		return actionCreate;
	}
	
	public String getActionModify() {
		return actionModify;
	}
	
	public String getActionList() {
		return actionList;
	}
	
	public String getActionView() {
		return actionView;
	}

	public String getActionDelete() {
		return actionDelete;
	}
	
	public boolean isCreate(){
		return action.equals(actionCreate);
	}
	
	public boolean isModify(){
		return action.equals(actionModify);
	}
	
	public boolean isView(){
		return action.equals(actionView);
	}
	
	public LinkElement getOption1() {
		return option1;
	}
	
	public void setOption1(LinkElement option1) {
		this.option1 = option1;
	}
	
	public LinkElement getOption2() {
		return option2;
	}
	
	public void setOption2(LinkElement option2) {
		this.option2 = option2;
	}
	
	public FormAction getFormAction() {
		return FormAction.getActionByDesc(this.getAction());
	} 
}
