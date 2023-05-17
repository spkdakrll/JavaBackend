package ru.tinkoff.edu.java.scrapper.domain.repository.interfaces;

import ru.tinkoff.edu.java.scrapper.controllers.handler.exceptions.BadEntityException;
import ru.tinkoff.edu.java.scrapper.controllers.handler.exceptions.DuplicateUniqueFieldException;
import ru.tinkoff.edu.java.scrapper.domain.entities.ChatData;

import java.util.List;

public interface ChatRepository {
    void add(ChatData chatData) throws DuplicateUniqueFieldException, BadEntityException;
    void remove(long id);
    List<ChatData> findAll();
}
