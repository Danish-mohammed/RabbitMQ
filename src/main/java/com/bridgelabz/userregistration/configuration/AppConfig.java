package com.bridgelabz.userregistration.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.bridgelabz.userregistration.jwtoperations.JwtOperations;
import com.bridgelabz.userregistration.model.UserEntity;

@Configuration
public class AppConfig {
	@Bean
	public BCryptPasswordEncoder bcryptpasswordencoder()
	{
		return new BCryptPasswordEncoder();
	}
	@Bean
	public JwtOperations jwtoperations()
	{
		return new JwtOperations();
	}
	@Bean
	public UserEntity userentity()
	{
		return new UserEntity();
	}
	@Bean
	public ModelMapper modelMapper() {
	ModelMapper modelMapper = new ModelMapper();
	modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	return modelMapper;
	}

}

