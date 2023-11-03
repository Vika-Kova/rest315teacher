package ru.kata.spring.boot_security.demo.exeption_handling;

public class UserIncorrectData {//объект этого класса будет преобразовы-ся в json и выв. на экран
    private String info;

    public UserIncorrectData() {
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
