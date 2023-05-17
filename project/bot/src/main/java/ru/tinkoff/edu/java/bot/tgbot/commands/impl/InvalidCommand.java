package ru.tinkoff.edu.java.bot.tgbot.commands.impl;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import ru.tinkoff.edu.java.bot.tgbot.core.SenderMessage;

public class InvalidCommand {
    private static final String TEXT = "Такой команды не существует.\nПопробуйте воспользоваться командой /help";
    public static SendMessage execute(Message message) {
        return SenderMessage.create(message, TEXT);
    }
}
