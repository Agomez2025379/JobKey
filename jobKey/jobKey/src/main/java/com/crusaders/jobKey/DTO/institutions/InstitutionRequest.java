package com.crusaders.jobKey.DTO.institutions;

import com.crusaders.jobKey.enums.InstitutionType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstitutionRequest {

    private Integer userId;           // id_usuario existente
    private String institutionName;
    private String phone;
    private String description;
    private InstitutionType type;
    private byte[] logo;
    private Integer departmentId;
}