package ru.tinkoff.edu.java.scrapper.domain.repository.interfaces;

import ru.tinkoff.edu.java.scrapper.controllers.handler.exceptions.BadEntityException;
import ru.tinkoff.edu.java.scrapper.controllers.handler.exceptions.DuplicateUniqueFieldException;
import ru.tinkoff.edu.java.scrapper.controllers.handler.exceptions.ForeignKeyNotExistsException;
import ru.tinkoff.edu.java.scrapper.domain.entities.ChatLinkData;

import java.util.Collection;
import java.util.List;

public interface ChatLinkRepository {
    void add(ChatLinkData chatLinkData) throws DuplicateUniqueFieldException, BadEntityException, ForeignKeyNotExistsException;
    void remove(long chatId, long linkId);
    Collection<ChatLinkData> findAll();
    List<ChatLinkData> findAllByChatId(long chatId);
    List<ChatLinkData> getAllChatByLink(long linkId);
}
