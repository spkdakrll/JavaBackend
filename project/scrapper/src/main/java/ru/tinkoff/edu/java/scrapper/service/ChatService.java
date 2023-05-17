package ru.tinkoff.edu.java.scrapper.service;

import ru.tinkoff.edu.java.scrapper.controllers.handler.exceptions.BadEntityException;
import ru.tinkoff.edu.java.scrapper.controllers.handler.exceptions.DuplicateUniqueFieldException;

public interface ChatService {
        void register(Long tgChatId) throws DuplicateUniqueFieldException, BadEntityException;
        void unregister(Long tgChatId);
}
