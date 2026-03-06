package com.crusaders.jobKey.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EModalidad {

    PRESENCIAL("presencial"),
    REMOTO("remoto"),
    HIBRIDO("hibrido");

    private final String value;

    EModalidad(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static EModalidad fromValue(String value) {
        for (EModalidad m : EModalidad.values()) {
            if (m.value.equalsIgnoreCase(value)) {
                return m;
            }
        }
        throw new IllegalArgumentException("Modalidad inválida: " + value);
    }
}
