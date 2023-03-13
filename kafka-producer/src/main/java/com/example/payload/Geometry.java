package com.example.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@NoArgsConstructor
@Getter
@Setter
public class Geometry<T> {

    private String type;

    private ArrayList<T> coordinates;

    public Geometry(String type, ArrayList<T> coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "{" +
                "type='" + type + '\'' +
                ", coordinates=" + coordinates +
                '}';
    }
}
