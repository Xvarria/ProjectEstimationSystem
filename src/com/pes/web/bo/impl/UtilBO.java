package com.pes.web.bo.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.pes.web.model.exception.InvalidParameterException;

public final class UtilBO {
	
	/**
	 * 
	 * @param valueStr
	 * @return
	 * @throws InvalidParameterException
	 */
	public static int getIntByParameter(String valueStr) throws InvalidParameterException{
		try {
			int valorInt = Integer.parseInt(valueStr);
			return valorInt;
		} catch (Exception e) {
			throw new InvalidParameterException();
		}
	};
	
	public static final String getFechaFmt(Date fecha) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return formatter.format(fecha);
	}

	public static final String getFechaFmt(LocalDateTime fecha) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return formatter.format(fecha);
	}

	
}
