package com.example.entity;

import com.example.payload.Properties;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.locationtech.jts.geom.Geometry;

import javax.persistence.*;

@Entity
@Table(name = "GeoJson")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@Getter
@Setter
public class GeoJsonEntity {

    @Id
    @GeneratedValue
    private long id;
    private String type;

    public Geometry geometry;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Properties properties;

    public GeoJsonEntity(){}
}
