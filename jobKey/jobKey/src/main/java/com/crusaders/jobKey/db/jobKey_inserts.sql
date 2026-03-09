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


-- usuarios
insert into usuarios (email,password_hash,rol,ultimo_acceso) values
('admin@jobkey.com','hash1','ADMIN',null),
('empresa1@jobkey.com','hash2','EMPRESA',null),
('empresa2@jobkey.com','hash3','EMPRESA',null),
('candidato1@jobkey.com','hash4','CANDIDATO',null),
('institucion1@jobkey.com','hash5','INSTITUCION',null);

-- admins
insert into admins (id_usuario,nombre) values
(1,'Carlos Mendoza');

-- empresas
insert into empresas (usuario_id,nombre_empresa,telefono,descripcion,sector_empresarial,logo,departamento_id) values
(2,'Tech Guatemala','5551001','empresa de desarrollo de software','tecnologia',null,1),
(3,'AgroExport GT','5551002','exportadora de productos agricolas','agricultura',null,2);

-- candidatos
insert into candidatos (usuario_id,nombre,apellido,telefono,profesion,experiencia,educacion,habilidades,curriculum_url,departamento_id) values
(4,'Juan','Perez','5552001','Desarrollador','2 años desarrollo web','Ingenieria en sistemas','java, sql, html','cv_juan.pdf',1);

-- instituciones
insert into instituciones (id_usuario,nombre_institucion,telefono,descripcion,tipo,logo,departamento_id) values
(5,'Universidad Tecnologica GT','5553001','universidad privada tecnologica','universidad',null,1);

-- ofertas_trabajo
insert into ofertas_trabajo (empresa_id,titulo,descripcion,requisitos,salario,modalidad,tipo_jornada,nivel_requerido,departamento_id,fecha_cierre) values
(1,'Desarrollador Java','desarrollo de aplicaciones empresariales','java y sql',8000,'PRESENCIAL','TIEMPO_COMPLETO','UNIVERSITARIO',1,'2026-12-31'),
(1,'Soporte Tecnico','soporte a sistemas','redes basicas',4500,'PRESENCIAL','MEDIO_TIEMPO','DIVERSIFICADO',1,'2026-11-30'),
(2,'Ingeniero Agronomo','gestion de cultivos','experiencia agricola',7000,'PRESENCIAL','TIEMPO_COMPLETO','UNIVERSITARIO',2,'2026-10-30'),
(2,'Analista de Datos','analisis de datos agricolas','excel y sql',6500,'HIBRIDO','TIEMPO_COMPLETO','UNIVERSITARIO',2,'2026-12-15'),
(1,'Practicante IT','apoyo en area de sistemas','conocimientos basicos',2000,'REMOTO','PRACTICAS','DIVERSIFICADO',1,'2026-09-01');

-- postulaciones
insert into postulaciones (oferta_id,candidato_id,estado,comentarios) values
(1,1,'PENDIENTE','postulacion reciente'),
(2,1,'REVISADO','perfil en revision'),
(3,1,'ENTREVISTA','entrevista programada'),
(4,1,'PENDIENTE','esperando respuesta'),
(5,1,'ACEPTADO','candidato seleccionado');

-- resenas
insert into resenas (tipo,empresa_id,candidato_id,oferta_id,puntuacion,comentario) values
('empresa_a_candidato',1,1,1,5,'excelente candidato'),
('empresa_a_candidato',1,1,2,4,'buen desempeño'),
('candidato_a_empresa',1,1,1,5,'muy buena empresa'),
('candidato_a_empresa',2,1,3,4,'proceso claro'),
('empresa_a_candidato',2,1,3,3,'perfil adecuado');