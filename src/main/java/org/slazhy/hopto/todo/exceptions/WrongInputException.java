package org.slazhy.hopto.todo.exceptions;

public class WrongInputException extends RuntimeException {
    public WrongInputException(String s) {
        super(s);
    }
}
