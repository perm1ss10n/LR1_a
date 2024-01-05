package ru.tim.lr1.exceptions;

public class InsuranceTypeNotFoundException extends Exception {
    public InsuranceTypeNotFoundException(Integer id){
        super("Вид страхования с id " + id + " не найден.");
    }
}
