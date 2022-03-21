package com.bridgelabz.userregistration.jwtoperations;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class MailService implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String consumerMail;
	private String message;
	private String body;
}

