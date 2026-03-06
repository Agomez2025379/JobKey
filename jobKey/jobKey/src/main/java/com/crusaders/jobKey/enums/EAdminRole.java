package com.crusaders.jobKey.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EAdminRole {
    ADMIN("super_admin"), //rol con mas permisos, basicamente el que manda
    MODERADOR("moderador"); //rol con permisos limitados, basicamente el que ayuda al admin a gestionar la plataforma

    private final String value;

    EAdminRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}