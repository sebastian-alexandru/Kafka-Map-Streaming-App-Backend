package com.example.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GeoJsonDTO {

    private long id;
    private String type;
    private GeometryDTO geometry;
    private PropertiesDTO properties;

        public GeoJsonDTO(String type, GeometryDTO geometry, PropertiesDTO properties) {
        this.type = type;
        this.geometry = geometry;
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "{" +
                "type=" + type +
                ", geometry=" + geometry +
                ", properties=" + properties +
                '}';
    }
}
