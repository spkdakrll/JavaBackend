package ru.tinkoff.edu.java.bot.tgbot.commands.interfaces;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;

public interface ReplyCommand {
    SendMessage executeReply(Message message);
}
