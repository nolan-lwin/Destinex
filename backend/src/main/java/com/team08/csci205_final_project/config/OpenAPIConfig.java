package com.team08.csci205_final_project.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI openApiInformation() {
        Server localServer = new Server()
                .url("http://localhost:8080")
                .description("Localhost Server URL");

        Contact contact = new Contact()
                .email("hqp001@bucknell.edu")
                .name("Hung Pham");

        Info info = new Info()
                .contact(contact)
                .description("The documentation has the same version as the back-end code you run." +
                        "\nThis documentation does not contain WebSocket endpoints." +
                        "\nTo access secured endpoint, login using auth-controller and copy the JWT to the green authorize on top of the page")
                .summary("Demo of Spring Boot 3 & Open API 3 Integration")
                .title("API Documentation and User Instructions")
                .version("V1.0.0")
                .license(new License().name("Apache 2.0").url("http://springdoc.org"));

        return new OpenAPI().info(info).addServersItem(localServer).components(new io.swagger.v3.oas.models.Components()
                .addSecuritySchemes("bearerAuth", new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                ));
    }
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public-api")
                .pathsToMatch("/api/**")
                .build();
    }

    @Bean
    public GroupedOpenApi userApi() {
        return GroupedOpenApi.builder()
                .group("user-api")
                .pathsToMatch("/api/users/**", "/api/auth/**")
                .build();
    }

    @Bean
    public GroupedOpenApi jobApi() {
        return GroupedOpenApi.builder()
                .group("job-api")
                .pathsToMatch("/api/jobs/**")
                .build();
    }

    @Bean
    public GroupedOpenApi providerApi() {
        return GroupedOpenApi.builder()
                .group("provider-api")
                .pathsToMatch("/api/provider/**")
                .build();
    }
}
