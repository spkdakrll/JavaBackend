package ru.tinkoff.edu.java.bot.configuration.dto.resonse;

import java.util.List;

public record ListLinksResponse(
        List<LinkResponse> links,
        Integer size
) {
}
