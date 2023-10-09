package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.*;

//UserService. Содержит методы для бизнес-логики приложения. Этот класс реализует
// интерфейс UserDetailsService
//   (необходим для Spring Security), в котором нужно переопределить один метод loadUserByUsername().
@Service
public class UserServiceImpl implements UserService {//UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User showUserById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    @Transactional
    public void update(Long id, User user) {
        user.setId(id);
        userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        // return userRepository.findByUsername(email);
        return userRepository.findByUsername(email);
    }

   // @Override
   // @Transactional
   // public UserDetails loadUserByUsername(String email) {
    //  User user = userRepository.findByUsername(email);
    //   if (user == null) {
    //   throw new UsernameNotFoundException(String.format("User '%s' not found", email));
     //   }
    //return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
   // }
}

