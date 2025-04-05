package com.example.demo.controller;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.MemberShip;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.IAuthenticationService;
import com.example.demo.service.IMemberShipService;
import com.example.demo.service.IUserService;
import com.example.demo.dto.MemberShipPlan;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
@CrossOrigin(origins = "*")
public class AdminController {
	@Autowired
	IUserService iUserService;
	
	@Autowired
	IMemberShipService iMemberShipService;
	
	@Autowired
	private final IAuthenticationService authenticationService;
	
	
	
	
	
	
	@GetMapping("/getUsersByTrainer/{trainerId}")
	public ResponseEntity<List<User>> getAllUsers(Long trainerId) {
		return ResponseEntity.ok(iUserService.getUserDetailsByTrainerId(trainerId));

	}
	
    @GetMapping("/getUsers")
    public ResponseEntity<List<User>> getActiveUsers() {
        return ResponseEntity.ok(iUserService.getUserDetails());
    }
    //This will work for user, trainer and Admin for AdminDashboard
    @GetMapping("/getUsersById")
    public ResponseEntity<User> getUsersById(Integer id) {
        return ResponseEntity.ok(iUserService.getUserDetailsById(id));
    }
    
    @PutMapping("/updateUsersById/{id}")
    public ResponseEntity<String> updateUserById(@PathVariable("id") Integer id, 
    		@RequestBody User user){
    	return ResponseEntity.ok(iUserService.updateUserDetailsById(id,user));
    }
    
    @DeleteMapping("/deleteUserById/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Integer id){
    	return ResponseEntity.ok(iUserService.deleteUserDetailsById(id));
    }
    
    @GetMapping("/getTrainers")
    public ResponseEntity<List<User>> getActiveTrainers(){
    	return ResponseEntity.ok(iUserService.getTrainerDetails());
    }
    
    @PostMapping("/TrainerRegister")
    public ResponseEntity<User> registerTrainer(@RequestBody RegisterRequest registerRequest) {
    	registerRequest.setRole(Role.TRAINER);
    	registerRequest.setIsTrainer(true);
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }
    
    @PostMapping("/AdminRegister")
    public ResponseEntity<User> registerAdmin(@RequestBody RegisterRequest registerRequest) {
    	registerRequest.setRole(Role.ADMIN);
    	registerRequest.setIsAdmin(true);
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }
    @PostMapping("/AddMemberShipPlan")
    public ResponseEntity<String> addPlan(@RequestBody MemberShipPlan membershipPlan){
    return ResponseEntity.ok(iMemberShipService.addPlan(membershipPlan));	
    }
    
    @GetMapping("/getAllPlans")
    public ResponseEntity<List<MemberShip>> getAllPlans(){
		return ResponseEntity.ok(iMemberShipService.getAllPlans());
    	
    }
    
    @GetMapping("/getPlansById")
    public ResponseEntity<MemberShip> getPlansById(@PathVariable("id") Long id){
		return ResponseEntity.ok(iMemberShipService.getPlansId(id));
    	
    }
    
    @PutMapping("/updatePlanById/{id}")
    public ResponseEntity<String> updatePlanById(@PathVariable("id") Long id, 
    		@RequestBody MemberShip memberShip){
    	Boolean isSuccess = iMemberShipService.updatePlanById(id,memberShip);
    	if(!isSuccess) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request");
    	}
    	return ResponseEntity.ok("Plan Updated Successfully");
    }
    
    @DeleteMapping("/deletePlanById/{id}")
    public ResponseEntity<String> deletePlanById(@PathVariable("id") Long id){
    	Boolean isSuccess = iMemberShipService.deletePlanDetailsById(id);
    	if(!isSuccess) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request");
    	}
    	return ResponseEntity.ok("Plan Deleted Successfully");
    }
    
}
