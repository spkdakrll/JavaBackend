package ru.tinkoff.edu.java.scrapper.service.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.controllers.handler.exceptions.BadEntityException;
import ru.tinkoff.edu.java.scrapper.controllers.handler.exceptions.DuplicateUniqueFieldException;
import ru.tinkoff.edu.java.scrapper.domain.entities.ChatData;
import ru.tinkoff.edu.java.scrapper.domain.repository.interfaces.ChatRepository;
import ru.tinkoff.edu.java.scrapper.service.ChatService;

@Service
@RequiredArgsConstructor
public class JdbcChatService implements ChatService {
    private final ChatRepository chatRepository;
    @Override
    public void register(Long tgChatId) throws DuplicateUniqueFieldException, BadEntityException {
        ChatData chatData = new ChatData();
        chatData.setId(tgChatId);
        try {
            chatRepository.add(chatData);
        } catch (DuplicateKeyException e) {
            throw new DuplicateUniqueFieldException("Пользователь с таким id уже существует");
        }
    }

    @Override
    public void unregister(Long tgChatId) {
        chatRepository.remove(tgChatId);
    }
}
