package ru.tinkoff.edu.java.bot.tgbot.commands.impl;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.Getter;
import ru.tinkoff.edu.java.bot.tgbot.commands.interfaces.BaseCommand;
import ru.tinkoff.edu.java.bot.tgbot.core.SenderMessage;

@Getter
public class StartCommand implements BaseCommand {
    private static final String GREETING = "Добро пожаловать!\nДля списка всех команд напишите команду /help";

    @Override
    public SendMessage execute(Message message) {
        return SenderMessage.create(message, GREETING);
    }
}
