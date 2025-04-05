package com.example.demo.service.impl;

import com.example.demo.dto.JwtAuthenticationResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.User;
import com.example.demo.repository.IMemberShipRepo;
import com.example.demo.repository.IUserRepository;
import com.example.demo.service.IAuthenticationService;
import com.example.demo.service.IJwtService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {
@Autowired
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final IJwtService jwtService;
    @Autowired
    private final IMemberShipRepo iMemberShipRepo;

    public User register(RegisterRequest registerRequest) {
        User user = new User();

        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setEmail(registerRequest.getEmail());
        user.setPhoneNo(registerRequest.getPhoneNo());
        user.setAddress(registerRequest.getAddress());
        user.setAge(registerRequest.getAge());
        user.setHeight(registerRequest.getHeight());
        user.setWeight(registerRequest.getWeight());
        user.setIsUser(registerRequest.getIsUser()?true:false);
        user.setIsAdmin(registerRequest.getIsAdmin()?true:false);
        user.setIsTrainer(registerRequest.getIsTrainer()?true:false);
        user.setIsActive(true);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(registerRequest.getRole());
        if(registerRequest.getMembership()!=null)
        user.setMemberShip(iMemberShipRepo.findById(registerRequest.getMembership()).get());
        return userRepository.save(user);
    }

    public JwtAuthenticationResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword())
        );

        var user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));
        var jwt = jwtService.generateToken(user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        return jwtAuthenticationResponse;
    }

}
