package com.pes.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "complexity")
public class Complexity {

	private int complexityId;
	private String description;
	private float baseHour;
	private float multiplexor;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COMPLEXITY_ID")
	public int getComplexityId() {
		return complexityId;
	}
	
	public void setComplexityId(int complexityId) {
		this.complexityId = complexityId;
	}
	
	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "BASE_HOUR")
	public float getBaseHour() {
		return baseHour;
	}
	
	public void setBaseHour(float baseHour) {
		this.baseHour = baseHour;
	}
	
	@Column(name = "MULTIPLEXOR")
	public float getMultiplexor() {
		return multiplexor;
	}
	
	public void setMultiplexor(float multiplexor) {
		this.multiplexor = multiplexor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(baseHour);
		result = prime * result + complexityId;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + Float.floatToIntBits(multiplexor);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Complexity other = (Complexity) obj;
		if (Float.floatToIntBits(baseHour) != Float.floatToIntBits(other.baseHour)) {
			return false;
		}
		if (complexityId != other.complexityId) {
			return false;
		}
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (Float.floatToIntBits(multiplexor) != Float.floatToIntBits(other.multiplexor)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Complexity [complexityId=");
		builder.append(complexityId);
		builder.append(", description=");
		builder.append(description);
		builder.append(", baseHour=");
		builder.append(baseHour);
		builder.append(", multiplexor=");
		builder.append(multiplexor);
		builder.append("]");
		return builder.toString();
	}
}
