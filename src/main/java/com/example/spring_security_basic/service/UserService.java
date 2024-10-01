package com.example.spring_security_basic.service;

import com.example.spring_security_basic.model.Role;
import com.example.spring_security_basic.model.User;
import com.example.spring_security_basic.repository.UserRepository;
import com.example.spring_security_basic.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private  UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole =roleRepository.findByName("ROLE_USER");
        if(userRole == null){
            userRole = new Role("ROLE_USER");
            roleRepository.save(userRole);
        }
        user.getRoles().add(userRole);
        userRepository.save(user);



    }
}
