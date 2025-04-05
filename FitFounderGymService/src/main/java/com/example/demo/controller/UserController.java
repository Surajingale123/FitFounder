package com.example.demo.controller;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.MemberShip;
import com.example.demo.model.User;
import com.example.demo.repository.IMemberShipRepo;
import com.example.demo.service.IMemberShipService;
import com.example.demo.service.IUserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*")
public class UserController {
	@Autowired
	IUserService iUserService;
	
	@Autowired
	IMemberShipService iMemberShipService;
	
	
	@GetMapping()
	public ResponseEntity<String> getUserDetails() {
		return ResponseEntity.ok("HI USER");
	}
	
	 @GetMapping("/getUsersById")
	    public ResponseEntity<User> getUsersById(Integer id) {
	        return ResponseEntity.ok(iUserService.getUserDetailsById(id));
	    }
	 
	 @PutMapping("/updateUsersById/{id}")
	    public ResponseEntity<String> updateUserById(@PathVariable("id") Integer id, 
	    		@RequestBody User user){
	    	return ResponseEntity.ok(iUserService.updateUserDetailsById(id,user));
	    }
	 
	 @GetMapping("/getAllPlans")
	    public ResponseEntity<List<MemberShip>> getAllPlans(){
			return ResponseEntity.ok(iMemberShipService.getAllPlans());
	    	
	    }
	
}
