package ru.tinkoff.edu.java.bot.tgbot.commands.impl;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.Getter;
import ru.tinkoff.edu.java.bot.tgbot.commands.interfaces.BaseCommand;
import ru.tinkoff.edu.java.bot.tgbot.core.SenderMessage;

import java.net.URI;
import java.util.Set;

@Getter
public class ListCommand implements BaseCommand {
    private static final String LINKS_MISSING = "Пока нет ни одной отслеживаемой ссылки.\nДобавить ссылку можно с помощью команды /track";

    @Override
    public SendMessage execute(Message message) {
        Set<URI> links = ManagerCollection.getLinks();
        String text;
        if (links.isEmpty()) {
            text = LINKS_MISSING;
        } else {
            StringBuilder textLinks = new StringBuilder("Список всех ссылок:");
            for (URI uri : links) {
                textLinks.append("\n");
                textLinks.append(uri);
            }
            text = textLinks.toString();
        }
        return SenderMessage.create(message, text);
    }
}
