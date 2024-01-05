package ru.tim.lr1.exceptions;

public class AgentNotFoundException extends Exception {
    public AgentNotFoundException(Integer id){
        super("Агент с id " + id + " не найден.");
    }
}
