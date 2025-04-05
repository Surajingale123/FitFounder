package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.MemberShipPlan;
import com.example.demo.model.MemberShip;
import com.example.demo.repository.IMemberShipRepo;
import com.example.demo.service.IMemberShipService;


@Service
public class MemberShipPlanService implements IMemberShipService {

	@Autowired
	private IMemberShipRepo iMemberShipRepo;
	
	@Override
	public String addPlan(MemberShipPlan membershipPlan) {
		// TODO Auto-generated method stub
		MemberShip memberShip = new MemberShip();
		memberShip.setPlanName(membershipPlan.getPlanName());
		memberShip.setFeature(membershipPlan.getFeature());
		memberShip.setDuration(membershipPlan.getDuration());
		memberShip.setPrice(membershipPlan.getPrice());
		iMemberShipRepo.save(memberShip);
		return "MemberShip plan submitted successfully.";
	}

	@Override
	public List<MemberShip> getAllPlans() {
		// TODO Auto-generated method stub
		return iMemberShipRepo.getActivePlans();
	}

	@Override
	public MemberShip getPlanById(Long id) {
		// TODO Auto-generated method stub
		return iMemberShipRepo.findById(id).get();
	}

	@Override
	public Boolean updatePlanById(Long id, MemberShip membershipPlan) {
		// TODO Auto-generated method stub
		Optional<MemberShip> dbMemberShip =iMemberShipRepo.findById(id);
		if(!dbMemberShip.isPresent()) {
			return false;
		}
		if(membershipPlan.getPlanName() !=null ) {
		dbMemberShip.get().setPlanName(membershipPlan.getPlanName());
		}
		if(membershipPlan.getFeature() !=null ) {
		dbMemberShip.get().setFeature(membershipPlan.getFeature());
		}
		if(membershipPlan.getDuration() !=0 ) {
		dbMemberShip.get().setDuration(membershipPlan.getDuration());
		}
		if(membershipPlan.getPrice() !=0 ) {
		dbMemberShip.get().setPrice(membershipPlan.getPrice());
		}
		iMemberShipRepo.save(dbMemberShip.get());
		return true;
	}

	@Override
	public Boolean deletePlanDetailsById(Long id) {
		// TODO Auto-generated method stub
		Optional<MemberShip> memberShip = iMemberShipRepo.findById(id);
		if(!memberShip.isPresent()) {
			return false;
		}
		memberShip.get().setIsActive(false);
		iMemberShipRepo.save(memberShip.get());
		return null;
	}

	@Override
	public MemberShip getPlansId(Long id) {
		// TODO Auto-generated method stub
		return iMemberShipRepo.findById(id).get();
		
	}

}
