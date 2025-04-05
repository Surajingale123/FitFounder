package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.MemberShip;

@Repository
public interface IMemberShipRepo extends JpaRepository<MemberShip, Long> {

	@Query("SELECT u FROM MemberShip u WHERE u.isActive=true")
	//@Query("SELECT u FROM MemberShip u ")
	//@Query("SELECT u FROM MemberShip u")
	List<MemberShip> getActivePlans();
 
}
