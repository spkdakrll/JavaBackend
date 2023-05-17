package ru.tinkoff.edu.java.bot.tgbot.commands.impl;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.Getter;
import ru.tinkoff.edu.java.bot.tgbot.commands.enums.InitBaseCommands;
import ru.tinkoff.edu.java.bot.tgbot.commands.interfaces.BaseCommand;
import ru.tinkoff.edu.java.bot.tgbot.core.SenderMessage;

import java.util.Arrays;

@Getter
public class HelpCommand implements BaseCommand {
    @Override
    public SendMessage execute(Message message) {
        StringBuilder text = new StringBuilder("Список всех команд:");
        Arrays.stream(InitBaseCommands.values()).forEach(
                (value) -> text.append("\n").append(value)
        );
        return SenderMessage.create(message, text.toString());
    }
}
