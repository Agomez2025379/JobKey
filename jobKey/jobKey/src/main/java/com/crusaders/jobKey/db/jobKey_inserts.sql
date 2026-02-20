use jobKey_in5bv;

-- insertar departamentos de guatemala
insert into departamentos (nombre) values 
('guatemala'),
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

insert into candidatos 
(nombre, apellido, email, telefono, password_hash, profesion, experiencia, educacion, habilidades, curriculum_url, departamento_id)
values
('Juan', 'Perez', 'juan.perez@mail.com', '77711111', 'hash123', 'Ingeniero de Sistemas', '3 años en desarrollo web', 'Licenciatura en Sistemas', 'Java, MySQL, HTML, CSS', 'cv_juan.pdf', 1),
('Maria', 'Lopez', 'maria.lopez@mail.com', '77722222', 'hash123', 'Diseñadora Grafica', '2 años en branding', 'Tecnico en Diseño', 'Photoshop, Illustrator', 'cv_maria.pdf', 2),
('Carlos', 'Ramirez', 'carlos.ramirez@mail.com', '77733333', 'hash123', 'Contador', '5 años en auditoria', 'Licenciatura en Contaduria', 'Excel, Finanzas', 'cv_carlos.pdf', 3),
('Ana', 'Torres', 'ana.torres@mail.com', '77744444', 'hash123', 'Marketing Digital', '4 años en redes sociales', 'Licenciatura en Marketing', 'SEO, SEM, Meta Ads', 'cv_ana.pdf', 4),
('Luis', 'Fernandez', 'luis.fernandez@mail.com', '77755555', 'hash123', 'Desarrollador Backend', '2 años en APIs REST', 'Ingenieria Informatica', 'PHP, Laravel, MySQL', 'cv_luis.pdf', 5);

insert into empresas
(nombre_empresa, email, telefono, password_hash, descripcion, sector_empresarial, logo_url, departamento_id)
values
('Tech Solutions', 'contacto@techsolutions.com', '22211111', 'hash123', 'Empresa de desarrollo de software', 'Tecnologia', null, 1),
('Creativa Studio', 'info@creativa.com', '22222222', 'hash123', 'Agencia de diseño y marketing', 'Marketing', null, 2),
('Finanzas Pro', 'contacto@finanzaspro.com', '22233333', 'hash123', 'Servicios contables', 'Finanzas', null, 3),
('Digital Market', 'info@digitalmarket.com', '22244444', 'hash123', 'Publicidad digital', 'Marketing', null, 4),
('Innovatech', 'contacto@innovatech.com', '22255555', 'hash123', 'Soluciones tecnológicas empresariales', 'Tecnologia', null, 5);

insert into instituciones
(nombre_institucion, email, telefono, password_hash, descripcion, tipo, logo_url, departamento_id)
values
('Universidad Nacional', 'info@unacional.edu', '33311111', 'hash123', 'Universidad publica', 'universidad', null, 1),
('Instituto Tecnologico Central', 'info@itc.edu', '33322222', 'hash123', 'Formacion tecnica profesional', 'instituto', null, 2),
('Code Bootcamp', 'info@codebootcamp.com', '33333333', 'hash123', 'Bootcamp de programacion', 'bootcamp', null, 3),
('Colegio San Marcos', 'info@sanmarcos.edu', '33344444', 'hash123', 'Educacion secundaria', 'colegio', null, 4),
('Universidad Privada del Norte', 'info@upn.edu', '33355555', 'hash123', 'Universidad privada', 'universidad', null, 5);

insert into ofertas_trabajo
(empresa_id, titulo, descripcion, requisitos, salario_min, salario_max, modalidad, tipo_jornada, nivel_requerido, departamento_id, fecha_cierre)
values
(1, 'Desarrollador Web', 'Desarrollo de aplicaciones web', 'Experiencia en PHP y MySQL', 800.00, 1200.00, 'hibrido', 'tiempo_completo', 'junior', 1, '2026-06-30'),
(2, 'Diseñador Grafico', 'Creacion de contenido visual', 'Manejo de Adobe Suite', 600.00, 900.00, 'presencial', 'tiempo_completo', 'junior', 2, '2026-05-30'),
(3, 'Auxiliar Contable', 'Apoyo en procesos contables', 'Conocimiento en Excel', 500.00, 800.00, 'presencial', 'medio_tiempo', 'estudiante', 3, '2026-04-30'),
(4, 'Especialista SEO', 'Optimización web', 'Experiencia en SEO y SEM', 700.00, 1100.00, 'remoto', 'tiempo_completo', 'senior', 4, '2026-07-30'),
(5, 'Backend Developer', 'Desarrollo de APIs', 'Experiencia en Laravel', 900.00, 1400.00, 'hibrido', 'tiempo_completo', 'junior', 5, '2026-08-30');

insert into postulaciones
(oferta_id, candidato_id, estado, comentarios)
values
(1, 1, 'pendiente', 'Interesado en la oferta'),
(2, 2, 'revisado', 'Buen perfil creativo'),
(3, 3, 'entrevista', 'Programar entrevista tecnica'),
(4, 4, 'pendiente', 'Cumple requisitos'),
(5, 5, 'aceptado', 'Seleccionado para el puesto');

insert into resenas
(tipo, empresa_id, candidato_id, oferta_id, puntuacion, comentario)
values
('empresa_a_candidato', 1, 1, 1, 5, 'Excelente desempeño'),
('candidato_a_empresa', 2, 2, 2, 4, 'Buen ambiente laboral'),
('empresa_a_candidato', 3, 3, 3, 3, 'Buen candidato pero necesita mejorar'),
('candidato_a_empresa', 4, 4, 4, 5, 'Proceso rapido y claro'),
('empresa_a_candidato', 5, 5, 5, 4, 'Buen nivel tecnico');

insert into admins
(nombre, email, password_hash, rol)
values
('Admin Principal', 'admin@jobkey.com', 'hash123', 'super_admin'),
('Carlos Admin', 'carlos.admin@jobkey.com', 'hash123', 'moderador'),
('Maria Admin', 'maria.admin@jobkey.com', 'hash123', 'moderador'),
('Luis Admin', 'luis.admin@jobkey.com', 'hash123', 'moderador'),
('Ana Admin', 'ana.admin@jobkey.com', 'hash123', 'moderador');

