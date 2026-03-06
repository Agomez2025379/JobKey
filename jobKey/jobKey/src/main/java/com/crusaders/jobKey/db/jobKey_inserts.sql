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
('juan','perez','juan@gmail.com','55511111','hash123','programador','2 anos desarrollo web','universidad','java, mysql, html','cv1.pdf',1),
('ana','lopez','ana@gmail.com','55522222','hash123','disenadora grafica','3 anos disenando','universidad','photoshop, illustrator','cv2.pdf',2),
('carlos','martinez','carlos@gmail.com','55533333','hash123','soporte tecnico','1 ano soporte it','tecnico','redes, hardware','cv3.pdf',3),
('maria','hernandez','maria@gmail.com','55544444','hash123','analista datos','2 anos analisis datos','universidad','python, sql','cv4.pdf',4),
('luis','ramirez','luis@gmail.com','55555555','hash123','administrador','4 anos administracion','universidad','excel, gestion','cv5.pdf',5);


-- empresas
insert into empresas (nombre_empresa,email,telefono,password_hash,descripcion,sector_empresarial,logo_url,departamento_id) values
('techsoft','contacto@techsoft.com','44411111','hash123','empresa desarrollo software','tecnologia',null,1),
('marketplus','info@marketplus.com','44422222','hash123','empresa marketing digital','marketing',null,2),
('datacorp','rrhh@datacorp.com','44433333','hash123','empresa analisis datos','tecnologia',null,3),
('logistica gt','contacto@logistica.com','44444444','hash123','empresa transporte','logistica',null,4),
('finanzas plus','info@finanzas.com','44455555','hash123','empresa servicios financieros','finanzas',null,5);


-- instituciones
insert into instituciones (nombre_institucion,email,telefono,password_hash,descripcion,tipo,logo_url,departamento_id) values
('universidad central','info@uc.edu','33311111','hash123','universidad privada','universidad',null,1),
('instituto tecnologico','info@it.edu','33322222','hash123','instituto tecnico','instituto',null,2),
('bootcamp code','contacto@bootcamp.com','33333333','hash123','bootcamp programacion','bootcamp',null,3),
('colegio moderno','info@colegio.com','33344444','hash123','colegio diversificado','colegio',null,4),
('universidad del norte','contacto@unorte.edu','33355555','hash123','universidad tecnologia','universidad',null,5);


-- ofertas_trabajo
insert into ofertas_trabajo (empresa_id,titulo,descripcion,requisitos,salario,modalidad,tipo_jornada,nivel_requerido,departamento_id,fecha_cierre,activa) values
(1,'desarrollador junior','desarrollo aplicaciones web','conocimientos java y sql',5000,'remoto','tiempo_completo','universitario',1,'2026-12-30',true),
(2,'disenador grafico','diseno contenido digital','manejo adobe',4500,'presencial','tiempo_completo','diversificado',2,'2026-11-15',true),
(3,'soporte tecnico','soporte equipos y redes','conocimiento hardware',3500,'presencial','medio_tiempo','basicos',3,'2026-10-20',true),
(4,'analista datos','analisis bases datos','python y sql',6000,'hibrido','tiempo_completo','universitario',4,'2026-09-10',true),
(5,'asistente administrativo','gestion documentos','excel intermedio',3200,'presencial','tiempo_completo','diversificado',5,'2026-08-25',true);


-- postulaciones
insert into postulaciones (oferta_id,candidato_id,estado,comentarios) values
(1,1,'pendiente','primera postulacion'),
(2,2,'revisado','perfil revisado'),
(3,3,'entrevista','entrevista programada'),
(4,4,'aceptado','contratado'),
(5,5,'rechazado','no cumple requisitos');


-- resenas
insert into resenas (tipo,empresa_id,candidato_id,oferta_id,puntuacion,comentario) values
('empresa_a_candidato',1,1,1,5,'excelente candidato'),
('empresa_a_candidato',2,2,2,4,'buen desempeno'),
('candidato_a_empresa',3,3,3,3,'proceso aceptable'),
('candidato_a_empresa',4,4,4,5,'muy buena empresa'),
('empresa_a_candidato',5,5,5,2,'poca experiencia');


-- admins
insert into admins (nombre,email,password_hash,rol) values
('admin uno','admin1@jobkey.com','hash123','super_admin'),
('admin dos','admin2@jobkey.com','hash123','moderador'),
('admin tres','admin3@jobkey.com','hash123','moderador'),
('admin cuatro','admin4@jobkey.com','hash123','moderador'),
('admin cinco','admin5@jobkey.com','hash123','moderador');


-- roles
insert into roles (nombre_rol) values
('admin'),
('instituciones'),
('candidatos'),
('empresa'),
('empresa');