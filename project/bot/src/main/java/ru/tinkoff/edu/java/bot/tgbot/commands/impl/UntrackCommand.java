package ru.tinkoff.edu.java.bot.tgbot.commands.impl;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.Getter;
import ru.tinkoff.edu.java.bot.exceptions.InvalidLinkException;
import ru.tinkoff.edu.java.bot.exceptions.NotUniqueLinkException;
import ru.tinkoff.edu.java.bot.tgbot.commands.interfaces.BaseCommand;
import ru.tinkoff.edu.java.bot.tgbot.commands.interfaces.ReplyCommand;
import ru.tinkoff.edu.java.bot.tgbot.core.SenderMessage;

@Getter
public class UntrackCommand implements BaseCommand, ReplyCommand {
    public static final String REPLY = "Напишите ссылку, отслеживание которой хотите прекратить";
    private static final String FINISH_TEXT = "Ссылка успешно удалена из списка";

    @Override
    public SendMessage execute(Message message) {
        return SenderMessage.create(message, REPLY, true);
    }

    @Override
    public SendMessage executeReply(Message message) {
        String text;
        try {
            ManagerCollection.remove(LinkValidation.validate(message.text()));
            text = FINISH_TEXT;
        } catch (NotUniqueLinkException | InvalidLinkException e) {
            text = e.getMessage();
        }
        return SenderMessage.create(message, text);
    }
}
