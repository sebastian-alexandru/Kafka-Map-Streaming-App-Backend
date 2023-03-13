package com.example.DTOs;

public interface GeometryDTO<T> {

    void setType(String type);

    void setCoordinates(T coordinates);
}
