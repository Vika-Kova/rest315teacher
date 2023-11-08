package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.exeption_handling.NoSuchUserException;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/adminApi")
public class AdminRestController {
    private final UserService userService;

    @Autowired
    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> list = userService.getAllUsers();
        return ResponseEntity.ok(list);
        //вернули список юзеров
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Optional<User>> showUserById(@PathVariable Long id) {//get получить по id
        Optional<User> user = userService.showUserById(id);

        if (!user.isPresent()) {//Zinaida@gmail.com
            throw new NoSuchUserException("There is no user with ID =" + id + " in Database");
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/user")//s
    public ResponseEntity<HttpStatus> createUser(@RequestBody User user) {//add//save?!
        // user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/user")
    public ResponseEntity<HttpStatus> updateUser(@PathVariable Long id, @RequestBody User user) {
        userService.updateUser(id, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

