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

-- candidatos
insert into candidatos (nombre, apellido, email, telefono, password_hash, profesion, experiencia, educacion, habilidades, curriculum_url, departamento_id) values
('juan', 'perez', 'juan.perez@gmail.com', '55550101', 'hash123', 'ingeniero en sistemas', '3 años como desarrollador web', 'universitario', 'java, mysql, html', 'http://cv.com/juan', 1),
('maria', 'lopez', 'maria.lopez@gmail.com', '55550202', 'hash456', 'contador publico', '5 años en contabilidad', 'universitario', 'excel, contabilidad financiera', 'http://cv.com/maria', 2),
('carlos', 'ramirez', 'carlos.ramirez@gmail.com', '55550303', 'hash789', 'diseñador grafico', '2 años en agencia de publicidad', 'diversificado', 'photoshop, illustrator', 'http://cv.com/carlos', 3),
('ana', 'martinez', 'ana.martinez@gmail.com', '55550404', 'hash321', 'administradora de empresas', '4 años en gestion administrativa', 'universitario', 'gestion, liderazgo', 'http://cv.com/ana', 1),
('luis', 'hernandez', 'luis.hernandez@gmail.com', '55550505', 'hash654', 'tecnico en soporte', '1 año en soporte tecnico', 'diversificado', 'redes, soporte tecnico', 'http://cv.com/luis', 4);

-- empresas
insert into empresas (nombre_empresa, email, telefono, password_hash, descripcion, sector_empresarial, departamento_id) values
('techsolutions', 'contacto@techsolutions.com', '22220001', 'hash111', 'empresa de desarrollo de software', 'tecnologia', 1),
('contaexpert', 'info@contaexpert.com', '22220002', 'hash222', 'servicios contables y financieros', 'finanzas', 2),
('disenarte', 'contacto@disenarte.com', '22220003', 'hash333', 'agencia de diseño grafico', 'publicidad', 3),
('agroindustrias del sur', 'info@agrosur.com', '22220004', 'hash444', 'produccion y exportacion agricola', 'agricultura', 4),
('logisticorp', 'contacto@logisticorp.com', '22220005', 'hash555', 'servicios de logistica y transporte', 'logistica', 5);

-- instituciones
insert into instituciones (nombre_institucion, email, telefono, password_hash, descripcion, tipo, departamento_id) values
('universidad nacional', 'info@uninacional.edu', '23330001', 'hashaaa', 'institucion de educacion superior', 'universidad', 1),
('instituto tecnico central', 'info@itc.edu', '23330002', 'hashbbb', 'formacion tecnica profesional', 'instituto', 2),
('bootcamp codigo rapido', 'info@codigorapido.com', '23330003', 'hashccc', 'programas intensivos de programacion', 'bootcamp', 1),
('colegio integral moderno', 'info@cim.edu', '23330004', 'hashddd', 'educacion media diversificada', 'colegio', 3),
('universidad del occidente', 'info@uoccidente.edu', '23330005', 'hasheee', 'educacion superior en el occidente', 'universidad', 2);

-- ofertas_trabajo
insert into ofertas_trabajo (empresa_id, titulo, descripcion, requisitos, salario, modalidad, tipo_jornada, nivel_requerido, departamento_id, fecha_cierre) values
(1, 'desarrollador java', 'desarrollo de aplicaciones empresariales', '2 años de experiencia en java', 8000.00, 'remoto', 'tiempo_completo', 'universitario', 1, '2026-12-31'),
(2, 'auxiliar contable', 'registro y control de operaciones contables', '1 año de experiencia', 4000.00, 'presencial', 'tiempo_completo', 'diversificado', 2, '2026-11-30'),
(3, 'diseñador junior', 'creacion de piezas graficas', 'manejo de adobe', 3500.00, 'hibrido', 'medio_tiempo', 'diversificado', 3, '2026-10-15'),
(4, 'ingeniero agronomo', 'supervision de cultivos', 'titulo universitario', 9000.00, 'presencial', 'tiempo_completo', 'universitario', 4, '2026-09-01'),
(5, 'coordinador logistico', 'gestion de rutas y transporte', 'experiencia en logistica', 7000.00, 'hibrido', 'tiempo_completo', 'universitario', 5, '2026-08-20');

-- postulaciones
insert into postulaciones (oferta_id, candidato_id, estado, comentarios) values
(1, 1, 'pendiente', 'interesado en la plaza'),
(2, 2, 'revisado', 'cumple con el perfil'),
(3, 3, 'entrevista', 'programar entrevista virtual'),
(4, 4, 'aceptado', 'contratacion inmediata'),
(5, 5, 'rechazado', 'no cumple experiencia requerida');

-- resenas
insert into resenas (tipo, empresa_id, candidato_id, oferta_id, puntuacion, comentario) values
('empresa_a_candidato', 1, 1, 1, 5, 'excelente candidato'),
('empresa_a_candidato', 2, 2, 2, 4, 'buen desempeño'),
('candidato_a_empresa', 3, 3, 3, 5, 'muy buena empresa'),
('candidato_a_empresa', 4, 4, 4, 3, 'proceso regular'),
('empresa_a_candidato', 5, 5, 5, 2, 'necesita mejorar habilidades');

-- admins
insert into admins (nombre, email, password_hash, rol) values
('admin principal', 'admin@jobkey.com', 'hashadmin1', 'super_admin'),
('moderador uno', 'mod1@jobkey.com', 'hashadmin2', 'moderador'),
('moderador dos', 'mod2@jobkey.com', 'hashadmin3', 'moderador'),
('soporte tecnico', 'soporte@jobkey.com', 'hashadmin4', 'moderador'),
('gestion usuarios', 'gestion@jobkey.com', 'hashadmin5', 'moderador');
