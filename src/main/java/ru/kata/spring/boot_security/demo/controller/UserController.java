package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "")
    public String showUserPage(Model model, Principal principal) {
        model.addAttribute("user", userService.findByEmail(principal.getName()));//1
       // Optional<User> user = userService.findByUserEmail(principal.getName());
       // model.addAttribute("user", user);
     //   model.addAttribute("userRoles", user.getClass());
        
        return "user-page";
    }
}

