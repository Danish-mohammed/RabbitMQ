package com.bridgelabz.userregistration.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ForgotPwdDto {
	@NotBlank
	@Email
	private String email;

}
