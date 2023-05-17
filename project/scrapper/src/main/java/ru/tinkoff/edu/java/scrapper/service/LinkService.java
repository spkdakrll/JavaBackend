package ru.tinkoff.edu.java.scrapper.service;

import ru.tinkoff.edu.java.scrapper.controllers.handler.exceptions.BadEntityException;
import ru.tinkoff.edu.java.scrapper.controllers.handler.exceptions.DuplicateUniqueFieldException;
import ru.tinkoff.edu.java.scrapper.controllers.handler.exceptions.EmptyResultException;
import ru.tinkoff.edu.java.scrapper.controllers.handler.exceptions.ForeignKeyNotExistsException;
import ru.tinkoff.edu.java.scrapper.domain.entities.LinkData;

import java.net.URI;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public interface LinkService {
    LinkData add(long chatId, URI url) throws EmptyResultException, ForeignKeyNotExistsException, BadEntityException, DuplicateUniqueFieldException;
    LinkData remove(long chatId, URI url) throws EmptyResultException;
    Collection<LinkData> listAll(long tgChatId);

    void updateDataChanges(Map<String, String> dataChanges, Long linkId);

    Optional<LinkData> getOldestUpdateLink();
}
