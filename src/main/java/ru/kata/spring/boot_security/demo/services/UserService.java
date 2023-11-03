package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
@Transactional
    void saveUser(User user);

    void deleteUserById(Long id);

    Optional<User> showUserById(Long id);

    void updateUser(Long id, User user);

    Optional<User> findByEmail(String email);

}







