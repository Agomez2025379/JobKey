package com.crusaders.jobKey.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EUsuarioRol {
    ADMIN("admin"),
    EMPRESA("empresa"),
    CANDIDATO("candidato"),
    INSTITUCION("institucion");


    private final String value;

    EUsuarioRol(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static EUsuarioRol fromValue(String value) {
        for (EUsuarioRol u : EUsuarioRol.values()) {
            if (u.value.equalsIgnoreCase(value)) {
                return u;
            }
        }
        throw new IllegalArgumentException("Modalidad inválida: " + value);
    }
}
