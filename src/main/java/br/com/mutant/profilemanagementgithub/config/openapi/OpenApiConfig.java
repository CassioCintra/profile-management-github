package br.com.mutant.profilemanagementgithub.config.openapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Profile Management Github.",
        version = "1.0.0",
        description = "Technical challenge for managing GitHub profiles.",
        contact = @Contact(
            name = "Cássio Cintra Rosa",
            email = "cassiocr2012@hotmail.com",
            url = "https://www.linkedin.com/in/cassio-ccr/"
        )
    ),
    servers = { @Server(url = "http://localhost:8080", description = "Local Development Server") },
    security = { @SecurityRequirement(name = "bearerAuth") }
)
@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT",
    description = "JWT token must be provided in the 'Authorization' header using the format: 'Bearer <token>'"
)
public class OpenApiConfig {}
