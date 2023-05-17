package ru.tinkoff.edu.java.bot.configuration.dto.resonse;

import java.net.URI;

public record LinkResponse(
        Long id,
        URI url
) {
}
