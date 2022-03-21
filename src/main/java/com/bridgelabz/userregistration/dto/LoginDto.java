package com.bridgelabz.userregistration.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class LoginDto {
	@NotBlank
	@Email
	private String email;
	@NotBlank
	@Size(min=6,max=10)
	private String password;
}

