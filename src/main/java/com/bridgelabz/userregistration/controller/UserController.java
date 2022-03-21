package com.bridgelabz.userregistration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.userregistration.dto.ForgotPwdDto;
import com.bridgelabz.userregistration.dto.LoginDto;
import com.bridgelabz.userregistration.dto.UserDto;
import com.bridgelabz.userregistration.jwtoperations.JwtOperations;
import com.bridgelabz.userregistration.model.UserEntity;
import com.bridgelabz.userregistration.response.Response;
import com.bridgelabz.userregistration.service.UserServiceImpl;

@RestController
public class UserController {
	@Autowired
	private UserServiceImpl userimpl;
	
	@Autowired
	private JwtOperations jwt;
	/**
	 * Register User : used to register the user
	 * @param dto
	 * @return register response
	 */
	
	@PostMapping("/registeruser")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Response> registerUser(@RequestBody UserDto dto,BindingResult result){
		Response response=userimpl.registerUser(dto);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	/**
	 * Verify Eamil : used to verify the email whether sent link is correct or not
	 * @param token
	 * @return verification response
	 */
	@GetMapping("/verifyemail/{token}")
	public ResponseEntity<Response> verifyemail(@PathVariable("token") String token)
	{
		return new ResponseEntity<Response>(new Response("email verified",userimpl.verify(token),201,"true"),HttpStatus.ACCEPTED);
	}
	
	/**
	 * Login User:used to login the user
	 * @param "password"
	 * @return login response
	 */
	@PostMapping("/loginuser")
	public ResponseEntity<Response> loginUser(@RequestBody LoginDto dto,BindingResult result)
	{
		Response respDTO =userimpl.loginUser(dto);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
	
	/**
	 * Get All Users: used to display all the users in the table
	 * @return list of users
	 */

	@GetMapping("/getallusers")
	public ResponseEntity<Response> getAllContacts() {
		Response respDTO = userimpl.getAllUser();
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}

	@GetMapping("/getuser")
	public ResponseEntity<Response> getuser(@RequestHeader String token)
	{
		 Long id=jwt.parseJWT(token);
	     UserEntity user=userimpl.getUserById(id);
		return new ResponseEntity<Response>(new Response("welcome",userimpl.getUser(token),200,"true"),HttpStatus.OK);
	}
	
	@PostMapping("/forgotpassword")
	public ResponseEntity<Response> forgotPassword(@RequestBody ForgotPwdDto forgotdto,BindingResult result)
	{

		if(result.hasErrors())
		return new ResponseEntity<Response>(new Response("invalid details",null,400,"true"),HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Response>(new Response("password updated and sent to mail successfully","your new pwd is:"+userimpl.forgotPwd(forgotdto),200,"true"),HttpStatus.OK);
	}
	

}
