package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class Init {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public Init(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void initializedDataBase() {
        roleService.save(new Role(1L, "ROLE_USER"));
        roleService.save(new Role(2L, "ROLE_ADMIN"));
        Set<Role> adminRole = new HashSet<>();
        Set<Role> userRole = new HashSet<>();
        Set<Role> allRoles = new HashSet<>();
        adminRole.add(roleService.showUserById(1L));
        userRole.add(roleService.showUserById(2L));
        allRoles.add(roleService.showUserById(1L));
        allRoles.add(roleService.showUserById(2L));

        userService.saveUser(new User(1L, "Daw", "$2a$12$XzDsds6YAXlFbiBLolx0o.XfVfzCoC.AasmG6F6ylaajdhlPMuw3y", "XXXW@X.com", adminRole));
        userService.saveUser(new User(2L, "Pit", "$2a$12$NUA06mdsPJzIPC1M97aIv.V3Wxy.hHfBEVs8wQ6aJIvcK3VLUWpra", "userRole@.com", userRole));
        userService.saveUser(new User(3L, "Kat", "$2a$12$C2ceoHhE6vGhY4IQn.N/JO78LCL8UL.DAODXJWEA49ySzj75VgEpq", "allRoles@d.com", allRoles));
    }
}

