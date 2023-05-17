package ru.tinkoff.edu.dto.response;

import java.time.OffsetDateTime;

public record QuestionResponse(Long question_id, OffsetDateTime creation_date, OffsetDateTime last_activity_date) { }
