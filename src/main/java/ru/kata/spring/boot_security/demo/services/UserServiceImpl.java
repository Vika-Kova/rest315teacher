package ru.kata.spring.boot_security.demo.services;

import org.apache.catalina.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserServise {
    private   UserRepository userRepository;
    private  PasswordEncoder passwordEncoder;

    public UserServiceImpl() {
    }

    @Override
    public void saveUser(User user) {

    }

    @Override
    public void updateUser(int id, User user) {

    }

    @Override
    public void deleteUser(int id) {

    }

    @Override
    public User showUser(int id) {
        return null;
    }

    @Override
    public List<User> indexUser() {
        return null;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }
}
