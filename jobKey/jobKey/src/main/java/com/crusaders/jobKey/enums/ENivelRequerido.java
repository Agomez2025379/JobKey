package com.crusaders.jobKey.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ENivelRequerido {

    PRIMARIA("primaria"),
    BASICOS("basicos"),
    DIVERSIFICADO("diversificado"),
    UNIVERSITARIO("universitario"),
    SIN_REQUISITOS("sin_requisitos");

        private final String value;

        ENivelRequerido(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @JsonCreator
        public static ENivelRequerido fromValue(String value) {
            for (ENivelRequerido n : ENivelRequerido.values()) {
                if (n.value.equalsIgnoreCase(value)) {
                    return n;
                }
            }
            throw new IllegalArgumentException("nivel inválida: " + value);
        }
    }
