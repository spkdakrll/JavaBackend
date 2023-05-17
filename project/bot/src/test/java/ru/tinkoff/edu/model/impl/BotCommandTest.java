package ru.tinkoff.edu.model.impl;

import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.tinkoff.edu.core.command.impl.ListCommand;
import ru.tinkoff.edu.configuration.client.ScrapperClient;
import ru.tinkoff.edu.controller.handler.response.LinkResponse;
import ru.tinkoff.edu.controller.handler.response.ListLinksResponse;
import ru.tinkoff.edu.core.factory.MessageProcessor;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BotCommandTest {
    @Mock
    private ScrapperClient client;
    @Mock
    private Update update;
    @Mock
    private Message message;
    @Mock
    private Chat chat;
    private MessageProcessor messageProcessor;

    @BeforeEach
    public void setUp() {
        messageProcessor = new MessageProcessor(new ListCommand(client));
    }

    @Test
    public void processInvalidCommand() {
        Long chatId = 123456L;

        when(update.message()).thenReturn(message);
        when(message.text()).thenReturn("/invalid");
        when(message.chat()).thenReturn(chat);
        when(chat.id()).thenReturn(chatId);

        SendMessage message = messageProcessor.processCommand(update);

        Assertions.assertEquals(chatId, message.getParameters().get("chat_id"));
        Assertions.assertEquals("Неизвестная команда", message.getParameters().get("text"));
    }
}
