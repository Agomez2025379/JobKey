# Guía Visual de Navegación por Rol - JobKey Home

## 📱 Pantalla de Home - Vista General

### 1️⃣ Usuario NO AUTENTICADO
```
┌─────────────────────────────────────────────────────────────┐
│                        JOBKEY                    [Login]    │
├─────────────────────────────────────────────────────────────┤
│                                                               │
│          Welcome to JobKey                                   │
│     Connecting talent with the best opportunities            │
│                                                               │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐       │
│  │ Candidates   │  │  Companies   │  │  Job Offers  │       │
│  │ Explore      │  │ Discover     │  │ Browse all   │       │
│  │ [View]       │  │ [View]       │  │ [View]       │       │
│  └──────────────┘  └──────────────┘  └──────────────┘       │
│                                                               │
│  ┌──────────────┐                                            │
│  │ Institutions │                                            │
│  │ Discover     │                                            │
│  │ [View]       │                                            │
│  └──────────────┘                                            │
│                                                               │
└─────────────────────────────────────────────────────────────┘
```

---

### 2️⃣ Usuario ADMIN (⭐ SUPER USUARIO - ACCESO TOTAL)
```
┌─────────────────────────────────────────────────────────────┐
│       JOBKEY              Bienvenido, admin@...  [Logout]   │
├─────────────────────────────────────────────────────────────┤
│                                                               │
│         Welcome to JobKey                                    │
│                                                               │
│  ╔═══════════════════════════════════════════════════════╗   │
│  ║ 👨‍💼 ADMINISTRATION PANEL                              ║   │
│  ║ Full access to all system features                   ║   │
│  ║ [View Users] [View Admins] [Create Admin]           ║   │
│  ╚═══════════════════════════════════════════════════════╝   │
│                                                               │
│  ╔═══════════════════════════════════════════════════════╗   │
│  ║ 💼 JOB OFFERS MANAGEMENT                              ║   │
│  ║ Full control over job offers                         ║   │
│  ║ [View Job Offers] [Create New Offer]                ║   │
│  ╚═══════════════════════════════════════════════════════╝   │
│                                                               │
│  ╔═══════════════════════════════════════════════════════╗   │
│  ║ 🏢 COMPANY MANAGEMENT                                ║   │
│  ║ Manage all companies in the system                   ║   │
│  ║ [View All Companies]                                ║   │
│  ╚═══════════════════════════════════════════════════════╝   │
│                                                               │
│  ╔═══════════════════════════════════════════════════════╗   │
│  ║ 👥 CANDIDATE MANAGEMENT                              ║   │
│  ║ Manage all candidates in the system                  ║   │
│  ║ [View All Candidates]                               ║   │
│  ╚═══════════════════════════════════════════════════════╝   │
│                                                               │
│  ╔═══════════════════════════════════════════════════════╗   │
│  ║ 🎓 INSTITUTION MANAGEMENT                            ║   │
│  ║ Manage all institutions in the system                ║   │
│  ║ [View All Institutions] [Create Institution]        ║   │
│  ╚═══════════════════════════════════════════════════════╝   │
│                                                               │
└─────────────────────────────────────────────────────────────┘

ADMIN puede acceder a TODO:
├── /usuarios (listar, editar, eliminar usuarios)
├── /admins (listar, crear, editar, eliminar admins)
├── /ofertas (listar, crear, editar, eliminar ofertas)
├── /candidatos (ver todos los candidatos)
├── /empresas (ver todas las empresas)
├── /instituciones (listar, crear, editar, eliminar)
└── Panel completo de administración
```

---

### 3️⃣ Usuario CANDIDATO
```
┌─────────────────────────────────────────────────────────────┐
│       JOBKEY        Bienvenido, candidato@...  [Logout]     │
├─────────────────────────────────────────────────────────────┤
│                                                               │
│         Welcome to JobKey                                    │
│                                                               │
│  ╔═══════════════════════════════════════════════════════╗   │
│  ║ 👤 CANDIDATE PROFILE                                 ║   │
│  ║ Manage your candidate profile and applications.     ║   │
│  ║                                                       ║   │
│  ║ [My Profile] [Browse Job Offers] [View Companies]  ║   │
│  ╚═══════════════════════════════════════════════════════╝   │
│                                                               │
│  ┌─────────────────────────────────────────────────────┐     │
│  │ 📋 GENERAL CONTENT                                  │     │
│  │ [All Job Offers] [All Candidates] [Companies] ...  │     │
│  └─────────────────────────────────────────────────────┘     │
│                                                               │
└─────────────────────────────────────────────────────────────┘

Candidato puede acceder a:
├── /candidatos (ver/crear/editar perfil)
├── /candidatos/editar/{id}
├── /candidatos/eliminar/{id}
├── /ofertas (listar ofertas - lectura)
└── /empresas (listar empresas - lectura)
```

---

### 4️⃣ Usuario EMPRESA
```
┌─────────────────────────────────────────────────────────────┐
│       JOBKEY          Bienvenido, empresa@...  [Logout]     │
├─────────────────────────────────────────────────────────────┤
│                                                               │
│         Welcome to JobKey                                    │
│                                                               │
│  ╔═══════════════════════════════════════════════════════╗   │
│  ║ 🏢 COMPANY MANAGEMENT                                ║   │
│  ║ Manage your company profile and job offers.         ║   │
│  ║                                                       ║   │
│  ║ [My Company] [View Job Offers]                      ║   │
│  ║ [+ CREATE NEW OFFER] [Browse Candidates]           ║   │
│  ╚═══════════════════════════════════════════════════════╝   │
│                                                               │
│  ┌─────────────────────────────────────────────────────┐     │
│  │ 📋 GENERAL CONTENT                                  │     │
│  │ [All Job Offers] [All Candidates] [Companies] ...  │     │
│  └─────────────────────────────────────────────────────┘     │
│                                                               │
└─────────────────────────────────────────────────────────────┘

Empresa puede acceder a:
├── /empresas (ver/crear/editar empresa)
├── /empresas/editar/{id}
├── /empresas/eliminar/{id}
├── /ofertas (listar - lectura)
├── /ofertas/crear (crear oferta laboral)
├── /ofertas/editar/{id} (editar oferta)
├── /ofertas/eliminar/{id} (eliminar oferta)
└── /candidatos (listar - lectura)
```

---

### 5️⃣ Usuario INSTITUCIÓN
```
┌─────────────────────────────────────────────────────────────┐
│    JOBKEY       Bienvenido, institucion@...  [Logout]       │
├─────────────────────────────────────────────────────────────┤
│                                                               │
│         Welcome to JobKey                                    │
│                                                               │
│  ╔═══════════════════════════════════════════════════════╗   │
│  ║ 🎓 INSTITUTION MANAGEMENT                            ║   │
│  ║ Manage educational institutions.                    ║   │
│  ║                                                       ║   │
│  ║ [View Institutions] [+ CREATE INSTITUTION]         ║   │
│  ╚═══════════════════════════════════════════════════════╝   │
│                                                               │
│  ┌─────────────────────────────────────────────────────┐     │
│  │ 📋 GENERAL CONTENT                                  │     │
│  │ [All Job Offers] [All Candidates] [Companies] ...  │     │
│  └─────────────────────────────────────────────────────┘     │
│                                                               │
└─────────────────────────────────────────────────────────────┘

Institución puede acceder a:
├── /instituciones (listar instituciones)
├── /instituciones/nueva (crear institución)
├── /instituciones/editar/{id} (editar institución)
└── /instituciones/eliminar/{id} (eliminar institución)
```

---

## 🔒 Matriz de Permisos

| Recurso/Acción | Anónimo | Admin | Empresa | Candidato | Institución |
|---|---|---|---|---|---|
| Ver Ofertas | ✅ | ✅ | ✅ | ✅ | ✅ |
| Crear Oferta | ❌ | ✅ | ✅ | ❌ | ❌ |
| Editar Oferta | ❌ | ✅ | ✅ | ❌ | ❌ |
| Eliminar Oferta | ❌ | ✅ | ✅ | ❌ | ❌ |
| Ver Usuarios | ❌ | ✅ | ❌ | ❌ | ❌ |
| Editar Usuarios | ❌ | ✅ | ❌ | ❌ | ❌ |
| Eliminar Usuarios | ❌ | ✅ | ❌ | ❌ | ❌ |
| Ver Candidatos | ✅ | ✅ | ✅ | ✅ | ✅ |
| Perfil Candidato | ❌ | ✅ | ❌ | ✅ | ❌ |
| Ver Empresas | ✅ | ✅ | ✅ | ✅ | ✅ |
| Perfil Empresa | ❌ | ✅ | ✅ | ❌ | ❌ |
| Ver Instituciones | ✅ | ✅ | ✅ | ✅ | ✅ |
| Crear Institución | ❌ | ✅ | ❌ | ❌ | ✅ |
| Editar Institución | ❌ | ✅ | ❌ | ❌ | ✅ |
| Eliminar Institución | ❌ | ✅ | ❌ | ❌ | ✅ |
| Gestionar Admins | ❌ | ✅ | ❌ | ❌ | ❌ |
| Gestionar Admins | ❌ | ✅ | ❌ | ❌ | ❌ |

---

## 🔐 Reglas de Seguridad Implementadas

### En SecurityConfig.java:

```java
// 1. Rutas públicas (sin login)
- / y /home
- /login
- /register
- /css/**, /js/**, /images/**

// 2. Lectura pública (todos)
- GET /ofertas
- GET /empresas
- GET /instituciones

// 3. Solo con rol específico
- /usuarios/** → ADMIN
- /admins/** → ADMIN
- /ofertas/crear → EMPRESA
- /ofertas/editar/** → EMPRESA
- /ofertas/eliminar/** → EMPRESA
- /candidatos/** → CANDIDATO
- /empresas/** → EMPRESA
- /instituciones/nueva → INSTITUCION
- /instituciones/editar/** → INSTITUCION
- /instituciones/eliminar/** → INSTITUCION

// 4. Cualquier otra ruta requiere autenticación
```

---

## 🚀 Flujo de Login

```
1. Usuario no autenticado accede a cualquier ruta protegida
                              ↓
2. Spring Security redirige a /login
                              ↓
3. Usuario ingresa credenciales y envía formulario
                              ↓
4. CustomUserDetailsService carga el usuario y su rol
                              ↓
5. Autenticación exitosa
                              ↓
6. Redirige a /home
                              ↓
7. AuthController retorna home.html
                              ↓
8. Thymeleaf renderiza contenido según rol con sec:authorize
                              ↓
9. Usuario ve solo opciones autorizadas para su rol
```

---

## ✅ Casos de Uso

### Caso 1: Candidato intenta crear oferta
```
GET /ofertas/crear
└── Spring Security verifica rol
    └── Usuario tiene rol CANDIDATO
        └── No tiene permiso para EMPRESA
            └── 403 Forbidden → Redirige a acceso denegado
```

### Caso 2: Empresa crea oferta laboral (exitoso)
```
GET /ofertas/crear
└── Spring Security verifica rol
    └── Usuario tiene rol EMPRESA
        └── Tiene permiso ✅
            └── Carga formulario ofertas-form.html
                └── Empresa completa datos
                    └── POST /ofertas/crear
                        └── Oferta guardada
                            └── Redirige a /ofertas
```

### Caso 3: Admin gestiona usuarios
```
GET /usuarios
└── Spring Security verifica rol
    └── Usuario tiene rol ADMIN
        └── Tiene permiso ✅
            └── Carga lista de usuarios
                └── Admin puede editar/eliminar usuarios
```

---

## 📝 Notas para Testing

1. **Crear usuarios de prueba:**
   ```
   Email: admin@jobkey.com → Rol: ADMIN
   Email: empresa@jobkey.com → Rol: EMPRESA
   Email: candidato@jobkey.com → Rol: CANDIDATO
   Email: institucion@jobkey.com → Rol: INSTITUCION
   ```

2. **Verificar redirecciones:**
   - Candidato → /ofertas/crear (debe ser 403 Forbidden)
   - Empresa → /usuarios (debe ser 403 Forbidden)
   - Admin → /empresas/editar/1 (debe ser 403 Forbidden)

3. **Verificar home según rol:**
   - Cada rol debe ver solo sus btnss autorizados
   - Los botones deben apuntar a rutas válidas



