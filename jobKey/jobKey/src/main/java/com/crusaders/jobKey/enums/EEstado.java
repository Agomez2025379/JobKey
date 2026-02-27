package com.crusaders.jobKey.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EEstado {
    PENDIENTE("Pendiente"),
    REVISADP("Revisado"),
    ENTREVISTA("Entrevista"),
    ACEPTADO("Aceptado"),
    RECHAZADO("Rechazado");


    private final String value;

    EEstado(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

}
