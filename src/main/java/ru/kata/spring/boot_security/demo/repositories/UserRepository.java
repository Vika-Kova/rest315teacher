package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.models.User;
import org.springframework.stereotype.Repository;

@Repository
//@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
//Интерфейс JpaRepository предоставляет набор стандартных методов (findBy,save,deleteById и др.) для работы с БД.