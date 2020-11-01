package com.pes.web.bo;

public interface PropertyMessageBO {

	public String getMessageFromProperties(String key, String defaultMsg, Object... params);
	
	public String getMessageFromProperties(String key);
	
	public String getMessageFromProperties(String key, String defaultMsg);
	
	public String getValueForBeanShell(String key) throws Exception;
}
