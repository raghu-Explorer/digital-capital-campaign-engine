package com.ventureseed.auth.config;

import com.ventureseed.auth.model.Role;
import com.ventureseed.auth.model.User;
import com.ventureseed.auth.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.count() == 0) {
                // Seed Creator
                User creator = new User(
                        "Elena Rostova",
                        "creator@ventureseed.com",
                        passwordEncoder.encode("password123"),
                        Role.ROLE_CREATOR
                );
                userRepository.save(creator);

                // Seed Backer
                User backer = new User(
                        "David Vance",
                        "backer@ventureseed.com",
                        passwordEncoder.encode("password123"),
                        Role.ROLE_BACKER
                );
                userRepository.save(backer);

                // Seed second Creator (Startup Founder)
                User startup = new User(
                        "Dr. Aris Thorne",
                        "founder@quantumleaf.io",
                        passwordEncoder.encode("password123"),
                        Role.ROLE_CREATOR
                );
                userRepository.save(startup);

                // Seed second Backer (Angel Investor)
                User angel = new User(
                        "Sophia Chen",
                        "investor@summitcapital.com",
                        passwordEncoder.encode("password123"),
                        Role.ROLE_BACKER
                );
                userRepository.save(angel);
            }
        };
    }
}
