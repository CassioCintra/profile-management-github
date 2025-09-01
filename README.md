# Profile Management GitHub

## Overview
This project is a user and role management system built with **Java 21** and **Spring Boot 3**. It provides secure 
authentication and authorization, database versioning, full API documentation, and integrates with the **GitHub API** 
to fetch users when the application starts.

---

## Key Technologies and Design Patterns

- **Java 21 & Spring Boot 3**: For fast and efficient development.
- **Spring Security & JWT**: Stateless authentication and authorization using JWT tokens, generated and validated for 
each request.
- **Flyway**: Database migration management to ensure schema integrity and versioning.
- **JPA & Hibernate**: Persistence layer for managing entities.
- **Testcontainers**: Integration testing with database and external API environments in Docker containers, ensuring 
robust and reliable tests.

---

## Implemented Features

- **User Authentication**: Users can log in using login and password.
- **Role Management**: Assign and manage roles for users.
- **Secure Endpoints**: JWT tokens are used to protect sensitive endpoints.

---

## Authentication & Authorization

- **Protected Endpoints**: Creating roles and adding roles to users, require a valid JWT token in the `Authorization` header: 
```
    Authorization: Bearer <your-jwt-token>
```

- **Public Endpoints**: Fetch users and the user registration endpoints are publicly accessible without authentication.

---

## Getting Started

To run the project locally:

1. Ensure **Docker** and **Docker Compose** are installed.
2. Clone the repository:

```bash 
    git clone https://github.com/CassioCintra/profile-management-github.git
```

3. Navigate to the project:
```bash
    cd profile-management-github
```

4. Navigate to the project root and run:
```bash
    mvn spring-boot:run
```
5. The API will be available at:
```
    http://localhost:8080
```
You can also start the application directly from your IDE by running `ProfileManagementGithubApplication.java`.

---

## API Documentation

The API is fully documented using OpenAPI 3 (Springdoc). You can access it via:

Swagger UI (interactive interface): http://localhost:8080/swagger-ui.html

OpenAPI JSON specification: http://localhost:8080/v3/api-docs
