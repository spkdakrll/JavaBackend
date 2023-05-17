package ru.tinkoff.edu.java.scrapper.controllers.handler.exceptions;

public class DuplicateUniqueFieldException extends Exception{
    public DuplicateUniqueFieldException(String message) {
        super(message);
    }
}