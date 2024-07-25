-- Migration to create delivery_location table
-- Author: EGribanov
-- Date: 2024-07-24
-- Service: geo

create table if not exists delivery_location
(
    id                 UUID           NOT NULL PRIMARY KEY UNIQUE,
    version            BIGINT         NOT NULL,
    city               VARCHAR(50)    NOT NULL,
    district           VARCHAR(50)    NOT NULL,
    price_rub          NUMERIC(10, 2) NOT NULL,
    fill               VARCHAR(10),
    polygon            GEOMETRY(Polygon, 4326),
    created_date       TIMESTAMP(6)   NOT NULL,
    last_modified_date TIMESTAMP(6)
);

CREATE INDEX polygons_geom_idx ON delivery_location USING GIST (polygon);
