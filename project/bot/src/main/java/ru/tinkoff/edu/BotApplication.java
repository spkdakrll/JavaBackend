package ru.tinkoff.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.tinkoff.edu.configuration.client.ScrapperClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.tinkoff.edu.configuration.ApplicationConfig;
import ru.tinkoff.edu.core.command.impl.*;
import ru.tinkoff.edu.core.factory.Bot;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfig.class)
public class BotApplication
{

    public static void main( String[] args )
    {
        var ctx = SpringApplication.run(BotApplication.class, args);
        ApplicationConfig config = ctx.getBean(ApplicationConfig.class);
        ScrapperClient client = ctx.getBean(ScrapperClient.class);
        Bot bot = new Bot(
                config.token(),
                new StartCommand(client),
                new ListCommand(client),
                new TrackCommand(client),
                new UntrackCommand(client),
                new HelpCommand());
    }

    @Bean
    public ScrapperClient scrapperClient() {
        return new ScrapperClient();
    }
}
