package com.bridgelabz.userregistration.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.bridgelabz.userregistration.dto.ForgotPwdDto;
import com.bridgelabz.userregistration.dto.LoginDto;
import com.bridgelabz.userregistration.dto.UserDto;
import com.bridgelabz.userregistration.model.UserEntity;
import com.bridgelabz.userregistration.response.Response;




@Service
public interface UserService {

	public Response registerUser(UserDto dto);
	public Response getAllUser();
	boolean isIdPresent(long userId);
	public boolean isEmailExists(String email);
	public UserEntity verify(String token);
	public Response loginUser(LoginDto dto);
	public UserEntity getUserByEmail(String email);
    public UserEntity getUserById(long userId);
    public String forgotPwd(ForgotPwdDto forgotdto);
}



