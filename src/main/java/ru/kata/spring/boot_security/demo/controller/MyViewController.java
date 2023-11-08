package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.models.User;

@Controller
public class MyViewController {
    @GetMapping("/admin")
    public String showAllUser(Model model) {
       model.addAttribute("newUser", new User());
       return "admin-page";
    }

    @GetMapping("/user")
    public String showOneUser() {
        return "user-page";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
