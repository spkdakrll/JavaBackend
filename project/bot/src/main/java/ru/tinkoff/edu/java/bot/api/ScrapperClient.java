package ru.tinkoff.edu.java.bot.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.bot.configuration.dto.request.AddLinkRequest;
import ru.tinkoff.edu.java.bot.configuration.dto.resonse.ListLinksResponse;
import ru.tinkoff.edu.java.bot.configuration.dto.request.RemoveLinkRequest;
import ru.tinkoff.edu.java.bot.configuration.scrapper.ScrapperConfiguration;

@RequiredArgsConstructor
public class ScrapperClient {
    private final WebClient webClient;

    public ScrapperClient() {
        webClient = WebClient.builder().baseUrl(ScrapperConfiguration.getBaseUrl()).build();
    }

    public ListLinksResponse getListLinks(Long chatId) {
        return webClient.get()
                .uri("links")
                .header("Tg-Chat-Id", String.valueOf(chatId))
                .retrieve().bodyToMono(ListLinksResponse.class).block();
    }

    public AddLinkRequest postLink(Long chatId) {
        return webClient.post()
                .uri("links")
                .header("Tg-Chat-Id", String.valueOf(chatId))
                .retrieve().bodyToMono(AddLinkRequest.class).block();
    }

    public RemoveLinkRequest deleteLink(Long chatId) {
        return webClient.delete()
                .uri("links")
                .header("Tg-Chat-Id", String.valueOf(chatId))
                .retrieve().bodyToMono(RemoveLinkRequest.class).block();
    }

    public void postChatId(Long chatId) {
        webClient.post().uri("tg-chat/{chatId}", chatId).retrieve().bodyToMono(RemoveLinkRequest.class).block();
    }

    public void deleteChatId(Long chatId) {
        webClient.delete().uri("tg-chat/{chatId}", chatId).retrieve().bodyToMono(RemoveLinkRequest.class).block();
    }
}
