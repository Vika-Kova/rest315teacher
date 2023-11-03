package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.exeption_handling.NoSuchUserException;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MyRestController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MyRestController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();//вернули список юзеров
        // List<User> allusers=userService.getAllUsers();
        //  return allusers;//вернули список юзеров
    }

    @GetMapping("/users/{id}")
    public Optional<User> showUserById(@PathVariable Long id) {//get получить по id
        Optional<User> user = userService.showUserById(id);
        // return  user;
        if (!user.isPresent()) {//Zinaida@gmail.com
            throw new NoSuchUserException("There is no user with ID =" + id + " in Database");
        }
        // return userService.showUserById(id);
        return user;
    }

    @PostMapping("/users")
    public User saveUser(@RequestBody User user) {//add
        // user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);//добавить в бд
        return user;
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {
        //userService.updateUser(id, user);
        userService.saveUser(user);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public String deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "User with id=" + id + "was deleted";
    }
}
