package ru.tinkoff.edu.java.scrapper.configuration.dto;

import java.util.List;

public record ListLinksResponse(
        List<LinkResponse> links,
        Integer size
) {
}
