package ru.tinkoff.edu.core.command.impl;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.AllArgsConstructor;
import ru.tinkoff.edu.configuration.client.ScrapperClient;
import ru.tinkoff.edu.controller.handler.response.ListLinksResponse;
import ru.tinkoff.edu.core.command.Command;

@AllArgsConstructor
public class ListCommand implements Command {
    private final ScrapperClient client;

    @Override
    public String command() {
        return "list";
    }

    @Override
    public String description() {
        return "Показать список отслеживаемых ссылок";
    }

    @Override
    public SendMessage process(Update update) {
        ListLinksResponse response = client.getListLinks(update.message().chat().id());
        if (response.getLinks() == null) {
            return new SendMessage(update.message().chat().id(), "Ошибка при извлечении данных, попробуйте еще раз");
        }
        if (response.getSize() == 0) {
            return new SendMessage(update.message().chat().id(), "Список отслеживаемых ссылок пуст!");
        }
        return new SendMessage(update.message().chat().id(), getMessageText(response))
                .parseMode(ParseMode.HTML);
    }

    private String getMessageText(ListLinksResponse list) {
        StringBuilder result = new StringBuilder("Список отслеживаемых ссылок:\n");
        for (int i = 0; i < list.getSize(); i++) {
            result.append(i + 1).append(". ").append(list.getLinks().get(i).getUrl().toString()).append("\n");
        }
        return result.toString();
    }
}
