# API Mail - Email Management API

An old high school project modernized to run with Spring Boot 3.x and Java 17+.

## What Changed

This project has been **completely modernized** from Spring Boot 1.4 (2016) to Spring Boot 3.2.1 (2024):

### Major Updates
- ✅ **Spring Boot**: 1.4.0 → 3.2.1
- ✅ **Java**: 8 → 17
- ✅ **Database Layer**: Raw JDBC → Spring Data JPA
- ✅ **Jakarta EE**: `javax.servlet` → `jakarta.servlet`
- ✅ **Date/Time API**: Joda-Time → Java Time API (LocalDateTime)
- ✅ **Logging**: Log4j → SLF4J + Logback
- ✅ **MySQL Driver**: old `mysql-connector-java` → `mysql-connector-j`
- ✅ **H2 Database**: Added for development without MySQL

### Database Changes
The project now uses:
- **Spring Data JPA** with repositories instead of raw JDBC
- **JPA annotations** on entity classes (`@Entity`, `@Table`, etc.)
- **H2 in-memory database** for development (no MySQL required!)
- **MySQL support** still available for production via Docker

## Running the Application

### Option 1: Development Mode (H2 Database - No MySQL needed!)

```bash
cd apimail
mvn spring-boot:run -Dspring-boot.run.profiles=dev -Dmaven.test.skip=true
```

The app will start on `http://localhost:8080`
- H2 Console: `http://localhost:8080/h2-console`
  - JDBC URL: `jdbc:h2:mem:testdb`
  - Username: `sa`
  - Password: (leave empty)

### Option 2: Production Mode (MySQL)

1. Start MySQL using Docker:
```bash
cd /Users/apple/Documents/api-mail
docker compose up -d
```

2. Run the application:
```bash
cd apimail
mvn spring-boot:run -Dmaven.test.skip=true
```

## Project Structure

```
apimail/
├── Model/           - JPA Entities (Usuario, Mensaje)
├── Repository/      - Spring Data JPA Repositories
├── Services/        - Business logic layer
├── Control/         - REST Controllers
├── Converter/       - DTO converters
├── Request/         - Request DTOs
├── Response/        - Response DTOs
├── Session/         - Session management
└── Dao/             - ⚠️ OLD JDBC code (needs removal)
```

## Known Issues

⚠️ **The old JDBC DAO classes still exist** and are causing conflicts with the new JPA repositories. They need to be removed or refactored:
- `Dao/Conexion.java` - Old JDBC connection
- `Dao/DaoMensajes.java` - Should use `MensajeRepository` instead
- `Dao/DaoUsuarios.java` - Should use `UsuarioRepository` instead  
- `Configuracion.java` - Old connection configuration

The Services and Controllers need to be updated to use the new JPA repositories instead of the old DAOs.

## API Endpoints

- `/usuarios` - User management
- `/mensajes` - Message management
- `/session` - Session/authentication

## Technologies

- Spring Boot 3.2.1
- Spring Data JPA
- H2 Database (development)
- MySQL 8.0 (production)
- Java 17
- Maven

## Development Notes

- Tests are currently disabled as they use old JUnit 4 and PowerMock (need migration to JUnit 5)
- The application uses session-based authentication with scheduled cleanup
- Hibernate auto-generates database schema based on JPA entities
