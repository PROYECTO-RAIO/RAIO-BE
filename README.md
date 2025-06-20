# RAIO Backend

Backend del proyecto **Reverberacions d'Amor i Odi**, una plataforma que permite recolectar mensajes de usuarios, generar reverberaciones (respuestas creativas), y visualizar conexiones simbólicas entre estos contenidos.

---

##  Descripción General

Este backend expone APIs REST construidas con **Java 21** y **Spring Boot 3.5.0**, diseñadas para:

- Almacenar y consultar mensajes enviados por usuarios.
- Crear y consultar "reverberaciones" asociadas a estos mensajes.
- Gestionar registros de auditoría (uso administrativo).
- Integrarse con servicios externos para generación de contenido.

---

##  Tecnologías Usadas

- **Lenguaje**: Java 21
- **Framework**: Spring Boot
- **Persistencia**: JPA con base de datos relacional (ej. PostgreSQL o H2)
- **Validación**: Bean Validation (javax)
- **Seguridad**: JWT
- **Despliegue**: Docker y PM2 (según scripts observados)
- **Build Tool**: Maven

---

##  Variables de Entorno

A configurar en `application.properties` o archivo `.env` externo:

DB_URL=jdbc:postgresql://localhost:54**/raio
DB_USERNAME=usuarie
DB_PASSWORD=contraseña
JWT_SECRET=clave_segura

---

##  Endpoints Principales

| Método | Ruta                     | Descripción                                 |
|--------|--------------------------|---------------------------------------------|
| GET    | `/api/messages`          | Obtener mensajes paginados                  |
| POST   | `/api/reverberations`    | Crear una reverberación                     |      

> Algunos endpoints pueden requerir autenticación JWT.

---

## Modelos de Datos

Ejemplo de entidad `Reverberation`:


public class Reverberation {
    private UUID id;
    private String title;
    private String description;
    private Frequency frequency; // ALWAYS, RANDOM, INTERVAL
    private Medium medium;       // DIGITAL, ANALOG
    private boolean isActive;
    private Metadata metadata;

    public static class Metadata {
        private String creator;
        private int delay;
    }
}
 Instalación Local
Clona el repositorio:

git clone https://github.com/PROYECTO-RAIO/RAIO-BE.git
cd RAIO-BE
Configura las variables de entorno en application.properties o .env.

Ejecuta con Maven:
./mvnw spring-boot:run
También puedes iniciar con Docker:

docker-compose up -d
🧪 Testing
(Si se encuentran tests en el proyecto)

./mvnw test
⚙️ Despliegue
Pasos típicos de despliegue:

Configurar .env o application.properties.

Crear contenedores:
docker-compose up -d

Desplegar con PM2 (si aplica):
pm2 start server.js
Asegúrate de tener configurada la base de datos antes de iniciar el servicio.

📝 Licencia
Este proyecto se desarrolla como parte de una iniciativa artística/educativa sin fines comerciales.

## 👩‍💻 Equipo del Proyecto RAIO

Este proyecto ha sido desarrollado por un equipo multidisciplinar de mujeres apasionadas por la tecnología:

- [**Kat Leverton**](https://www.linkedin.com/in/kat-leverton/) — Desarrolladora
- [**Carolina Más**](https://www.linkedin.com/in/carolina-mas/) — Desarrolladora
- [**Mariona Cuyàs**](https://www.linkedin.com/in/mariona-cuyas/) — Product Owner & Desarrolladora
- [**Karisha Melendez**](https://www.linkedin.com/in/karisssha/) — Desarrolladora
- [**Marta Ibarra**](https://www.linkedin.com/in/marta-ibarra-cumbrero/) — Scrum Master & Desarrolladora
