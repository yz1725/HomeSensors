package com.letaotao.services.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "temperature_sensors")
public class TemperatureSensorModel {
    @Id
    @Column(name = "id")
    private int id;

    private String target;
    private String ambient;
    private String timeCollected;
    private String comments;

    private Timestamp createdAt;
    private Timestamp updatedAt;
}
