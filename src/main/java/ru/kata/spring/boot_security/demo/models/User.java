package ru.kata.spring.boot_security.demo.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.*;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@Table(name = "users")//"t_user"

public class User implements UserDetails {
    @Id
    @NotEmpty()
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    @NotEmpty()
    @Size(min = 2, message = "Не меньше 5 знаков")
    private String username;


    @Column(name = "password")
    @NotEmpty
    @Size(min = 2, message = "Не меньше 5 знаков")
    private String password;

    @Column(name = "email")
    @NotEmpty()
    private String email;

    //Список ролей связан с пользователем отношением многие ко многим (один пользователь
    //может иметь несколько ролей с одной стороны и у одной роли может быть несколько пользователей с другой)
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))

    //private Set<Role> roleList = new HashSet<>();
    private Collection<Role> roles;

    public User() {//Конструктор по умолчанию нужен для Spring
    }

    public User(Long id, String username, String password, String email, Collection<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;

    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //переопред. методы UserDetails
    // getAuthorities(), он возвращает список ролей пользователя.
    //Поэтому для остальных методов измените возвращаемое значение на true.

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }
}

// public String roleToString(){
//    StringBuilder sb = new StringBuilder();
//   for(Role role: roleList){
//       sb.append(role.getName().toLowerCase());
//sb.append(role.getNameRole()).append(" ");
//    }
//    return sb.toString();
//}

// public void setRoles(List<Role> adminRoles) {
// }
//}

//связь с ролями
//Здесь жадная загрузка, чтобы сразу грузились все дочерние зависимости юзера. fetch (извлечение)
// @ManyToMany(fetch = FetchType.EAGER)//(cascade = CascadeType.MERGE)
// @JoinTable(name = "user_roles",
//     joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),// колонка сущности User.
//    inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID"))







