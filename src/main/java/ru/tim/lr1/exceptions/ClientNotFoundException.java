package ru.tim.lr1.exceptions;

public class ClientNotFoundException extends Exception {
    public ClientNotFoundException(Integer id){
        super("Клиент с id " + id + " не найден.");
    }
}
