package ru.kata.spring.boot_security.demo.models;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

import static java.lang.Long.*;


@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Column(name = "role")
    private String name;

    public Role() {

    }
    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    public void setId(Integer id) {
        this.id = Long.valueOf(id);

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

    @Override
    public String getAuthority() {
        return getRole();
    }

    private String getRole() {
        return getRole();
    }


    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }



    //связь с ролями
   // @ManyToMany(mappedBy = "roles")
   // private List<User> users;

   // public List<User> getUsers() {
     //   return users;
   // }


}
