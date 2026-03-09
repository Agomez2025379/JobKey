package com.crusaders.jobKey.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ETipoInstitucion {
    UNIVERSIDAD("universidad"),
    INSTITUTO("instituto"),
    COLEGIO("colegio");

    private final String value;

    ETipoInstitucion(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static ETipoInstitucion fromValue(String value) {
        for (ETipoInstitucion t : ETipoInstitucion.values()) {
            if (t.value.equalsIgnoreCase(value)) {
                return t;
            }
        }
        throw new IllegalArgumentException("Tipo de institución inválida: " + value);
    }

}
