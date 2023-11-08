package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
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
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> showUserById(Long id) {
       // return userRepository.getOne(id);
        //По сути, getOne - это операция отложенной загрузки. Таким образом, вы получаете
        // только ссылку (прокси) на объект. Это означает, что доступ к БД фактически
        // не осуществляется. Только когда вы вызываете
        // его свойства, он запрашивает базу данных. findById выполняет вызов "охотно" /
        // немедленно при вашем вызове, таким образом, фактическая сущность полностью заполнена
        ///////
         return  userRepository.findById(id);
    }

    @Override
    @Transactional
    public void updateUser(Long id, User user) {
        user.setId(id);
        userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByUserName(email);//findByEmail(email);
    }
}








