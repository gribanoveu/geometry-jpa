package com.habr.egribanov.geometry.service;

import com.habr.egribanov.geometry.dao.repo.DeliveryLocationRepository;
import com.habr.egribanov.geometry.dao.table.DeliveryLocation;
import com.habr.egribanov.geometry.dto.CreateZonesRequest;
import com.habr.egribanov.geometry.dto.DeliveryTermsResponse;
import com.habr.egribanov.geometry.dto.LocationPointRequest;
import com.habr.egribanov.geometry.dto.mapper.DeliveryLocationToTermsMapper;
import com.habr.egribanov.geometry.exception.Message;
import com.habr.egribanov.geometry.exception.RestException;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Polygon;
import org.springframework.stereotype.Service;

/**
 * @author Evgeny Gribanov
 * @version 24.07.2024
 */
@Service
@RequiredArgsConstructor
public class DeliveryGeoService implements GeoService {
    private final DeliveryLocationRepository locationRepository;
    private final DeliveryLocationToTermsMapper locationToTermsMapper;

    public void saveAllDeliveryZones(CreateZonesRequest geoJson) {
        var zones = geoJson.features().stream()
                .map(feature -> DeliveryLocation.builder()
                        .city(feature.properties().city())
                        .district(feature.properties().district())
                        .price(feature.properties().price())
                        .fill(feature.properties().fill())
                        .polygon((Polygon) feature.geometry())
                        .build())
                .toList();

        locationRepository.saveAll(zones);
    }

    public DeliveryTermsResponse getDeliveryTerms(LocationPointRequest request) {
        var location = locationRepository.findLocationByCoordinates(
                request.longitudeX(), request.latitudeY()
        ).orElseThrow(() -> new RestException(Message.ADDRESS_OUT_OF_DELIVERY_ZONE));

        return locationToTermsMapper.toResponse(location);
    }


    @Override
    public void inDeliveryZone(LocationPointRequest request) {
        var isDeliverable = locationRepository.existsLocationContainingPoint(
                request.longitudeX(), request.latitudeY()
        );
        if (!isDeliverable) throw new RestException(Message.ADDRESS_OUT_OF_DELIVERY_ZONE);
    }
}
