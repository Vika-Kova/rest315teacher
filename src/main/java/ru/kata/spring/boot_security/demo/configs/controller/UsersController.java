package ru.kata.spring.boot_security.demo.configs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

@Controller
@RequestMapping("/")
public class UsersController {

        private   UserService userService;

        // @Autowired
        public UsersController(UserService userService) {
            this.userService = userService;
        }
        public  UsersController(){

        }

        @GetMapping
        public String indexUser(Model model) {
            model.addAttribute("users", userService.indexUser());
            return "users/index";
        }

       // @GetMapping("users/{id}")//адреса для строки
    //    public String showUser(@PathVariable("id") int id, Model model) {
      //      model.addAttribute("user", userService.showUser(id));// userService.showUser(id));
      //      return "users/show";
    //    }

        @GetMapping("users/new")
        public String newUser(@ModelAttribute("user") User user) {
            return "users/new";
        }

        @PostMapping("/users")
        public String createUser(@ModelAttribute("user") User user) {
            userService.saveUser((org.apache.catalina.User) user);//?
            return "redirect:/";
        }

     //   @GetMapping("users/{id}/edit")
     //   public String editUser(Model model, @PathVariable("id") int id) {
       //     model.addAttribute("user", userService.showUser(id));
           // return "users/edit";
      //  }

       // @PatchMapping("users/{id}")
       // public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") int id) {
       //     userService.updateUser(id, (org.apache.catalina.User) user);
     //       return "redirect:/";
    //    }

       @DeleteMapping("users/{id}")
        public String deleteUser(@PathVariable("id") int id) {
           userService.deleteUser(id);
            return "redirect:/";
        }
    }

