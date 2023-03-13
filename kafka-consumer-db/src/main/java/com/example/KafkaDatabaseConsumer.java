package com.example;

import com.example.entity.GeoJsonEntity;
import com.example.payload.GeoJsonMessage;
import com.example.repository.GeoJsonRepository;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.geom.impl.CoordinateArraySequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class KafkaDatabaseConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);
    private final GeoJsonRepository dataRepository;
    private Geometry expectedResponse;

    public KafkaDatabaseConsumer(GeoJsonRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @KafkaListener(
            topics = "GeoJson8",
            groupId = "myConsumerGroup"
    )
    public void consume(GeoJsonMessage eventMessage) {

        LOGGER.info(String.format("Message received -> %s", eventMessage.toString()));
        
        GeoJsonEntity transformedData = new GeoJsonEntity();

        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(PrecisionModel.FLOATING), 4326);

        String geometryType = eventMessage.getGeometry().getType();

        if (geometryType.equals("Point")) {

                ArrayList<Double> coordinates = eventMessage.getGeometry().getCoordinates();

                expectedResponse = new Point(
                        new CoordinateArraySequence(
                                new Coordinate[]{
                                        new Coordinate(coordinates.get(0), coordinates.get(1))
                                }),
                        geometryFactory);

        } else if (geometryType.equals("LineString")) {

                ArrayList<ArrayList<Double>> coordinates = eventMessage.getGeometry().getCoordinates();

                Coordinate[] coordArray = new Coordinate[coordinates.size()];

                for (int i = 0; i < coordinates.size(); i++) {
                        coordArray[i] = new Coordinate(coordinates.get(i).get(0), coordinates.get(i).get(1));
                }

                expectedResponse = new LineString(new CoordinateArraySequence(coordArray), geometryFactory);

        } else if (geometryType.equals("Polygon")) {

                ArrayList<ArrayList<ArrayList<Double>>> coordinates = eventMessage.getGeometry().getCoordinates();

                Coordinate[] coordArray = new Coordinate[coordinates.get(0).size()];

                for (int i = 0; i < coordinates.size(); i++) {
                        for (int j = 0; j < coordinates.get(i).size(); j++) {
                                coordArray[j] = new Coordinate(coordinates.get(i).get(j).get(0), coordinates.get(i).get(j).get(1));
                        }
                }

                expectedResponse = new Polygon(
                        new LinearRing(
                                new CoordinateArraySequence(coordArray), geometryFactory), null, geometryFactory);
        }

        transformedData.setType(eventMessage.getType());
        transformedData.setGeometry(expectedResponse);
        transformedData.setProperties(eventMessage.getProperties());

        dataRepository.save(transformedData);
    }
}
