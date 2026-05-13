package com.crusaders.jobKey.DTO.institutions;

import com.crusaders.jobKey.enums.InstitutionType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InstitutionResponse {

    private Integer institutionId;
    private Integer userId;
    private String institutionName;
    private String phone;
    private String description;
    private InstitutionType type;
    private Integer departmentId;
}