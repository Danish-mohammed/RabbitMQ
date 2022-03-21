package com.bridgelabz.userregistration.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.userregistration.UserRegistrationApplication;
import com.bridgelabz.userregistration.dto.ForgotPwdDto;
import com.bridgelabz.userregistration.dto.LoginDto;
import com.bridgelabz.userregistration.dto.UserDto;
import com.bridgelabz.userregistration.exception.CustomException;
import com.bridgelabz.userregistration.jwtoperations.Jms;
import com.bridgelabz.userregistration.jwtoperations.JwtOperations;
import com.bridgelabz.userregistration.model.UserEntity;
import com.bridgelabz.userregistration.repository.UserRepository;
import com.bridgelabz.userregistration.response.Response;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userrepo;
	@Autowired
	private BCryptPasswordEncoder pwdencoder;
	
	@Autowired
	private JwtOperations jwt=new JwtOperations();
	@Autowired
	private Jms jms;
	@Autowired
	private ModelMapper modelmapper;

	
	//@Override
	public Response registerUser(UserDto dto) {
		
		Optional<UserEntity>isuserprsent=userrepo.isEmailExists(dto.getEmail());
		if(isuserprsent.isPresent()) {
			throw new CustomException("invalid details",null,400,"true");
		}
		else {
		UserEntity entity=modelmapper.map(dto, UserEntity.class);
		entity.setRegisteredDate(LocalDateTime.now());
		entity.setUpdatedDate(LocalDateTime.now());
		entity.setPassword(pwdencoder.encode(entity.getPassword()));
		userrepo.save(entity);
		log.info(entity.getFirstName()+" registered "+"date:"+entity.getRegisteredDate());
		String body="http://localhost:8081/verifyemail/"+jwt.jwtToken(entity.getUserid());
		System.out.println(body);
		jms.sendEmail(entity.getEmail(),"verification email",body);
			System.out.println(entity.getEmail());
		return new Response("registration success",entity,201,"true");
	}
	}
	
	@Override
	public boolean isEmailExists(String email) {
		if(userrepo.isEmailExists(email).isPresent())
			throw new CustomException("email already exists",HttpStatus.OK,null,"false");
		return false;
	}

	@Override
	public UserEntity verify(String token) {
		long id=jwt.parseJWT(token);
		log.debug(token);
		UserEntity user=userrepo.isIdExists(id).orElseThrow(() -> new CustomException("user not exists",HttpStatus.OK,id,"false"));
		user.setVerifyEmail(true);
		userrepo.save(user);
		return user;
	}

	@Override
	public UserEntity getUserByEmail(String email) {
		System.out.println(email);
		UserEntity user=userrepo.getUserByEmail(email).orElseThrow(() -> new CustomException("email not exists",HttpStatus.OK,null,"false"));
		return user;
	}


	@Override
	public Response loginUser(LoginDto dto) {
		UserEntity  user=userrepo.getUserByEmail(dto.getEmail()).orElseThrow(() -> new CustomException("login failed",HttpStatus.OK,null,"false"));
		boolean ispwd=pwdencoder.matches(dto.getPassword(),user.getPassword());
		if(ispwd==false) {
			throw new CustomException("login failed",HttpStatus.OK,null,"false");
		} else {
			String token=jwt.jwtToken(user.getUserid());
			return new Response(" Successfully login user ", user, 200, token);
			
		}
		
		
		
	}
	public UserEntity getUser(String token) {
		long id=jwt.parseJWT(token);
		return userrepo.getUserById(id).orElseThrow(() -> new CustomException("user not exists",HttpStatus.OK,id,"false"));
	}
	

	@Override
	public UserEntity getUserById(long userId) {
		return userrepo.getUserById(userId).orElseThrow(() -> new CustomException("user not exists",HttpStatus.OK,userId,"false"));
	}

	@Override
	public String forgotPwd(ForgotPwdDto forgotdto) {
		UserEntity user=getUserByEmail(forgotdto.getEmail());
		String body="http://localhost:4200/resetpassword/"+jwt.jwtToken(user.getUserid());
		jms.sendEmail(user.getEmail(),"Reset Password",body);
		return "success";
	}



	@Override
	public Response getAllUser() {
		List<UserEntity> isUserPresent = userrepo.findAll();
		return new Response("users are",isUserPresent,200,"true");
	}

	@Override
	public boolean isIdPresent(long userId) {
		UserEntity user=userrepo.getUserById(userId).orElseThrow(() -> new CustomException("user not exists",HttpStatus.OK,userId,"false"));
		if(user.getEmail()!=null)
		return true;
		return false;
	}
	}
	
