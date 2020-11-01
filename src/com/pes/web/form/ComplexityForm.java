package com.pes.web.form;

import com.pes.web.form.base.BaseForm;
import com.pes.web.model.Complexity;

public class ComplexityForm extends BaseForm {
	
	private String complexityId;
	private String description;
	private String baseHour;
	private String multiplexor;
	
	private Complexity validatedComplexity;
	
	public ComplexityForm () {
		this.complexityId = "";
		this.description = "";
		this.baseHour = "";
		this.multiplexor = "";
	}
	
	/**
	 * @return the complexityId
	 */
	public String getComplexityId() {
		return complexityId;
	}
	
	/**
	 * @param complexityId the complexityId to set
	 */
	public void setComplexityId(String complexityId) {
		this.complexityId = complexityId;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return the baseHour
	 */
	public String getBaseHour() {
		return baseHour;
	}
	
	/**
	 * @param baseHour the baseHour to set
	 */
	public void setBaseHour(String baseHour) {
		this.baseHour = baseHour;
	}
	
	/**
	 * @return the multiplexor
	 */
	public String getMultiplexor() {
		return multiplexor;
	}
	
	/**
	 * @param multiplexsor the multiplexor to set
	 */
	public void setMultiplexor(String multiplexor) {
		this.multiplexor = multiplexor;
	}

	/**
	 * @return the validatedComplexity
	 */
	public Complexity getValidatedComplexity() {
		return validatedComplexity;
	}

	/**
	 * @param validatedComplexity the validatedComplexity to set
	 */
	public void setValidatedComplexity(Complexity validatedComplexity) {
		this.validatedComplexity = validatedComplexity;
	}
}
