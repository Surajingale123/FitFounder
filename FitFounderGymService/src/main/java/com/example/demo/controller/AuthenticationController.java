package com.example.demo.controller;


import com.example.demo.service.IAuthenticationService;
import com.example.demo.dto.JwtAuthenticationResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.IUserRepository;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")

@RequestMapping("/api/v1/auth")

public class AuthenticationController {
	@Autowired
    private  IAuthenticationService authenticationService;
	@Autowired
    private IUserRepository userRepository;
    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterRequest registerRequest) {
    	registerRequest.setRole(Role.USER);
    	registerRequest.setIsUser(true);
    	System.out.println(registerRequest);
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

//    @PostMapping("/login")
//    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody LoginRequest loginRequest) {
//    	System.out.println(loginRequest);
//    	
//        return ResponseEntity.ok(authenticationService.login(loginRequest));
 //   }
//    
//    @PostMapping("/login")
//    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody LoginRequest loginRequest) {
//        System.out.println(loginRequest);
//
//        // Check if the email is "admin@cptl.in" and set the role accordingly
//        if ("admin@cptl.in".equalsIgnoreCase(loginRequest.getEmail())) {
//            // Set role to ADMIN for the admin email
//           User.setRole(Role.ADMIN); // Assuming LoginRequest has a setRoleName method
//        } 
//
//        // Proceed with the authentication logic
//        JwtAuthenticationResponse response = authenticationService.login(loginRequest);
//        return ResponseEntity.ok(response);
//    }

    
    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody LoginRequest loginRequest) {
        System.out.println(loginRequest);

        // Check if the email is "admin@cptl.in" and set the role accordingly
        if ("admin@cptl.in".equalsIgnoreCase(loginRequest.getEmail())) {
            // Fetch user by email and set the role to ADMIN
            Optional<User> userOptional = userRepository.findByEmail(loginRequest.getEmail());
            
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                user.setRole(Role.ADMIN);  // Set the role to ADMIN
                userRepository.save(user);  // Save the updated user if necessary
            }
        }

        // Proceed with the authentication logic
        JwtAuthenticationResponse response = authenticationService.login(loginRequest);
        return ResponseEntity.ok(response);
    }

    
    

}


























