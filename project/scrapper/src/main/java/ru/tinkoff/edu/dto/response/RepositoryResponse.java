package ru.tinkoff.edu.dto.response;

import java.time.OffsetDateTime;

public record RepositoryResponse(Long id, String full_name, OffsetDateTime created_at, OffsetDateTime updated_at,
                                 OffsetDateTime pushed_at) { }
