package com.example.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
public class GeometryLineDTO implements GeometryDTO <ArrayList<ArrayList<Double>>> {
    public String type;
    public ArrayList<ArrayList<Double>> lineCoordinates;

    @Override
    public void setCoordinates(ArrayList<ArrayList<Double>> coordinates) {

    }
}
