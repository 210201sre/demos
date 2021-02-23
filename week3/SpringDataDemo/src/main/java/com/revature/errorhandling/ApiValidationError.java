package com.revature.errorhandling;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ApiValidationError extends ApiSubError {

	private String object; // The type
	private String field; // The field name
	private Object rejectedValue; // The particular value
	private String message; // The reason
	
	public ApiValidationError(String object, String message) {
		this.object = object;
		this.message = message;
	}
	
	public ApiValidationError(String object, String message, String field) {
		this(object, message);
		this.field = field;
	}
	
	public ApiValidationError(String object, String message, String field, Object rejectedValue) {
		this(object, message, field);
		this.rejectedValue = rejectedValue;
	}
}
