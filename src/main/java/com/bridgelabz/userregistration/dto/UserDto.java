package com.bridgelabz.userregistration.dto;

import lombok.Data;

@Data
public class UserDto {
	private String firstName;
	private String lastName;
	private String dataOfBirth;
	private String email;
	private String password;

}
