package com.habr.egribanov.geometry.dao.repo;

import com.habr.egribanov.geometry.dao.table.DeliveryLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * For geodetic coordinates, X is longitude and Y is latitude
 * <a href="https://postgis.net/docs/ST_MakePoint.html">postgis</a>
 * @author Evgeny Gribanov
 * @version 12.07.2024
 */
@Repository
public interface DeliveryLocationRepository extends JpaRepository<DeliveryLocation, UUID> {

    @Query("""
            SELECT COUNT(loc) > 0 FROM DeliveryLocation loc
            WHERE ST_Contains(loc.polygon, ST_SetSRID(ST_MakePoint(:x, :y), 4326))
            """)
    boolean existsLocationContainingPoint(@Param("x") double longitudeX, @Param("y") double latitudeY);

    @Query("""
            SELECT loc FROM DeliveryLocation loc
            WHERE ST_Contains(loc.polygon, ST_SetSRID(ST_MakePoint(:x, :y), 4326))
            """)
    Optional<DeliveryLocation> findLocationByCoordinates(@Param("x") double longitudeX, @Param("y") double latitudeY);

}
