use jobKey_in5bv;

-- insertar departamentos de guatemala
insert into departamentos (nombre)
values ('guatemala'),
       ('alta verapaz'),
       ('baja verapaz'),
       ('chimaltenango'),
       ('chiquimula'),
       ('el progreso'),
       ('escuintla'),
       ('huehuetenango'),
       ('izabal'),
       ('jalapa'),
       ('jutiapa'),
       ('peten'),
       ('quetzaltenango'),
       ('quiche'),
       ('retalhuleu'),
       ('sacatepequez'),
       ('san marcos'),
       ('santa rosa'),
       ('solola'),
       ('suchitepequez'),
       ('totonicapan'),
       ('zacapa');


insert into usuarios (email, password_hash, rol, ultimo_acceso)
values ('admin@jobkey.com', 'hash1', 'ADMIN', now()),
       ('tech@empresa.com', 'hash2', 'EMPRESA', now()),
       ('carlos@gmail.com', 'hash3', 'CANDIDATO', now()),
       ('uni@edu.com', 'hash4', 'INSTITUCION', now()),
       ('finanzas@empresa.com', 'hash5', 'EMPRESA', now()),
       ('ana@gmail.com', 'hash6', 'CANDIDATO', now());

insert into candidatos (usuario_id, nombre, apellido, telefono, profesion, experiencia, educacion, habilidades,
                        curriculum_url, departamento_id)
values (3, 'carlos', 'perez', '55511111', 'desarrollador web', '2 años laravel', 'ingenieria sistemas',
        'php, mysql, js', 'cv/carlos.pdf', 1),
       (6, 'ana', 'lopez', '55522222', 'qa tester', '1 año testing', 'ingenieria sistemas', 'selenium, junit',
        'cv/ana.pdf', 2);

insert into empresas (usuario_id, nombre_empresa, telefono, descripcion, sector_empresarial, logo, departamento_id)
values (2, 'tech solutions', '40001111', 'empresa de software', 'tecnologia', null, 1),
       (5, 'finanzas global', '40002222', 'servicios financieros', 'finanzas', null, 2);

insert into instituciones (id_usuario, nombre_institucion, telefono, descripcion, tipo, logo, departamento_id)
values (4, 'universidad nacional', '50001111', 'educacion superior', 'UNIVERSIDAD', null, 1);

insert into ofertas_trabajo
(empresa_id, titulo, descripcion, requisitos, salario, modalidad, tipo_jornada, nivel_requerido, departamento_id,
 fecha_cierre)
values (1, 'frontend react developer', 'desarrollo de interfaces modernas con react', 'react, js, css', 9500, 'HIBRIDO',
        'TIEMPO_COMPLETO', 'DIVERSIFICADO', 1, '2026-12-31'),

       (1, 'backend nodejs developer', 'api rest con nodejs', 'nodejs, express, mysql', 11000, 'REMOTO',
        'TIEMPO_COMPLETO', 'UNIVERSITARIO', 2, '2026-12-31'),

       (1, 'devops engineer junior', 'gestion de infraestructura cloud', 'aws, docker, linux', 13000, 'REMOTO',
        'TIEMPO_COMPLETO', 'UNIVERSITARIO', 3, '2026-12-31'),

       (1, 'qa automation engineer', 'automatizacion de pruebas', 'selenium, junit', 9000, 'HIBRIDO', 'TIEMPO_COMPLETO',
        'UNIVERSITARIO', 4, '2026-12-31'),

       (1, 'soporte tecnico nivel 1', 'soporte a usuarios internos', 'redes basicas, windows', 6500, 'PRESENCIAL',
        'TIEMPO_COMPLETO', 'SIN_REQUISITOS', 5, '2026-12-31'),

       (1, 'analista de sistemas', 'analisis de requerimientos', 'uml, sql', 10000, 'PRESENCIAL', 'TIEMPO_COMPLETO',
        'UNIVERSITARIO', 1, '2026-12-31'),

       (1, 'arquitecto de software junior', 'diseño de arquitectura de sistemas', 'java, microservicios', 14000,
        'REMOTO', 'TIEMPO_COMPLETO', 'UNIVERSITARIO', 2, '2026-12-31'),

       (1, 'desarrollador php laravel', 'sistemas web en laravel', 'php, laravel, mysql', 10500, 'HIBRIDO',
        'TIEMPO_COMPLETO', 'UNIVERSITARIO', 3, '2026-12-31'),

       (1, 'ingeniero de datos jr', 'procesamiento de datos', 'sql, python', 12000, 'REMOTO', 'TIEMPO_COMPLETO',
        'UNIVERSITARIO', 4, '2026-12-31'),

       (1, 'full stack developer', 'frontend + backend', 'react, nodejs', 15000, 'REMOTO', 'TIEMPO_COMPLETO',
        'UNIVERSITARIO', 5, '2026-12-31'),

       (2, 'analista financiero senior', 'analisis financiero avanzado', 'excel, power bi', 15000, 'PRESENCIAL',
        'TIEMPO_COMPLETO', 'UNIVERSITARIO', 1, '2026-12-31'),

       (2, 'contador general', 'gestion contable de empresa', 'contabilidad, sap', 11000, 'PRESENCIAL',
        'TIEMPO_COMPLETO', 'UNIVERSITARIO', 2, '2026-12-31'),

       (2, 'auxiliar contable', 'apoyo contable', 'excel basico', 7500, 'PRESENCIAL', 'MEDIO_TIEMPO', 'BASICOS', 3,
        '2026-12-31'),

       (2, 'auditor interno', 'revision de procesos financieros', 'auditoria', 14000, 'PRESENCIAL', 'TIEMPO_COMPLETO',
        'UNIVERSITARIO', 4, '2026-12-31'),

       (2, 'analista de riesgo', 'evaluacion de riesgos financieros', 'finanzas, estadistica', 13500, 'HIBRIDO',
        'TIEMPO_COMPLETO', 'UNIVERSITARIO', 5, '2026-12-31'),

       (2, 'asesor financiero jr', 'asesoria a clientes', 'finanzas basicas', 9000, 'PRESENCIAL', 'TIEMPO_COMPLETO',
        'DIVERSIFICADO', 1, '2026-12-31'),

       (2, 'gestor de cobranza', 'recuperacion de cartera', 'negociacion', 7000, 'PRESENCIAL', 'MEDIO_TIEMPO',
        'SIN_REQUISITOS', 2, '2026-12-31'),

       (2, 'analista de datos financieros', 'analisis de datos bancarios', 'sql, excel', 12500, 'REMOTO',
        'TIEMPO_COMPLETO', 'UNIVERSITARIO', 3, '2026-12-31'),

       (2, 'consultor financiero', 'consultoria empresarial', 'finanzas, estrategia', 16000, 'HIBRIDO',
        'TIEMPO_COMPLETO', 'UNIVERSITARIO', 4, '2026-12-31'),

       (2, 'especialista en creditos', 'evaluacion crediticia', 'analisis financiero', 10000, 'PRESENCIAL',
        'TIEMPO_COMPLETO', 'DIVERSIFICADO', 5, '2026-12-31');

insert into postulaciones (oferta_id, candidato_id, estado, comentarios)
values (1, 1, 'PENDIENTE', 'interesado en backend'),
       (2, 2, 'REVISADO', 'perfil financiero');

insert into resenas (tipo, empresa_id, candidato_id, oferta_id, puntuacion, comentario)
values ('empresa_a_candidato', 1, 1, 1, 5, 'excelente candidato'),
       ('candidato_a_empresa', 1, 1, 1, 5, 'muy buena empresa');

insert into admins (id_usuario, nombre)
values (1, 'super admin');


select * from usuarios;