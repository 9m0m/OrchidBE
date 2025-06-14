package com.example.orchidservice.config;

import com.example.orchidservice.pojo.Account;
import com.example.orchidservice.pojo.Role;
import com.example.orchidservice.repository.AccountRepository;
import com.example.orchidservice.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializationConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initDatabase(RoleRepository roleRepository, AccountRepository accountRepository) {
        return args -> {
            // Create SuperAdmin role if not exists
            Role superAdmin = null;
            if (roleRepository.findById(1).isEmpty()) {
                superAdmin = new Role();
                superAdmin.setRoleId(1);
                superAdmin.setRoleName("SuperAdmin");
                superAdmin = roleRepository.save(superAdmin);
            } else {
                superAdmin = roleRepository.findById(1).get();
            }

            // Create Admin role if not exists
            if (roleRepository.findById(2).isEmpty()) {
                Role admin = new Role();
                admin.setRoleId(2);
                admin.setRoleName("Admin");
                roleRepository.save(admin);
            }

            // Create User role if not exists
            if (roleRepository.findById(3).isEmpty()) {
                Role user = new Role();
                user.setRoleId(3);
                user.setRoleName("User");
                roleRepository.save(user);
            }

            // Create default SuperAdmin account if not exists
            if (accountRepository.findByEmail("superadmin@gmail.com").isEmpty()) {
                Account defaultAdmin = new Account();
                defaultAdmin.setEmail("superadmin@gmail.com");
                defaultAdmin.setPassword(passwordEncoder.encode("123456"));
                defaultAdmin.setAccountName("SuperAdmin");
                defaultAdmin.setRole(superAdmin);
                accountRepository.save(defaultAdmin);
            }
        };
    }
}