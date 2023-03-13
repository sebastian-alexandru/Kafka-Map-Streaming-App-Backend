package com.example.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
public class GeometryPointDTO implements GeometryDTO <ArrayList<Double>>{
    public String type;
    public ArrayList<Double> pointCoordinates;


    @Override
    public void setCoordinates(ArrayList<Double> coordinates) {

    }
}
