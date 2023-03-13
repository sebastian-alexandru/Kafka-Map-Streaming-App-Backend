package com.example;

import com.example.DTOs.*;
import com.example.entity.GeoJsonEntity;
import com.example.repository.GeoJsonRepository;
import org.locationtech.jts.geom.Coordinate;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class LocationController {
    private final GeoJsonRepository locationRepository;

    public LocationController(GeoJsonRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @QueryMapping()
    Iterable<GeoJsonDTO> locations() {
        List<GeoJsonEntity> entities = locationRepository.findAll();

        ArrayList<GeoJsonDTO> locations = new ArrayList<>();

        for (GeoJsonEntity entity:entities) {
            if (entity.getGeometry().getGeometryType().equals("Point")) {

                ArrayList<Coordinate> coordinates = new ArrayList<>(
                        Arrays.asList(entity.getGeometry().getCoordinates())
                );

                ArrayList<Double> convertedCoordinates = new ArrayList<>();

                for (Coordinate coordinate:coordinates) {
                    convertedCoordinates.add(coordinate.getX());
                    convertedCoordinates.add(coordinate.getY());
                }

                locations.add(
                        new GeoJsonDTO(
                                entity.getId(),
                                entity.getType(),
                                new GeometryPointDTO(
                                        entity.getGeometry().getGeometryType(),
                                        convertedCoordinates
                                ),
                                new PropertiesDTO(entity.getProperties().getName())
                        )
                );
            }

            else if (entity.getGeometry().getGeometryType().equals("LineString")) {

                ArrayList<Coordinate> coordinates = new ArrayList(
                        Arrays.asList(entity.getGeometry().getCoordinates())
                );

                ArrayList<ArrayList<Double>> convertedCoordinates = new ArrayList<>();

                for (Coordinate coordinate:coordinates) {
                    convertedCoordinates.add(new ArrayList<>(Arrays.asList(coordinate.getX(), coordinate.getY())));
                }

                locations.add(
                        new GeoJsonDTO(
                                entity.getId(),
                                entity.getType(),
                                new GeometryLineDTO(
                                        entity.getGeometry().getGeometryType(),
                                        convertedCoordinates
                                ),
                                new PropertiesDTO(entity.getProperties().getName())
                        )
                );
            }

            else if (entity.getGeometry().getGeometryType().equals("Polygon")) {

                ArrayList<Coordinate> coordinates = new ArrayList(
                        Arrays.asList(entity.getGeometry().getCoordinates())
                );

                ArrayList<ArrayList<ArrayList<Double>>> convertedCoordinates = new ArrayList<>();

                ArrayList<ArrayList<Double>> wrapped = new ArrayList();


                for (Coordinate coordinate:coordinates) {

                    ArrayList<Double> coordinatesArray = new ArrayList<>(Arrays.asList(coordinate.getX(), coordinate.getY()));

                    wrapped.add(coordinatesArray);


                }
                convertedCoordinates.add(wrapped);

                locations.add(
                        new GeoJsonDTO(
                                entity.getId(),
                                entity.getType(),
                                new GeometryPolygonDTO(
                                        entity.getGeometry().getGeometryType(),
                                        convertedCoordinates
                                ),
                                new PropertiesDTO(entity.getProperties().getName())
                        )
                );
            }
        }
        return locations;
    }
}
