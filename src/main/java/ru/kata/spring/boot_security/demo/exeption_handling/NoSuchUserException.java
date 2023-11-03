package ru.kata.spring.boot_security.demo.exeption_handling;

public class NoSuchUserException  extends RuntimeException{//отвечает за сообщение в ошибке
    public NoSuchUserException(String message) {
        super(message);
    }
    // переопределим его конструктор который принимает String message
}
