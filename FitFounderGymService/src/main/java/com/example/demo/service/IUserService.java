package com.example.demo.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.demo.model.MemberShip;
import com.example.demo.model.User;


public interface IUserService {
    UserDetailsService userDetailsService();

	List<User> getUserDetails();

	User  getUserDetailsById(Integer id);

	String updateUserDetailsById(Integer id, User user);

	List<User> getTrainerDetails();

	String deleteUserDetailsById(Integer id);

	List<User> getUserDetailsByTrainerId(Long object);
	
	

}
