# 🚀 Guía de Setup y Testing - JobKey

## Instalación y Configuración

### 1. Compilación del Proyecto

```bash
cd jobKey
.\mvnw.cmd clean compile -q
```

### 2. Contrasella Base de Datos

Asegúrate de tener MySQL corriendo y configurado en `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/jobkey
spring.datasource.username=root
spring.datasource.password=tu_password
spring.jpa.hibernate.ddl-auto=update
```

### 3. Ejecución de la Aplicación

```bash
.\mvnw.cmd spring-boot:run
```

La aplicación se ejecutará en: **http://localhost:8080**

---

## 📝 Cómo Crear Usuarios de Prueba

### Opción 1: Por GUI (Recomendado)

1. Accede a `http://localhost:8080`
2. Haz clic en **[Login]** en la navegación
3. Haz clic en **[Register]** en la página de login
4. Completa el formulario:
   - Email: `candidato@jobkey.com`
   - Contraseña: `password123`
   - Rol: Selecciona **CANDIDATO**
5. Haz clic **[Register]**
6. Intenta login con las credenciales

### Opción 2: Por Base de Datos (Manual)

```sql
-- ADMIN
INSERT INTO usuarios (email, password_hash, rol, fecha_registro)
VALUES ('admin@jobkey.com', 
        '$2a$10$...', -- Hash de "password123" con BCrypt
        'ADMIN', 
        NOW());

-- EMPRESA
INSERT INTO usuarios (email, password_hash, rol, fecha_registro)
VALUES ('empresa@jobkey.com', 
        '$2a$10$...', 
        'EMPRESA', 
        NOW());

-- CANDIDATO
INSERT INTO usuarios (email, password_hash, rol, fecha_registro)
VALUES ('candidato@jobkey.com', 
        '$2a$10$...', 
        'CANDIDATO', 
        NOW());

-- INSTITUCIÓN
INSERT INTO usuarios (email, password_hash, rol, fecha_registro)
VALUES ('institucion@jobkey.com', 
        '$2a$10$...', 
        'INSTITUCION', 
        NOW());
```

**Para generar el hash BCrypt:**
```java
new BCryptPasswordEncoder().encode("password123")
// Resultado: $2a$10$slYQmyNdGzin7olVn76p2OPST9/PgBkqquzi.Ss68qFUUgJ0w/rk6
```

---

## 🧪 Casos de Prueba

### Test 1: Sin Autenticación
```
Paso 1: Abre http://localhost:8080 en incógnito
Resultado esperado: Ver 4 tarjetas públicas (Candidatos, Empresas, Ofertas, Instituciones)

Paso 2: Haz clic en cualquier tarjeta
Resultado esperado: Puedes ver datos pero debes autenticarte para acciones

Paso 3: Intenta acceder a /usuarios directamente
Resultado esperado: Redirige a /login
```

### Test 2: Login como CANDIDATO
```
Paso 1: Accede a http://localhost:8080/login
Paso 2: Ingresa candidato@jobkey.com / password123
Paso 3: Haz clic [Login]
Resultado esperado: Redirige a /home con sección CANDIDATO visible

Pasos a validar:
- ✅ Solo ve opciones: Mi Perfil, Ofertas, Empresas
- ✅ Botón "Crear Nueva Oferta" NO aparece
- ✅ Botón "Ver Usuarios" NO aparece
- ✅ Botón "Ver Instituciones" aparece en GENERAL CONTENT

Test de ataque:
Paso 1: Intenta acceder a http://localhost:8080/ofertas/crear
Resultado esperado: 403 Forbidden (no tiene permiso EMPRESA)
```

### Test 3: Login como EMPRESA
```
Paso 1: Accede a http://localhost:8080/login
Paso 2: Ingresa empresa@jobkey.com / password123
Paso 3: Haz clic [Login]
Resultado esperado: Redirige a /home con sección EMPRESA visible

Pasos a validar:
- ✅ Botón "Crear Nueva Oferta" SÍ aparece
- ✅ Botón "Mi Company" aparece
- ✅ Botón "Ver Usuarios" NO aparece
- ✅ Botón "Ver Administración" NO aparece

Prueba funcionalidad:
Paso 1: Haz clic [Crear Nueva Oferta]
Paso 2: Completa el formulario
Paso 3: Guarda la oferta
Resultado esperado: Redirige a /ofertas y muestra la nueva oferta

Test de ataque:
Paso 1: Intenta acceder a http://localhost:8080/usuarios
Resultado esperado: 403 Forbidden (no tiene permiso ADMIN)
```

### Test 4: Login como ADMIN (⭐ Super Usuario)
```
Paso 1: Accede a http://localhost:8080/login
Paso 2: Ingresa admin@jobkey.com / password123
Paso 3: Haz clic [Login]
Resultado esperado: Redirige a /home con secciones ADMIN completas

Pasos a validar:
- ✅ Botón "Panel Administrativo" SÍ aparece
- ✅ Botón "Ver Usuarios" SÍ aparece
- ✅ Botón "Editar/Eliminar Usuarios" SÍ funcionan
- ✅ Botón "Ver Admins" SÍ aparece
- ✅ Botón "Crear Admin" SÍ aparece
- ✅ Botón "Job Offers Management" SÍ aparece
- ✅ Botón "Crear Nueva Oferta" SÍ aparece (como ADMIN)
- ✅ Botón "Company Management" SÍ aparece
- ✅ Botón "Candidate Management" SÍ aparece
- ✅ Botón "Institution Management" SÍ aparece

Prueba de acceso total:
Paso 1: Intenta acceder a http://localhost:8080/ofertas/crear
Resultado esperado: ✅ Puede crear ofertas (rol ADMIN)

Paso 2: Intenta acceder a http://localhost:8080/usuarios
Resultado esperado: ✅ Puede ver usuarios (rol ADMIN)

Paso 3: Intenta acceder a http://localhost:8080/instituciones/nueva
Resultado esperado: ✅ Puede crear instituciones (rol ADMIN)

ADMIN tiene acceso TOTAL a:
- Todas las ofertas (crear, editar, eliminar)
- Todos los usuarios (ver, editar, eliminar)
- Todos los admins (crear, editar, eliminar)
- Todas las empresas
- Todos los candidatos
- Todas las instituciones (crear, editar, eliminar)
```

### Test 5: Login como INSTITUCIÓN
```
Paso 1: Accede a http://localhost:8080/login
Paso 2: Ingresa institucion@jobkey.com / password123  
Paso 3: Haz clic [Login]
Resultado esperado: Redirige a /home con sección INSTITUCIÓN visible

Pasos a validar:
- ✅ Botón "Ver Instituciones" SÍ aparece
- ✅ Botón "Crear Institución" SÍ aparece
- ✅ Botón "Ver Usuarios" NO aparece
- ✅ Botón "Crear Nueva Oferta" NO aparece

Prueba funcionalidad:
Paso 1: Haz clic [Ver Instituciones]
Paso 2: Valida que ve lista de instituciones
Paso 3: Haz clic [Crear Institución]
Paso 4: Completa formulario
Resultado esperado: Nueva institución creada
```

### Test 6: Logout
```
Paso 1: Usuario autenticado
Paso 2: Haz clic [Logout] en la navegación
Paso 3: Confirma la acción
Resultado esperado: Redirige a /home con vista de no autenticado
```

---

## 🔍 Depuración

### Ver logs de Spring Security

En `application.properties`:
```properties
logging.level.org.springframework.security=DEBUG
```

### Ver qué rol tiene un usuario

```java
// En cualquier controlador
System.out.println("Current user roles: " + 
    SecurityContextHolder.getContext()
        .getAuthentication()
        .getAuthorities());
```

### URLs para Testing Rápido

```
http://localhost:8080/home          - Home (información según rol)
http://localhost:8080/login         - Login
http://localhost:8080/register      - Registro
http://localhost:8080/usuarios      - ADMIN ONLY
http://localhost:8080/ofertas       - TODOS (lectura), EMPRESA (CRUD)
http://localhost:8080/candidatos    - TODOS (lectura), CANDIDATO (full)
http://localhost:8080/empresas      - TODOS (lectura), EMPRESA (full)
http://localhost:8080/instituciones - TODOS (lectura), INSTITUCION (CRUD)
http://localhost:8080/admins        - ADMIN ONLY
```

---

## 📊 Matriz de Permisos - Testing Rápido

| URL | Anónimo | Admin | Empresa | Candidato | Institución |
|---|---|---|---|---|---|
| GET / | ✅ | ✅ | ✅ | ✅ | ✅ |
| GET /home | ✅ | ✅ | ✅ | ✅ | ✅ |
| GET /login | ✅ | ✅ | ✅ | ✅ | ✅ |
| GET /usuarios | 🚫 | ✅ | 🚫 | 🚫 | 🚫 |
| GET /ofertas | ✅ | ✅ | ✅ | ✅ | ✅ |
| POST /ofertas/crear | 🚫 | ✅ | ✅ | 🚫 | 🚫 |
| GET /ofertas/editar/1 | 🚫 | ✅ | ✅ | 🚫 | 🚫 |
| GET /ofertas/eliminar/1 | 🚫 | ✅ | ✅ | 🚫 | 🚫 |
| GET /candidatos | ✅ | ✅ | ✅ | ✅ | ✅ |
| POST /candidatos/guardar | 🚫 | ✅ | 🚫 | ✅ | 🚫 |
| GET /empresas | ✅ | ✅ | ✅ | ✅ | ✅ |
| POST /empresas/guardar | 🚫 | ✅ | ✅ | 🚫 | 🚫 |
| GET /instituciones | ✅ | ✅ | ✅ | ✅ | ✅ |
| GET /instituciones/nueva | 🚫 | ✅ | 🚫 | 🚫 | ✅ |
| GET /admins | 🚫 | ✅ | 🚫 | 🚫 | 🚫 |

---

## ⚠️ Solución de Problemas

### Problema: "Usuario no encontrado"
**Causa:** El usuario no existe en la base de datos  
**Solución:** Crea el usuario manualmente o por GUI en /register

### Problema: "Acceso denegado" en todas las rutas
**Causa:** Base de datos vacía o roles mal configurados  
**Solución:** 
```sql
SELECT * FROM usuarios;
-- Verifica que roltengs valores válidos: ADMIN, EMPRESA, CANDIDATO, INSTITUCION
```

### Problema: Login funciona pero no ve las opciones en home
**Causa:** El rol no está siendo cargado correctamente  
**Solución:** Verifica que:
- El usuario tiene un rol válido en BD
- CustomUserDetailsService está leyendo el rol
- La sesión de Spring Security tiene los roles

### Problema: CSS y JS no se cargan
**Causa:** Rutas de recursos no están permitidas  
**Solución:** En SecurityConfig, verifica:
```java
.requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
```

---

## 🎯 Verificación Final

Checklist antes de entregar:

- [ ] Proyecto compila sin errores: `.\mvnw.cmd clean compile`
- [ ] Se puede login como ADMIN
- [ ] Se puede login como EMPRESA
- [ ] Se puede login como CANDIDATO
- [ ] Se puede login como INSTITUCIÓN
- [ ] Candidato NO puede crear ofertas
- [ ] Empresa SÍ puede crear ofertas
- [ ] ADMIN ve panel administrativo
- [ ] Logout funciona correctamente
- [ ] URLs protegidas retornan 403 sin rol/autenticación
- [ ] Home muestra contenido diferente por rol
- [ ] Todos los botones funcionan correctamente



