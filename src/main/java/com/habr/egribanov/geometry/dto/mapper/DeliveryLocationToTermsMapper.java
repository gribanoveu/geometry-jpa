package com.habr.egribanov.geometry.dto.mapper;

import com.habr.egribanov.geometry.dao.table.DeliveryLocation;
import com.habr.egribanov.geometry.dto.DeliveryTermsResponse;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Evgeny Gribanov
 * @version 22.07.2024
 */
@Component
public class DeliveryLocationToTermsMapper {

    public DeliveryTermsResponse toResponse(DeliveryLocation location) {
        return new DeliveryTermsResponse(location.getCity(), location.getDistrict(), location.getPrice());
    }

    public List<DeliveryTermsResponse> toResponse(List<DeliveryLocation> locations) {
        return locations.stream()
                .map(zone -> new DeliveryTermsResponse(zone.getCity(), zone.getDistrict(), zone.getPrice()))
                .toList();
    }
}