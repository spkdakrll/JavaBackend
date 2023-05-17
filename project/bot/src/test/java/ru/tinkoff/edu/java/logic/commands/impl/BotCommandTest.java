package ru.tinkoff.edu.java.logic.commands.impl;

import com.pengrad.telegrambot.request.SendMessage;
import org.junit.jupiter.api.Test;
import ru.tinkoff.edu.java.bot.tgbot.commands.impl.HelpCommand;
import ru.tinkoff.edu.java.bot.tgbot.commands.impl.UntrackCommand;
import ru.tinkoff.edu.java.bot.tgbot.commands.enums.InitBaseCommands;
import ru.tinkoff.edu.java.logic.commands.abstracts.CommandTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;


public class BotCommandTest extends CommandTest {
    @Test
    void descriptionCommands_EnumBaseCommandDescription() {
        StringBuilder text = new StringBuilder("Список всех команд:");
        Arrays.stream(InitBaseCommands.values()).forEach((value) -> text.append("\n").append(value));
        SendMessage sendMessage = new HelpCommand().execute(message);
        assertMessage(sendMessage, text.toString());

    }

    @Test
    void callTrack_TextReply() {
        String answer = "Напишите ссылку, отслеживание которой хотите прекратить";
        SendMessage sendMessage = new UntrackCommand().execute(message);
        assertAll(
                () -> assertEquals(sendMessage.getParameters().get("text"), answer),
                () -> assertEquals(sendMessage.getParameters().get("chat_id"), chat_id),
                () -> assertEquals(sendMessage.getParameters().get("reply_markup").getClass().getSimpleName(), "ForceReply")
        );
    }


}