#  Cancion App — Spring Boot MVC con Thymeleaf

Aplicación web MVC desarrollada con **Spring Boot**, **Spring MVC** y **Thymeleaf**, con persistencia en **PostgreSQL** mediante **Spring Data JPA**. El proyecto implementa una arquitectura por capas (Controller → Service → Repository → Entity), autenticación con **Spring Security**, y recuperación de contraseña por correo electrónico.

> Desarrollado como actividad académica de la asignatura Desarrollo Web — Universidad de Cartagena.

---

##  Tabla de contenido

- [Datos de la actividad](#-datos-de-la-actividad)
- [Tecnologías](#-tecnologías-utilizadas)
- [Arquitectura](#-arquitectura)
- [Funcionalidades](#-funcionalidades-implementadas)
- [Requisitos previos](#-requisitos-previos)
- [Variables de entorno](#-variables-de-entorno)
- [Ejecución local](#-ejecución-local)
- [Base de datos](#-base-de-datos)
- [Rutas de la aplicación](#-rutas-principales)
- [Despliegue en Render](#-despliegue-en-render)
- [Autor](#-autor)

---

##  Datos de la actividad

| Campo | Detalle |
|---|---|
| Asignatura | Desarrollo Web |
| Actividad | Spring Boot MVC con Thymeleaf: desarrollo web basado en framework |
| Modalidad | Individual |
| Ejercicio asignado | N.º 20 — Entidad **Cancion** |
| Enfoque | MVC tradicional con vistas del lado del servidor (no API RESTful) |

---

##  Tecnologías utilizadas

| Categoría | Tecnología |
|---|---|
| Lenguaje | Java 17 |
| Framework | Spring Boot 4.1.1 |
| Capa web | Spring MVC |
| Motor de plantillas | Thymeleaf |
| Persistencia | Spring Data JPA / Hibernate |
| Base de datos | PostgreSQL (hospedada en [Neon](https://neon.tech)) |
| Seguridad | Spring Security + BCrypt |
| Correo | Spring Mail (JavaMailSender) |
| Gestor de dependencias | Maven |
| Control de versiones | Git / GitHub |
| Hosting | [Render](https://render.com) |

---

##  Arquitectura

```
Navegador
   │
   ▼
Controller  ──►  Service  ──►  Repository  ──►  Base de datos (PostgreSQL)
   │                                                    │
   └────────────────►  Model  ◄────────────────────────┘
                         │
                         ▼
                     Thymeleaf
                         │
                         ▼
                    HTML → Navegador
```

- **Controller**: recibe las solicitudes HTTP, coordina el flujo de la operación y retorna el nombre de la vista a renderizar.
- **Service**: concentra la lógica de negocio.
- **Repository**: encapsula el acceso a datos mediante Spring Data JPA.
- **Entity/Model**: `Usuario` y `Cancion`, las entidades persistentes de la aplicación.
- **Thymeleaf**: genera las vistas HTML a partir de los datos enviados por el controlador mediante el objeto `Model`.

### Estructura de paquetes

```
edu.unicartagena.cancionapp
├── config/       → configuración de Spring Security
├── controller/   → controladores MVC (Cancion, Usuario, Auth, RecuperarClave)
├── model/        → entidades JPA (Usuario, Cancion)
├── repository/   → interfaces Spring Data JPA
└── service/      → lógica de negocio
```

---

##  Funcionalidades implementadas

- CRUD completo de **Usuario** (crear, listar, ver detalle, editar, eliminar)
- CRUD completo de **Cancion** (crear, listar, ver detalle, editar, eliminar)
- Autenticación con Spring Security: login, registro y logout
- Contraseñas encriptadas con BCrypt
- Recuperación de clave por correo electrónico (token único de un solo uso + JavaMailSender)
- Reportes / consultas parametrizadas:
    - Canciones por banda
    - Canciones por álbum
    - Usuarios por rol
    - Usuarios por nombre (búsqueda parcial)

---

##  Requisitos previos

- JDK 17 o superior
- Maven (o usar el wrapper `mvnw` incluido, no requiere instalación aparte)
- Una base de datos PostgreSQL (se usó [Neon](https://neon.tech), plan gratuito)
- Una cuenta de Gmail con una **Contraseña de aplicación** generada, para el envío de correos de recuperación

---

##  Variables de entorno

El proyecto no guarda credenciales sensibles en el código fuente; se leen desde variables de entorno tanto en desarrollo local como en producción:

| Variable | Descripción |
|---|---|
| `DB_PASSWORD` | Contraseña de la base de datos PostgreSQL (Neon) |
| `MAIL_PASSWORD` | Contraseña de aplicación de Gmail usada para enviar los correos de recuperación de clave |

La URL de conexión a la base de datos y el usuario de correo se configuran directamente en `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://<host-de-neon>/neondb?sslmode=require
spring.datasource.username=<usuario-de-neon>
spring.datasource.password=${DB_PASSWORD}

spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=<tu_correo>@gmail.com
spring.mail.password=${MAIL_PASSWORD}
```

---

##  Ejecución local

```bash
git clone https://github.com/Moises203548/desarrollo-web-spring-cancion.git
cd desarrollo-web-spring-cancion/cancionapp
```

**Windows (PowerShell):**
```powershell
$env:DB_PASSWORD="tu_password_de_neon"
$env:MAIL_PASSWORD="tu_app_password_de_gmail"
.\mvnw.cmd spring-boot:run
```

**Linux / Mac:**
```bash
export DB_PASSWORD="tu_password_de_neon"
export MAIL_PASSWORD="tu_app_password_de_gmail"
./mvnw spring-boot:run
```

La aplicación queda disponible en `http://localhost:8080`.

---




##  Rutas principales

| Ruta | Descripción |
|---|---|
| `/login` | Inicio de sesión |
| `/registro` | Registro de nuevo usuario |
| `/recuperar-clave` | Solicitud de recuperación de clave |
| `/recuperar-clave/restablecer?token=...` | Restablecer clave con el token recibido por correo |
| `/canciones` | Listado de canciones |
| `/canciones/nueva` | Formulario de nueva canción |
| `/canciones/{id}` | Detalle de una canción |
| `/canciones/{id}/editar` | Editar canción |
| `/canciones/{id}/eliminar` | Eliminar canción |
| `/canciones/reportes/por-banda` | Reporte parametrizado por banda |
| `/canciones/reportes/por-album` | Reporte parametrizado por álbum |
| `/usuarios` | Listado de usuarios |
| `/usuarios/nuevo` | Formulario de nuevo usuario |
| `/usuarios/{id}` | Detalle de un usuario |
| `/usuarios/{id}/editar` | Editar usuario |
| `/usuarios/{id}/eliminar` | Eliminar usuario |
| `/usuarios/reportes/por-rol` | Reporte parametrizado por rol |
| `/usuarios/reportes/por-nombre` | Reporte parametrizado por nombre |

---

##  Despliegue en Render

La aplicación está desplegada como un **Web Service** en [Render](https://render.com), conectado directamente a este repositorio de GitHub. La base de datos PostgreSQL utilizada en producción es la misma de Neon usada en desarrollo.

### 1. Crear el Web Service

1. Inicia sesión en [render.com](https://render.com) con tu cuenta de GitHub.
2. Dashboard → **New** → **Web Service**.
3. Selecciona el repositorio `desarrollo-web-spring-cancion`.
4. Configura:

| Campo | Valor |
|---|---|
| Name | `cancion-app` (o el que prefieras) |
| Region | La más cercana disponible |
| Root Directory | `cancionapp` *(la subcarpeta donde está el `pom.xml`)* |
| Runtime | Java |
| Build Command | `./mvnw clean package -DskipTests` |
| Start Command | `java -jar target/*.jar` |
| Instance Type | Free |

### 2. Configurar variables de entorno

En la sección **Environment** del Web Service, agrega:

| Key | Value |
|---|---|
| `DB_PASSWORD` | La contraseña de tu base de datos en Neon |
| `MAIL_PASSWORD` | Tu App Password de Gmail |

### 3. Desplegar

Haz clic en **Create Web Service**. Render clona el repositorio, ejecuta el build de Maven y levanta la aplicación automáticamente. Cada nuevo `git push` a la rama `main` dispara un redespliegue automático.

### 4. Verificar

Al finalizar, Render entrega una URL pública con el formato:

```
https://cancion-app.onrender.com
```



