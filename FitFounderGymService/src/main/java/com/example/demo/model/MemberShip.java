package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "membershipplan")
public class MemberShip {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
private String PlanName;
private int Price ;
private float Duration;
private String Feature;
private Boolean isActive;
}
