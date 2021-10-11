package com.task1.bookingsys.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.task1.bookingsys.model.Role;
import com.task1.bookingsys.model.User;
import com.task1.bookingsys.repository.RoleRepo;
import com.task1.bookingsys.repository.UserRepo;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleRepo.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default as user role for newly created record!");
        roleRepo.save(userRole);

        User adminUser = new User();
        adminUser.setUsername("admin123");
        adminUser.setUsermail("admin@gmail.com");
        adminUser.setPassword(getEncodedPassword("a123"));
        
       
   
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userRepo.save(adminUser);

    }

    public User registerUser(User user) {
    	System.out.println(user.getPassword());
        Role role = roleRepo.findById("User").get();
        System.out.println(role.getRoleName());
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
       user.setPassword(getEncodedPassword(user.getPassword()));

        return userRepo.save(user);
    }

   public String getEncodedPassword(String password) {
       return passwordEncoder.encode(password);
   }
    
//    public User save(UserLoginDto user) {
//		User newUser = new User();
//		newUser.setUsername(user.getUsername());
//		newUser.setUsermail(user.getUsermail());
//		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
//		return repo.save(newUser);
//	}
}
