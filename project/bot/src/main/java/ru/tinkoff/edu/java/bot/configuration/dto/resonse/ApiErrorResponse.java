package ru.tinkoff.edu.java.bot.configuration.dto.resonse;


public record ApiErrorResponse(
        String description,
        String code,
        String exceptionName,
        String exceptionMessage,

        StackTraceElement[] stackTrace) {
}
