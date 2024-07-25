package com.habr.egribanov.geometry.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * @author Evgeny Gribanov
 * @version 24.07.2024
 */
@Schema(description = "Создание зоны доставки, формат GeoJson")
public record CreateZonesRequest(
        @JsonProperty("type")
        @Schema(description = "Тип, по умолчанию FeatureCollection", example = "FeatureCollection")
        String type,

        @JsonProperty("features")
        @Schema(description = "Список зон")
        List<GeoJsonFeatureDto> features
) {
}
