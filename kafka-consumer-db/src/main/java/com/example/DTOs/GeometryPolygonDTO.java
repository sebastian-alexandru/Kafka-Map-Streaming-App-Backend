package com.example.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
public class GeometryPolygonDTO implements GeometryDTO <ArrayList<ArrayList<ArrayList<Double>>>>{
    public String type;
    public ArrayList<ArrayList<ArrayList<Double>>> polygonCoordinates;

    @Override
    public void setCoordinates(ArrayList<ArrayList<ArrayList<Double>>> coordinates) {
        this.polygonCoordinates = coordinates;
    }
}
