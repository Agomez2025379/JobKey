package com.crusaders.jobKey.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EModalidad {
    PRESENCIAL("presencial"),
    REMOTO("remoto"),
    HIBRIDO("hibrido");

    private final String value;

    EModalidad(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }


}
