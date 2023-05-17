package ru.tinkoff.edu.java.scrapper.controllers.handler.exceptions;

public class EmptyResultException extends Exception {
    public EmptyResultException(String message) {
        super(message);
    }
}
