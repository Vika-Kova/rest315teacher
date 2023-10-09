package ru.kata.spring.boot_security.demo.models;

import javax.persistence.*;
import java.util.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@Table(name = "users")

public class User implements UserDetails {
    // UserDetailsService интерфейс, в котором содержится loadUserByUsername(username строка)
    // способ искать UserDetails для данного пользователя.
    //UserDetails представляет собой объект пользователя, прошедшего проверку подлинности

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    // private Collection<Role> roles;
private Set<Role>roles;
    //Список ролей связан с пользователем отношением многие ко многим (один пользователь
    //может иметь несколько ролей с одной стороны и у одной роли может быть несколько пользователей с другой)
    public User() {
    }

    public User(String firstName, String lastName, String username, String password,Set <Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    /////////
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    /////////

    //public Collection<Role> getRoles() {
       // return roles;
   // }

  //  public void setRoles(Collection<Role> roles) {
//this.roles = roles;
   // }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {//аккаунт не просрочен
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {    //пароль рабочий
        return true;
    }

    @Override
    public boolean isEnabled() {//аккаунт рабочий
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

}










