package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Role;
import com.example.demo.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByEmail(String email);

	Optional<User> findByRole(Role role);

	
	@Query("SELECT u FROM User u WHERE u.isUser=true")
	List<User> getuserList();

	@Query("SELECT u FROM User u WHERE u.isTrainer=true")
	List<User> gettrainerList();

	@Query("SELECT u FROM User u WHERE u.id=id AND u.isActive=true")
	User getByIdANDISActive(@Param("id") Integer id);
	
	@Query("SELECT u FROM User u WHERE u.isActive=true AND u.trainerId=trainerId")
	List<User> getUserListByTrainer(@Param("trainerId") Long trainerId);
	
	@Query("SELECT u FROM User u WHERE u.isUser=true AND u.isActive=true")
	List<User> findAllANDISUser();

	 
}
