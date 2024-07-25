package com.habr.egribanov.geometry.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import org.locationtech.jts.geom.Geometry;
import org.n52.jackson.datatype.jts.GeometryDeserializer;
import org.n52.jackson.datatype.jts.GeometrySerializer;

/**
 * @author Evgeny Gribanov
 * @version 24.07.2024
 */
@Schema(description = "Зона доставки")
public record GeoJsonFeatureDto(
        @JsonProperty("type")
        @Schema(description = "Тип, по умолчанию Feature", example = "Feature")
        String type,

        @JsonProperty("properties")
        @Schema(description = "Параметры зоны доставки")
        GeoJsonPropertiesDto properties,

        @JsonDeserialize(using = GeometryDeserializer.class)
        @JsonSerialize(using = GeometrySerializer.class)
        @JsonProperty("geometry")
        @Schema(description = "Координаты, объект GEOMETRY(Polygon, 4326)")
        Geometry geometry
) {
}
