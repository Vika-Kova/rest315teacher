package ru.kata.spring.boot_security.demo.models;

import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    //@Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Column(name = "role")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> userSet;

    public Role() {
    }
    public Role(Long id, String name, Set<User> userSet) {
        this.id = id;
        this.name = name;
        this.userSet = userSet;
    }

    public Role(Long id) {
        this.id = id;
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

    //интерфейс GrantedAuthority, в котором необходимо переопределить только один методgetAuthority()
    //  (возвращает имя роли). Имя роли должно соответствовать шаблону: «ROLE_ИМЯ», например, ROLE_USER
    @Override
    public String getAuthority() {
        return getName();
    }
    // getAuthorities(), он возвращает список ролей пользователя.
    @Override
    public String toString() {
        return getName().substring(getName().indexOf('_') + 1);
    }
    public Role(String name) {
        this.name = name;
    }
}
