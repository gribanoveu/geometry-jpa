package com.habr.egribanov.geometry.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

/**
 * @author Evgeny Gribanov
 * @version 24.07.2024
 */
@Schema(description = "Параметры зоны доставки")
public record GeoJsonPropertiesDto(
        @JsonProperty("city")
        @Schema(description = "Город", example = "Волгоград")
        String city,

        @JsonProperty("district")
        @Schema(description = "Район", example = "Центральный район")
        String district,

        @JsonProperty("price_rub")
        @Schema(description = "Цена в рублях", example = "100")
        BigDecimal price,

        @JsonProperty("fill")
        @Schema(description = "Цвет зоны", example = "#37ab0d")
        String fill
) {
}
