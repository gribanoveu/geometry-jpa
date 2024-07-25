package com.habr.egribanov.geometry.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

/**
 * @author Evgeny Gribanov
 * @version 22.07.2024
 */
@Schema(description = "Условия доставки")
public record DeliveryTermsResponse(
        @Schema(description = "Город")
        @JsonProperty("city")
        String city,

        @Schema(description = "Район")
        @JsonProperty("district")
        String district,

        @Schema(description = "Цена в рублях")
        @JsonProperty("price_rub")
        BigDecimal price
) {
}
