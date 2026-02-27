package com.crusaders.jobKey.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Modalidad {
    PRESENCIAL("presencial"),
    REMOTO("remoto"),
    HIBRIDO("hibrido");

    private final String value;

    Modalidad(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static Modalidad fromValue(String v) {
        if (v == null) return null;
        String norm = v.trim().toLowerCase();
        for (Modalidad m : values()) {
            if (m.value.equalsIgnoreCase(norm) || m.name().equalsIgnoreCase(norm)) {
                return m;
            }
        }
        throw new IllegalArgumentException("Modalidad desconocida: " + v);
    }
}
