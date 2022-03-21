package com.bridgelabz.userregistration.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String message;
	HttpStatus status;
	private Object data;
	String statusMsg;
	public CustomException(String message,HttpStatus status,Object data,String statusMsg) {
		this.status=status;
		this.message=message;
		this.data=data;
		this.statusMsg=statusMsg;
		
	}

}
