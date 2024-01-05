package ru.tim.lr1.exceptions;

public class ContractNotFoundException extends Exception {
    public ContractNotFoundException(Integer id){
        super("Контракт с id " + id + " не найден.");
    }
}
