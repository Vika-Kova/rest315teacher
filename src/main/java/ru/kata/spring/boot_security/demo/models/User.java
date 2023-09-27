package ru.kata.spring.boot_security.demo.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotEmpty()
    private String name; //для входа

    @NotEmpty
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    @NotEmpty
    @Size(min = 4)
    private String password;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Column(name = "role")
    private String role;

    public User() {//Конструктор по умолчанию нужен для Spring
    }

    public User(String name, String username, String password, String role) {//?
        this.name = name;
        this.password = password;
        this.role = role;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }


    //переопред. методы UserDetails
    //возdращает коллекцию прав пользователя
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return  getRoles();
    }

    public String getPassword() {
        return password;
    }

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
        //аккаунт не заблокирован
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


    //связь с ролями
    //Здесь жадная загрузка, чтобы сразу грузились все дочерние зависимости юзера. fetch (извлечение)
    @ManyToMany(fetch = FetchType.EAGER)//(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),// колонка сущности User.
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID"))
    //колонка второй  сущности, с которой связан User т.е. Role.
    private List<Role> roles = new ArrayList<>();

    // private List<Role> roles;

}






