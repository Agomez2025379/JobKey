package com.crusaders.jobKey.entity.enums;

public enum ETipoJornada {
    TIEMPO_COMPLETO("tiempo completo"),
    MEDIO_TIEMPO("medio tiempo"),
    PRACTICAS("practicas");

    private final String value;

    ETipoJornada(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
