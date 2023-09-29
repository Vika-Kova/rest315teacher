package ru.kata.spring.boot_security.demo.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.kata.spring.boot_security.demo.models.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User,Long> {
    // @Query("select u from User u join fetch u.roleList where u.username=:username")//?
     Optional<User> findByUsername(String username);
//User findByUsername(String username);
}
//Интерфейс JpaRepository предоставляет набор стандартных методов (findBy, save, deleteById и др.) для работы с БД.