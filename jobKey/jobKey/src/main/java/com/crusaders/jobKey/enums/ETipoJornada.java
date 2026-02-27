package com.crusaders.jobKey.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ETipoJornada {
    TIEMPO_COMPLETO("tiempo completo"),
    MEDIO_TIEMPO("medio tiempo"),
    PRACTICAS("practicas");

    private final String value;

    ETipoJornada(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
