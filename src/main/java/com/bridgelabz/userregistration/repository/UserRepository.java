package com.bridgelabz.userregistration.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.userregistration.model.UserEntity;



@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	@Query(value = "select email from user_registration where email=?1",nativeQuery = true)
	Optional<UserEntity> isEmailExists(String email);
	
	@Query(value = "select * from user_registration where userid=?1",nativeQuery = true)
	Optional<UserEntity> isIdExists(long id);
	
	@Query(value = "select * from user_registration where email=?1",nativeQuery = true)
	Optional<UserEntity> getUserByEmail(String email);
	
	@Query(value = "select * from user_registration where name=?1",nativeQuery = true)
	Optional<UserEntity> getUserByName(String firstName);
	
	@Query(value="select * from user_registration",nativeQuery=true)
	Optional<List<UserEntity>> getAllUsers();
	
	@Query(value = "select * from user_registration where userid=?1",nativeQuery = true)
	Optional<UserEntity> getUserById(long userId);
	
//	Optional<UserEntity> findByEmail(String email);
	
	

}

