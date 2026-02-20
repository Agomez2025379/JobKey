drop database if exists jobKey_in5bv;
create database if not exists jobKey_in5bv;
use jobKey_in5bv;

-- tabla de departamentos
create table departamentos (
    idDepartamento int primary key auto_increment,
    nombre varchar(50) not null unique
);

-- tabla de candidatos
create table candidatos (
    idCandidato int primary key auto_increment,
    nombre varchar(100) not null,
    apellido varchar(100) not null,
    email varchar(100) unique not null,
    telefono varchar(20),
    password_hash varchar(255) not null,
    profesion varchar(100),
    experiencia text,
    educacion text,
    habilidades text,
    curriculum_url varchar(255),
    departamento_id int,
    fecha_registro timestamp default current_timestamp,
    foreign key (departamento_id) 
        references departamentos(idDepartamento) 
        on delete set null
);

-- tabla de empresas
create table empresas (
    idEmpresa int primary key auto_increment,
    nombre_empresa varchar(150) not null,
    email varchar(100) unique not null,
    telefono varchar(20),
    password_hash varchar(255) not null,
    descripcion text,
    sector_empresarial varchar(100),
    logo_url longblob,
    departamento_id int,
    fecha_registro timestamp default current_timestamp,
    foreign key (departamento_id) 
        references departamentos(idDepartamento) 
        on delete set null
);

-- tabla de instituciones
create table instituciones (
    idInstitucion int primary key auto_increment,
    nombre_institucion varchar(150) not null,
    email varchar(100) unique not null,
    telefono varchar(20),
    password_hash varchar(255) not null,
    descripcion text,
    tipo enum('universidad', 'instituto', 'bootcamp', 'colegio') not null,
    logo_url longblob,
    departamento_id int,
    fecha_registro timestamp default current_timestamp,
    foreign key (departamento_id) 
        references departamentos(idDepartamento) 
        on delete set null
);

-- tabla de ofertas
create table ofertas_trabajo (
    idOferta int primary key auto_increment,
    empresa_id int not null,
    titulo varchar(150) not null,
    descripcion text not null,
    requisitos text,
    salario_min decimal(10,2),
    salario_max decimal(10,2),
    modalidad enum('presencial', 'remoto', 'hibrido') default 'presencial',
    tipo_jornada enum('tiempo_completo', 'medio_tiempo', 'practicas') not null,
    nivel_requerido enum('estudiante', 'graduado', 'junior', 'senior') not null,
    departamento_id int,
    fecha_publicacion timestamp default current_timestamp,
    fecha_cierre date,
    activa boolean default true,
    foreign key (empresa_id) 
        references empresas(idEmpresa) 
        on delete cascade,
    foreign key (departamento_id) 
        references departamentos(idDepartamento) 
        on delete set null
);

-- tabla de postulaciones
create table postulaciones (
    idPostulacion int primary key auto_increment,
    oferta_id int not null,
    candidato_id int not null,
    fecha_postulacion timestamp default current_timestamp,
    estado enum('pendiente', 'revisado', 'entrevista', 'aceptado', 'rechazado') default 'pendiente',
    comentarios text,
    unique key unique_postulacion (oferta_id, candidato_id),
    foreign key (oferta_id) 
        references ofertas_trabajo(idOferta) 
        on delete cascade,
    foreign key (candidato_id) 
        references candidatos(idCandidato) 
        on delete cascade
);

-- tabla de reseñas
create table resenas (
    idResena int primary key auto_increment,
    tipo enum('empresa_a_candidato', 'candidato_a_empresa') not null,
    empresa_id int,
    candidato_id int,
    oferta_id int,
    puntuacion int check (puntuacion between 1 and 5),
    comentario text,
    fecha timestamp default current_timestamp,
    foreign key (empresa_id) 
        references empresas(idEmpresa) 
        on delete cascade,
    foreign key (candidato_id) 
        references candidatos(idCandidato) 
        on delete cascade,
    foreign key (oferta_id) 
        references ofertas_trabajo(idOferta) 
        on delete set null
);

-- tabla de admins
create table admins (
    idAdmin int primary key auto_increment,
    nombre varchar(100) not null,
    email varchar(100) unique not null,
    password_hash varchar(255) not null,
    rol enum('super_admin', 'moderador') default 'moderador',
    ultimo_acceso timestamp null,
    fecha_registro timestamp default current_timestamp
);
