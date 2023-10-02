package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.User;

import javax.annotation.PostConstruct;
import java.util.List;

public interface UserService extends UserDetails, UserDetailsService {

    void saveUser(User user);

    User showUser(Long id);//findUserById

    List<User> indexUser();//list user

    void updateUser( User user,Long id);




    void deleteUser(Long id);


    User findByUsername(String username);


    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}

