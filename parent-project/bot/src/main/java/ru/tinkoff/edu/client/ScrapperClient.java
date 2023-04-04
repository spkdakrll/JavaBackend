package ru.tinkoff.edu.client;

import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.request.AddLinkRequest;
import ru.tinkoff.edu.request.RemoveLinkRequest;
import ru.tinkoff.edu.response.LinkResponse;
import ru.tinkoff.edu.response.ListLinksResponse;

import java.time.Duration;

public class ScrapperClient {
    private final String BASE_URL = "http://localhost:8081/";

    private final WebClient WEB_CLIENT;

    public ScrapperClient() {
        WEB_CLIENT = WebClient.builder()
                .baseUrl(BASE_URL)
                .build();
    }

    public ScrapperClient(String url) {
        WEB_CLIENT = WebClient.builder()
                .baseUrl(url)
                .build();
    }

    public void registerChat(Long id) {
        WEB_CLIENT.post()
                .uri("/tg-chat/" + id.toString())
                .retrieve()
                .bodyToMono(Void.class)
                .timeout(Duration.ofSeconds(10))
                .block();
    }

    public ListLinksResponse getListLinks(Long id) {
        return WEB_CLIENT.get()
                .uri("links")
                .header("Tg-Chat-Id", id.toString())
                .retrieve()
                .bodyToMono(ListLinksResponse.class)
                .timeout(Duration.ofSeconds(10))
                .onErrorReturn(new ListLinksResponse())
                .block();
    }

    public boolean addTrackedLink(Long id, String link) {
        AddLinkRequest request = new AddLinkRequest(link);
        LinkResponse response = WEB_CLIENT.post()
                .uri("links")
                .header("Tg-Chat-Id", id.toString())
                .body(Mono.just(request), AddLinkRequest.class)
                .retrieve()
                .bodyToMono(LinkResponse.class)
                .timeout(Duration.ofSeconds(10))
                .onErrorReturn(new LinkResponse())
                .block();
        return response != null && response.getUrl() != null && response.getUrl().toString().equals(link);
    }

    public boolean deleteTrackedLink(Long id, String link) {
        RemoveLinkRequest request = new RemoveLinkRequest(link);
        LinkResponse response = WEB_CLIENT.method(HttpMethod.DELETE)
                .uri("links")
                .header("Tg-Chat-Id", id.toString())
                .body(Mono.just(request), RemoveLinkRequest.class)
                .retrieve()
                .bodyToMono(LinkResponse.class)
                .timeout(Duration.ofSeconds(10))
                .onErrorReturn(new LinkResponse())
                .block();
        return response != null && response.getUrl() != null && response.getUrl().toString().equals(link);
    }
}
