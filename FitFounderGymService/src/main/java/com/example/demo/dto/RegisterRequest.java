package com.example.demo.dto;


import com.example.demo.model.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private Long phoneNo;
    private String email;
    
    private String password;
    private String address;
    private Double height;
    private Double weight;
    private Double Age;
    private Boolean isAdmin=false;
    private Boolean isTrainer=false;
    private Boolean isUser=false;
    private Double bmi;
    private Role role;
    private Long membership;
    
}
