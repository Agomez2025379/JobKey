package com.crusaders.jobKey.dto.auth;

import com.crusaders.jobKey.entity.Departamentos;
import com.crusaders.jobKey.enums.ETipoInstitucion;
import com.crusaders.jobKey.enums.EUsuarioRol;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    //comun para login, Usuario
    private String email;
    private String password;
    private EUsuarioRol rol;

    // especifico para administrador
    private String nombreAdmin;

    // especifico para candidato
    private String nombre;
    private String apellido;
    private String telefono;
    private String profesion;
    private String experiencia;
    private String educacion;
    private String habilidades;
    private String curriculumUrl;

    // especifico para empresa
    private String nombreEmpresa;
    private String descripcionEmpresa;
    private String telefonoEmpresa;
    private String sectorEmpresarial;
    private byte[] logoEmpresa;

    // especifico para institucion
    private String nombreInstitucion;
    private String telefonoInstitucion;
    private String descripcionInstitucion;
    private ETipoInstitucion tipoInstitucion;
    private byte[] logoInstitucion;


    // compartido por empresa, institucion y candidato
    private Integer departamentoId;
}