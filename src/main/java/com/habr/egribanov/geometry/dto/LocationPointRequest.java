package com.habr.egribanov.geometry.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author Evgeny Gribanov
 * @version 22.07.2024
 */
@Schema(description = "Координаты")
public record LocationPointRequest(
        @JsonProperty("latitudeY")
        @Schema(description = "Широта", example = "48.716496")
        Float latitudeY,

        @JsonProperty("longitudeX")
        @Schema(description = "Долгота", example = "44.530353")
        Float longitudeX
) {
}
