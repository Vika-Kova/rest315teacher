package ru.kata.spring.boot_security.demo.models;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
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

    public Role(Set<User> userSet) {
        this.userSet = userSet;
    }

    public Role(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName().substring(getName().indexOf('-') + 1);
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}


