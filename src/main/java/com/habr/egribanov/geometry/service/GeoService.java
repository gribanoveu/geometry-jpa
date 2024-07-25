package com.habr.egribanov.geometry.service;

import com.habr.egribanov.geometry.dto.CreateZonesRequest;
import com.habr.egribanov.geometry.dto.DeliveryTermsResponse;
import com.habr.egribanov.geometry.dto.LocationPointRequest;
import org.springframework.stereotype.Service;

/**
 * @author Evgeny Gribanov
 * @version 24.07.2024
 */
@Service
public interface GeoService {
    void saveAllDeliveryZones(CreateZonesRequest geoJson);
    DeliveryTermsResponse getDeliveryTerms(LocationPointRequest request);

    void inDeliveryZone(LocationPointRequest request);
}
