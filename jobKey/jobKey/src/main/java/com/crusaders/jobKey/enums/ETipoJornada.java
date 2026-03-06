package com.crusaders.jobKey.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ETipoJornada {
    TIEMPO_COMPLETO("tiempo_completo"),
    MEDIO_TIEMPO("medio_tiempo"),
    PRACTICAS("practicas");

    private final String value;

    ETipoJornada(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static ETipoJornada fromValue(String value) {
        for (ETipoJornada t : ETipoJornada.values()) {
            if (t.value.equalsIgnoreCase(value)) {
                return t;
            }
        }
        throw new IllegalArgumentException("Modalidad inválida: " + value);
    }
}
