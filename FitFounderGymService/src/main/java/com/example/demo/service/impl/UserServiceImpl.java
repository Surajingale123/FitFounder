package com.example.demo.service.impl;

import com.example.demo.model.MemberShip;
import com.example.demo.model.User;
import com.example.demo.repository.IUserRepository;
import com.example.demo.service.IUserService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final IUserRepository userRepository;

    @Override 
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }

//	@Override
//	public List<User> getUserDetails() {
//		// TODO Auto-generated method stub
//	 userRepository.findAllANDISUser();
//		 System.out.println("Fetched users: " + user);  
//		 return user;
//	}
    @Override
    public List<User> getUserDetails() {
        List<User> users = userRepository.findAllANDISUser(); 
        if (users != null && !users.isEmpty()) {
            System.out.println("Fetched users: " + users);
        } else {
            System.out.println("No users found.");
        }
        return users;
    }


	@Override
	public User getUserDetailsById(Integer id) {
		// TODO Auto-generated method stub
		return userRepository.getByIdANDISActive(id);
	}
	
	
	
	@Override
	public String updateUserDetailsById(Integer id, User user) {
		 Optional<User> dbUser=userRepository.findById(id);
		 if(dbUser.isPresent() && id.equals(dbUser.get().getId()))
		 {
			 User updateUser = dbUser.get();
			 updateUser.setFirstName(Optional.ofNullable(user.getFirstName()).isPresent()?user.getFirstName():updateUser.getFirstName());
			 updateUser.setLastName(Optional.ofNullable(user.getLastName()).isPresent()?user.getLastName():updateUser.getLastName());
			 updateUser.setAddress(Optional.ofNullable(user.getAddress()).isPresent()?user.getAddress():updateUser.getAddress());
			 updateUser.setAge(Optional.ofNullable(user.getAge()).isPresent()?user.getAge():updateUser.getAge());
			 updateUser.setBmi(Optional.ofNullable(user.getBmi()).isPresent()?user.getBmi():updateUser.getBmi());
			 updateUser.setEmail(Optional.ofNullable(user.getEmail()).isPresent()?user.getEmail():updateUser.getEmail());
			 updateUser.setHeight(Optional.ofNullable(user.getHeight()).isPresent()?user.getHeight():updateUser.getHeight());
			 if(user.getWeight()!=null ) {
				 updateUser.setWeight(user.getWeight());
			 }
			 if(user.getPhoneNo()!=null ) {
			 updateUser.setPhoneNo(user.getPhoneNo());
			 }
			 if(user.getPassword()!=null) {
			 updateUser.setPassword(user.getPassword());
			 }
			 if(user.getFirstName()!=null) {
				 updateUser.setFirstName(user.getFirstName());
			 }
			 if(user.getLastName()!=null) {
				 updateUser.setLastName(user.getLastName());
			 }
			 if(user.getAddress()!=null) {
				 updateUser.setAddress(user.getAddress());
			 }
			 if(user.getAge()!=null) {
				 updateUser.setAge(user.getAge());
			 }
			 if(user.getEmail()!=null) {
				 updateUser.setEmail(user.getEmail());
			 }
			 if(user.getHeight()!=null) {
				 updateUser.setHeight(user.getHeight());
			 }
			 if(user.getTrainerId() !=null)
			 {
				 updateUser.setTrainerId(user.getTrainerId());
			 }
			 userRepository.save(dbUser.get());
		 }
		 
		 return "User details Updated Successfully";
	}

	@Override
	public List<User> getTrainerDetails() {
		return userRepository.gettrainerList();
	}

	@Override
	public String deleteUserDetailsById(Integer id) {
		 Optional<User> dbUser=userRepository.findById(id);
		 if(dbUser.isPresent())
		 {
			 dbUser.get().setIsActive(false);
			 userRepository.save(dbUser.get());
		 }
		return "User details Deleted Successfully";
	}

	@Override
	public List<User> getUserDetailsByTrainerId(Long trainerId) {
		return userRepository.getUserListByTrainer(trainerId);
	}
    
}
