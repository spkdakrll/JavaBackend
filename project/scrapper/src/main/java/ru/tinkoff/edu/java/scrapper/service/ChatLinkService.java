package ru.tinkoff.edu.java.scrapper.service;

import java.util.List;

public interface ChatLinkService {
    List<Long> getAllLink(long chatId);
    List<Long> getAllChat(long linkId);
}
