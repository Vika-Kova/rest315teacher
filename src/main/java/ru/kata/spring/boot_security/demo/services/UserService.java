package ru.kata.spring.boot_security.demo.services;

import org.apache.catalina.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void saveUser(User user);

    void updateUser(int id, User user);

    void deleteUser(int id) ;


    User showUser(int id);

    List<User> indexUser();


    User findByUsername (String username);
}

