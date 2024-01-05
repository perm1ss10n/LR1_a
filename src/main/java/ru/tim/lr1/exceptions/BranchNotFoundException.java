package ru.tim.lr1.exceptions;

public class BranchNotFoundException extends Exception {
    public BranchNotFoundException(Integer id){
        super("Филиал с id " + id + " не найден.");
    }
}
