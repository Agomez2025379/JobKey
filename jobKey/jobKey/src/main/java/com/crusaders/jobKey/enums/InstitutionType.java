package com.crusaders.jobKey.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum InstitutionType {
    UNIVERSITY("university"),
    INSTITUTE("institute"),
    SCHOOL("school");

    private final String value;

    InstitutionType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static InstitutionType fromValue(String value) {
        for (InstitutionType t : InstitutionType.values()) {
            if (t.value.equalsIgnoreCase(value)) {
                return t;
            }
        }
        throw new IllegalArgumentException("Invalid institution type: " + value);
    }

}