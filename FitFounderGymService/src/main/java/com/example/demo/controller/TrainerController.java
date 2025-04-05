package com.example.demo.controller;

import java.net.http.HttpRequest;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.IUserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/trainer")
public class TrainerController {
	@Autowired
	IUserService iUserService;

	@GetMapping("/getUsersByTrainer/{trainerId}")
	public ResponseEntity<List<User>> getAllUsers(Long trainerId) {
		return ResponseEntity.ok(iUserService.getUserDetailsByTrainerId(trainerId));

	}

}
