package ru.tinkoff.edu.java.scrapper.controllers.handler.exceptions;

public class ForeignKeyNotExistsException extends Exception {
    public ForeignKeyNotExistsException(String message) {
        super(message);
    }
}
