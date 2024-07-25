package com.habr.egribanov.geometry.dao.table;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.locationtech.jts.geom.Polygon;

import java.math.BigDecimal;

/**
 * @author Evgeny Gribanov
 * @version 12.07.2024
 */
@Entity
@Builder
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "delivery_location")
@Schema(description = "Доступные зоны доставки")
public class DeliveryLocation extends AbstractEntity {

    @Column(name = "city")
    private String city;

    @Column(name = "district")
    private String district;

    @Column(name = "price_rub")
    private BigDecimal price;

    @Column(name = "fill")
    private String fill;

    @Column(name = "polygon") // , columnDefinition = "geometry(Polygon, 4326)
    private Polygon polygon;
}