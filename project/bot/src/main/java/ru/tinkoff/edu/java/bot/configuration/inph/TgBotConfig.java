package ru.tinkoff.edu.java.bot.configuration.inph;

import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.bot.configuration.application.ApplicationConfig;
import ru.tinkoff.edu.java.bot.tgbot.core.TgUpdaterLinkBot;
import ru.tinkoff.edu.java.bot.tgbot.commands.impl.InputHandler;

@Configuration
@RequiredArgsConstructor
public class TgBotConfig {
    private TgUpdaterLinkBot tgBot;
    private final ApplicationConfig config;
    private final InputHandler inputHandler;
    @Bean("tgBot")
    public TgUpdaterLinkBot getTgBot() {
        tgBot = new TgUpdaterLinkBot(config, inputHandler);
        tgBot.start();
        return tgBot;
    }

    @PreDestroy
    public void destroy() {
        tgBot.close();
    }
}