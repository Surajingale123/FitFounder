package com.example.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.IUserRepository;


@SpringBootApplication
public class FitFounderGymServiceApplication implements CommandLineRunner  {
	@Autowired
	private IUserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(FitFounderGymServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Optional<User> adminAccount = userRepository.findByRole(Role.ADMIN);

		if (adminAccount.isEmpty()) {
			User user = new User();

			user.setFirstName("admin");
			user.setLastName("admin");
			user.setEmail("admin@ctpl.in");
			user.setPhoneNo(879465L);
			user.setAddress("Gym address");
			user.setIsAdmin(true);
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			user.setRole(Role.ADMIN);

			userRepository.save(user);
		}
	}
}


