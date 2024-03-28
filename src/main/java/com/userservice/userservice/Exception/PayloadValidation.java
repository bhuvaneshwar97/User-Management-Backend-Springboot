package com.userservice.userservice.Exception;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PayloadValidation {
	private String errorCode;
    private List message;
 
    public PayloadValidation(String errorCode, List message) {
    	
        super();
        this.errorCode = errorCode;
        this.message = message;
    }

	public PayloadValidation() {
		super();
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public List getMessage() {
		return message;
	}

	public void setMessage(List message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "PayloadValidation [errorCode=" + errorCode + ", message=" + message + "]";
	}
    
}
