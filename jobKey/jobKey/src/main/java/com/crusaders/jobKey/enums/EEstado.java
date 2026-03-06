package com.crusaders.jobKey.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EEstado {
    PENDIENTE("pendiente"),
    REVISADO("revisado"),
    ENTREVISTA("entrevista"),
    ACEPTADO("aceptado"),
    RECHAZADO("rechazado");

    private final String value;

    EEstado(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static EEstado fromValue(String value) {
        for (EEstado e : EEstado.values()) {
            if (e.value.equalsIgnoreCase(value)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Modalidad inválida: " + value);
    }
}
