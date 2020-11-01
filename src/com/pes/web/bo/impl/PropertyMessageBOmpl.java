package com.pes.web.bo.impl;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import com.pes.web.bo.PropertyMessageBO;

@Service
@Scope("singleton")
public class PropertyMessageBOmpl implements PropertyMessageBO {

	private static Log log = LogFactory.getLog(PropertyMessageBOmpl.class);
	
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	public static final String MSG_DEFAULT_VALUE = "Message no found for property: ";
	
	public String getMessageFromProperties(String key, String defaultMsg, Object... params){
		String value = "";
		if (StringUtils.isEmpty(defaultMsg)){
			value = MSG_DEFAULT_VALUE + key;
		}else{
			value = defaultMsg;
		}
		try{
			value = messageSource.getMessage(key, params, Locale.ENGLISH);
		}catch (Exception e){
			log.error("Error getitng message from property: " + key, e);
		}
		return value;
	}
	
	public String getMessageFromProperties(String key){
		Object[] params = {};
		return getMessageFromProperties(key, null, params);
	}
	
	public String getMessageFromProperties(String key, String defaultMsg){
		Object[] params = {};
		return getMessageFromProperties(key, defaultMsg, params);
	}

	/*
	 * (non-Javadoc)
	 * @see com.vicr.productpagemgr.service.PropertyMessageService#getValueForBeanShell(java.lang.String)
	 */
	public String getValueForBeanShell(String key) throws Exception {
		String value = "";
		try{
			value = messageSource.getMessage(key, null, Locale.ENGLISH);
		}catch (Exception e){
			log.error("Error getitng value from property: " + key +", to use the value on bean shell", e);
			throw e;
		}
		return value;
	}
}
