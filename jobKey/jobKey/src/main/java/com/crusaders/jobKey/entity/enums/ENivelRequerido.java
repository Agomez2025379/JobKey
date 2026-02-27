package com.crusaders.jobKey.entity.enums;

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

    public String getValue() {
        return value;
    }
}
