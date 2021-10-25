package com.letaotao.services.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TemperatureSensorCreateRequest implements Serializable {

    private static final long serialVersionUID = 128986055755621157L;

    String id;
    String target;
    String ambient;
    String timeCollected;

}
