package com.example.demo.service;

import java.util.List;


import com.example.demo.dto.MemberShipPlan;
import com.example.demo.model.MemberShip;

public interface IMemberShipService {

	String addPlan(MemberShipPlan membershipPlan);

	List<MemberShip> getAllPlans();
	
	MemberShip getPlanById(Long id);

	Boolean updatePlanById(Long id, MemberShip user);

	Boolean deletePlanDetailsById(Long id);

	MemberShip getPlansId(Long id);
    
}
