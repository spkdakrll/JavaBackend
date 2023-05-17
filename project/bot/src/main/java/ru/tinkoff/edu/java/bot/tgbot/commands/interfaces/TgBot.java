package ru.tinkoff.edu.java.bot.tgbot.commands.interfaces;

import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;

import java.util.List;

public interface TgBot extends UpdatesListener{
    void start();

    @Override
    int process(List<Update> updates);

    void close();
}
