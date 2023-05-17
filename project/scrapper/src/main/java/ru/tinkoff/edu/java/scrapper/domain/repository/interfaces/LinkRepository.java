package ru.tinkoff.edu.java.scrapper.domain.repository.interfaces;

import ru.tinkoff.edu.java.scrapper.controllers.handler.exceptions.BadEntityException;
import ru.tinkoff.edu.java.scrapper.controllers.handler.exceptions.DuplicateUniqueFieldException;
import ru.tinkoff.edu.java.scrapper.controllers.handler.exceptions.EmptyResultException;
import ru.tinkoff.edu.java.scrapper.controllers.handler.exceptions.ForeignKeyNotExistsException;
import ru.tinkoff.edu.java.scrapper.domain.entities.LinkData;

import java.util.List;
import java.util.Map;

public interface LinkRepository {
    void add(LinkData link) throws BadEntityException, ForeignKeyNotExistsException, DuplicateUniqueFieldException;
    void remove(long id);
    void remove(String link);
    void updateUpdatedDateLink(long linkId);

    void updateDataChangesLink(Map<String, String> dataChanges, Long linkId);

    LinkData getByLink(String link) throws EmptyResultException;
    List<LinkData> findAll(String nameField, boolean orderASC, Integer limit);
    List<LinkData> getByLinkIds(List<Long> arrChatLink);
}
