package com.habr.egribanov.geometry.controller;

import com.habr.egribanov.geometry.dto.CreateZonesRequest;
import com.habr.egribanov.geometry.dto.DeliveryTermsResponse;
import com.habr.egribanov.geometry.dto.LocationPointRequest;
import com.habr.egribanov.geometry.service.GeoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Evgeny Gribanov
 * @version 24.07.2024
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name="Зоны доставки")
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Зоны добавлены / Адрес в зоне доставки"),
        @ApiResponse(responseCode = "406", description = "Адрес вне зоны доставки")
})
class DeliveryController {
    public static final String SAVE_DELIVERY_LOCATION_URL = "/v1/location";
    public static final String DELIVERY_LOCATION_TERM_URL = "/v1/location-term";
    public static final String DELIVERY_LOCATION_CHECK_URL = "/v1/location-check";

    private final GeoService geoService;

    @Operation(summary = "Добавить зоны доставки в формате GeoJson")
    @PostMapping(SAVE_DELIVERY_LOCATION_URL)
    @ResponseStatus(HttpStatus.OK)
    public void saveAllDeliveryZones(@RequestBody CreateZonesRequest geoJson) {
        geoService.saveAllDeliveryZones(geoJson);
    }

    @Operation(summary = "Получить условия доставки в эту зону")
    @PostMapping(DELIVERY_LOCATION_TERM_URL)
    @ResponseStatus(HttpStatus.OK)
    public DeliveryTermsResponse getDeliveryTerms(@RequestBody LocationPointRequest request) {
        return geoService.getDeliveryTerms(request);
    }

    @Operation(summary = "Находится ли в зоне доставки")
    @PostMapping(DELIVERY_LOCATION_CHECK_URL)
    @ResponseStatus(HttpStatus.OK)
    public void inDeliveryZone(@RequestBody LocationPointRequest request) {
        geoService.inDeliveryZone(request);
    }
}
