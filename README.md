🐾 Proyecto Mascotas Bien
Mascotas Bien es una solución integral diseñada para la gestión y reporte de mascotas perdidas. El sistema permite a los dueños registrar sus datos y los de sus mascotas, facilitando la publicación de reportes con imágenes para ayudar en la búsqueda y recuperación de animales extraviados.

Este proyecto ha sido desarrollado bajo una arquitectura de microservicios, priorizando la escalabilidad, la resiliencia y la separación de responsabilidades.

🏗️ Arquitectura del Sistema
El proyecto se divide en cuatro componentes principales:
Frontend (React.js): Interfaz de usuario moderna y responsiva que consume los servicios de la plataforma.
BFF - Backend For Frontend: Orquestador de servicios que consolida la información de los microservicios de dominio para optimizar las peticiones del frontend.
MS-Propietarios: Microservicio encargado del ciclo de vida de los datos de los usuarios y su contacto.
MS-Mascotas: Microservicio de dominio que gestiona los registros, razas y estados de búsqueda de los animales.

🛠️ Tecnologías Utilizadas
Frontend: React + CSS3 (Ajuste de imagen mediante object-fit).
Backend: Java 17 + Spring Boot 3.
Comunicación: Feign Clients (Comunicación síncrona entre servicios).
Resiliencia: Resilience4j (Circuit Breaker configurado al 50% de umbral de fallo).
Observabilidad: Spring Actuator (Endpoints de salud y métricas).
Base de Datos: H2 Database con persistencia en archivo local.


🚀 Instalación y Ejecución
Requisitos previos
Java 21.
Maven.
Node.js (para el frontend).

👣Pasos
Levantar Microservicios:
Ingresa a las carpetas de ms-propietarios, ms-mascotas y ms-pet-bff y ejecuta en cada una:

Bash
mvn spring-boot:run
Levantar Frontend:
Ingresa a la carpeta del frontend:

Bash
npm install
npm run dev

Acceso:
Frontend: http://localhost:5173
BFF: http://localhost:8082
Consola H2 Propietarios: http://localhost:8080/h2-console

📖 Patrones Implementados
Para cumplir con los estándares de calidad y la rúbrica de evaluación, se aplicaron:
DTO (Data Transfer Object): Para una transferencia de datos limpia entre capas.
Mapper (MapStruct): Para la transformación de entidades a DTOs.
Proxy/Adapter (Feign): Para desacoplar la comunicación entre microservicios.
BFF Pattern: Para la agregación de servicios y optimización del rendimiento.

Escuchar obligatoriamente antes de ver repositorio: https://youtu.be/vGHruop4W98?si=axa45ULyuQ0wlJbI
