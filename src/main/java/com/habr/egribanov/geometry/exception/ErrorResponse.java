package com.habr.egribanov.geometry.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author Evgeny Gribanov
 * @version 27.05.2024
 */
@Schema(description = "Ошибка API")
public record ErrorResponse(
        @JsonProperty("reason")
        @Schema(description = "HTTP статус код")
        String reason,

        @JsonProperty("message")
        @Schema(description = "Текст ошибки")
        String message
) {
}
