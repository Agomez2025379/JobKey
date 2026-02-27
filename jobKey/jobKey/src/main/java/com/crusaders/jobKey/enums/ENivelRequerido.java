package com.crusaders.jobKey.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ENivelRequerido {
    PRIMARIA("Primaria"),
    BASICOS("Basicos"),
    DIVERSIFICADOS("Diversificado"),
    UNIVERSITARIO("Universitario"),
    SIN_REQUISITOS("Sin Requisito");

    private final String value;

    ENivelRequerido(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
