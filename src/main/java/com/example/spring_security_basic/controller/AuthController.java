package com.example.spring_security_basic.controller;

import com.example.spring_security_basic.model.User;
import com.example.spring_security_basic.service.UserService;
import  org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private  UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password, Model model) {

        try {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            userService.saveUser(user);
            model.addAttribute("message", "Registered Successfully");
            return "login";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }

    }
}


