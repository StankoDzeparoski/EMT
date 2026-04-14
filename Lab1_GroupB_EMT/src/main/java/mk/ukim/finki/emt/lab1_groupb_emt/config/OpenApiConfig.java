package mk.ukim.finki.emt.lab1_groupb_emt.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI/Swagger configuration for the EMT Lab1 Group B API.
 * This configuration provides API documentation and testing interface via Swagger UI.
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "EMT Lab1 Group B - Accommodation Management API",
                version = "1.0.0",
                description = "API for managing accommodations with advanced features:\n" +
                        "- Accommodation listing with pagination and filtering\n" +
                        "- Activity logging and tracking\n" +
                        "- Event handling for rentals and fully occupied status\n" +
                        "- Database views and materialized views\n" +
                        "- Scheduled materialized view refresh\n" +
                        "- Statistics and analytics",
                contact = @Contact(
                        name = "EMT Lab Team",
                        email = "lab@example.com"
                ),
                license = @License(
                        name = "MIT License"
                )
        ),
        servers = {
                @Server(
                        url = "http://localhost:8080",
                        description = "Local Development Server"
                )
        }
)
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        description = "JWT token for API authentication",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}

