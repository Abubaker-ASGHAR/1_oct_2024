package com.example.spring_security_basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
        public String home(Model model) {
        model.addAttribute("message", "welcome to Home Page");
        return "home";
    }
    @GetMapping("/secured")
    public String securedPage(Model model) {
        model.addAttribute("message", "this is a Secured Page");
return "secured";
    }


}
