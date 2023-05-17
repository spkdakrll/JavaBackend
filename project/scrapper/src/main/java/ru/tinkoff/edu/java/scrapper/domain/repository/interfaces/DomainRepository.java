package ru.tinkoff.edu.java.scrapper.domain.repository.interfaces;

import ru.tinkoff.edu.java.scrapper.controllers.handler.exceptions.BadEntityException;
import ru.tinkoff.edu.java.scrapper.controllers.handler.exceptions.DuplicateUniqueFieldException;
import ru.tinkoff.edu.java.scrapper.controllers.handler.exceptions.EmptyResultException;
import ru.tinkoff.edu.java.scrapper.domain.entities.DomainData;

import java.util.List;

public interface DomainRepository {
    void add(DomainData domainData) throws BadEntityException, DuplicateUniqueFieldException;
    void remove(long id);
    void remove(String name);
    DomainData getByName(String name) throws EmptyResultException;
    List<DomainData> findAll();
}
