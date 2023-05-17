package ru.tinkoff.edu.java.scrapper.service.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.domain.entities.ChatLinkData;
import ru.tinkoff.edu.java.scrapper.domain.repository.interfaces.ChatLinkRepository;
import ru.tinkoff.edu.java.scrapper.service.ChatLinkService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JdbcChatLinkService implements ChatLinkService {
    private final ChatLinkRepository chatLinkRepository;
    @Override
    public List<Long> getAllLink(long chatId) {
        List<ChatLinkData> arrChatLink = chatLinkRepository.findAllByChatId(chatId);
        return arrChatLink.stream().map(ChatLinkData::getLinkId).toList();
    }

    @Override
    public List<Long> getAllChat(long linkId) {
        List<ChatLinkData> arrChatLink = chatLinkRepository.getAllChatByLink(linkId);
        return arrChatLink.stream().map(ChatLinkData::getLinkId).toList();
    }
}
