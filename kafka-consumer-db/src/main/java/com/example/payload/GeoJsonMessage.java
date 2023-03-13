package com.example.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GeoJsonMessage {

    private long id;
    private String type;
    private Geometry geometry;
    private Properties properties;

    public GeoJsonMessage(String type, Geometry geometry, Properties properties) {
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
