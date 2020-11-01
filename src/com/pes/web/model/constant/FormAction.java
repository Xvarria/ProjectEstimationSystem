package com.pes.web.model.constant;

public enum FormAction {
	LIST,
	CREATE,
	UPDATE,
	DELETE,
	VIEW,
	SHOW;
	
	public static FormAction getActionByDesc(String description){
		if (description != null) {
			for (FormAction accion : FormAction.values()) {
				if (accion.toString().equals(description)) {
					return accion;
				}
			}
		}
		return null;
	}
}
