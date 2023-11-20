package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import javax.annotation.PostConstruct;

@Component
public class Init {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public Init(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    @Transactional
    public void InitInit() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        roleRepository.save(roleAdmin);
        roleRepository.save(roleUser);

        User user = new User();
        user.setFirstName("Zina");
        user.setLastName("Kovalevskaya");
        user.setEmail("Zinaj@jmail.com");
        user.setPassword(passwordEncoder.encode("adminZ"));
        user.getRoles().add(roleRepository.findRoleByRole("ROLE_ADMIN"));
        userRepository.save(user);

        user = new User();
        user.setFirstName("Max");
        user.setLastName("Ivanov");
        user.setEmail("Maxx@jmail.com");
        user.setPassword(passwordEncoder.encode("userMax"));
        user.getRoles().add(roleRepository.findRoleByRole("ROLE_USER"));
        userRepository.save(user);
    }
}

