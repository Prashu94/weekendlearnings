package io.spring.learning;

import io.spring.learning.users.User;
import io.spring.learning.users.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServerApplication.class, args);
	}

	@Bean
	public ApplicationRunner dataLoader(UserRepository userRepository, PasswordEncoder encoder){
		return args -> {
			userRepository.save(
					new User("habuma", encoder.encode("password"), "ROLE_ADMIN"));
			userRepository.save(
					new User("tacochef", encoder.encode("password"), "ROLE_ADMIN"));
		};
	}

}
