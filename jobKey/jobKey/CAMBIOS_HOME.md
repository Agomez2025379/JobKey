# Cambios Realizados en Home.html y Configuración de Seguridad

## Descripción General
Se han actualizado el home de la aplicación y la configuración de seguridad para mostrar opciones según el rol del usuario y permitir acceso solo a recursos autorizados.

## Cambios Principales

### 1. **Home.html - Interfaz Responsiva por Rol**
**Archivo:** `src/main/resources/templates/home.html`

#### Cambios:
- **Sección para No Autenticados:** Muestra opciones públicas de lectura (Candidatos, Empresas, Ofertas, Instituciones)
- **Sección para ADMIN:**
  - Ver/Editar/Eliminar Usuarios (`/usuarios`, `/usuarios/**`)
  - Ver/Crear/Editar/Eliminar Admins (`/admins`, `/admins/**`)
  
- **Sección para CANDIDATO:**
  - Mi Perfil de Candidato (`/candidatos`)
  - Buscar Ofertas de Trabajo (`/ofertas`)
  - Ver Empresas (`/empresas`)
  
- **Sección para EMPRESA:**
  - Mi Perfil de Empresa (`/empresas`)
  - Ver Ofertas (`/ofertas`)
  - **Crear/Editar/Ver Ofertas de Trabajo** (`/ofertas/crear`, `/ofertas/editar/{id}`)
  - Ver Candidatos (`/candidatos`)
  
- **Sección para INSTITUCIÓN:**
  - Ver Instituciones (`/instituciones`)
  - Crear/Editar Instituciones (`/instituciones/nueva`, `/instituciones/editar/{id}`)
  
- **Sección Común:** Acceso general a todos los recursos públicos

#### Características Visuales:
- Botones con colores diferenciados por acción (éxito en verde, secundarios en gris)
- Grid responsive con tarjetas
- Iconos emoji para mejor UX
- Estilos consistentes con el resto de la aplicación

### 2. **SecurityConfig.java - Configuración de Autorización por URL**
**Archivo:** `src/main/java/com/crusaders/jobKey/config/SecurityConfig.java`

#### Cambios:
Configuración completa de autorización basada en roles para **todos los endpoints**:

#### Rutas Públicas (sin autenticación):
```
/ y /home - Página principal
/login - Login
/register - Registro
/css/**, /js/**, /images/** - Recursos estáticos
```

#### Rutas Públicas de Lectura:
```
GET /ofertas - Ver ofertas (público)
GET /empresas - Ver empresas (público)
GET /instituciones - Ver instituciones (público)
/api/resenas/** - API reseñas (público)
```

#### Rutas Protegidas por ROL:

| Rol | Rutas | Acciones |
|-----|-------|----------|
| **ADMIN** | `/usuarios/**`, `/admins/**` | CRUD completo de usuarios y admins |
| **EMPRESA** | `/ofertas/crear`, `/ofertas/editar/**`, `/ofertas/eliminar/**`, `/empresas/**` | Gestionar empresa y ofertas laborales |
| **CANDIDATO** | `/candidatos/**` | Gestionar perfil de candidato |
| **INSTITUCIÓN** | `/instituciones/nueva`, `/instituciones/editar/**`, `/instituciones/eliminar/**` | Gestionar instituciones |

#### Especificaciones de Seguridad:
- **ADMIN:** Acceso total a todos los endpoints (super usuario)
- **EMPRESA:** CRUD de ofertas, gestión de empresa
- **CANDIDATO:** Gestión de perfil de candidato
- **INSTITUCIÓN:** Gestión de instituciones
- Login en `/login` con redirección a `/home` después del login exitoso
- Logout en `/logout` con redirección a `/home`
- CSRF deshabilitado (considerarlo en producción)
- Cualquier ruta no especificada requiere autenticación

### 3. **pom.xml - Corrección de Compilación**
**Archivo:** `pom.xml`

#### Cambios:
- Removido `--enable-preview` del compilador para Java 21 (no compatible)
- Permitió que el proyecto compile correctamente

## Roles del Sistema

```
1. ADMIN (admins) ⭐ SUPER USUARIO
   - ACCESO TOTAL A TODO EL SISTEMA
   - Gestión completa de usuarios y otros admins
   - Crear, editar y eliminar ofertas de trabajo
   - Gestionar perfiles de empresa
   - Ver y gestionar candidatos
   - Crear, editar y eliminar instituciones
   - Acceso a panel administrativo

2. EMPRESA
   - Crear, editar y eliminar ofertas de trabajo
   - Gestionar perfil de empresa
   - Ver candidatos disponibles

3. CANDIDATO
   - Gestionar perfil de candidato
   - Ver y postularse a ofertas
   - Ver empresas

4. INSTITUCION
   - Crear, editar y eliminar instituciones
   - Gestionar información institucional
```

## Flujo de Autorización

1. **Usuario no autenticado:**
   - Accede a `/home` → Ve opciones públicas
   - Puede ver ofertas, empresas, instituciones (lectura)
   - Debe login para acceder a funcionalidades restringidas

2. **Usuario autenticado según rol:**
   - `/home` adapta el contenido según rol
   - Spring Security valida permisos en cada petición
   - Acceso denegado a rutas no autorizadas

## Ejemplo de URL Blocks en template:

```html
<!-- Solo ADMIN -->
<div sec:authorize="hasRole('ADMIN')">
    <a href="/usuarios">Ver Usuarios</a>
</div>

<!-- Solo EMPRESA -->
<div sec:authorize="hasRole('EMPRESA')">
    <a href="/ofertas/crear">Crear Oferta</a>
</div>

<!-- Solo CANDIDATO -->
<div sec:authorize="hasRole('CANDIDATO')">
    <a href="/candidatos">Mi Perfil</a>
</div>
```

## Implementación de Spring Security

### CustomUserDetailsService.java
- Carga usuarios por email
- Asigna roles automáticamente del enum `EUsuarioRol`
- Spring convierte roles a formato `ROLE_ADMIN`, `ROLE_EMPRESA`, etc.

### Usos de hasRole() en Thymeleaf
- `sec:authorize="hasRole('ADMIN')"` → Valida contra `EUsuarioRol.ADMIN`
- Spring Security automáticamente agrega el prefijo `ROLE_`

## Validación Realizada

✅ **Compilación:** El proyecto compila sin errores
✅ **Rutas:** Todas las rutas de controladores están protegidas
✅ **Roles:** Cuatro roles diferentes con permisos específicos
✅ **Template:** Home.html usa directivas de Thymeleaf para mostrar/ocultar por rol
✅ **Seguridad:** SecurityConfig valida permisos en cada petición

## Notas Importantes

1. **CSRF:** Está deshabilitado en la configuración. En producción, considerar habilitarlo.
2. **Roles:** Los cuatro roles del sistema son mutuamente exclusivos (un usuario debe tener uno)
3. **API REST:** Los endpoints REST (`/api/*`) también están protegidos
4. **Formularios:** El formulario de logout requiere POST en `/logout`

## Testing Recomendado

1. Login como ADMIN → Ver panel de administración
2. Login como EMPRESA → Ver opciones de crear ofertas
3. Login como CANDIDATO → Ver perfil y ofertas disponibles
4. Login como INSTITUCIÓN → Ver gestión de instituciones
5. No autenticado → Ver solo opciones públicas



