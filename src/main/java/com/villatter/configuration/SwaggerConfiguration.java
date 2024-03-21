package com.villatter.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI openApiDocumentation(){
        var license = new License()
                .name("Apache 2.0")
                .url("http://springdoc.org");
        var info = new Info()
                .title("Villatter Open API Documentation")
                .description("All Endpoint Villatter Documentation")
                .version("v 1.0.0")
                .license(license);
        var externalDocumentation = new ExternalDocumentation()
                .description("Villatter MVC")
                .url("/");
        var schemeName = "bearerAuth";
        var securityRequirement = new SecurityRequirement()
                .addList(schemeName);
        var securityScheme = new SecurityScheme()
                .name(schemeName)
                .type(SecurityScheme.Type.HTTP)
                .scheme("Bearer")
                .bearerFormat("JWT");
        var component = new Components()
                .addSecuritySchemes(schemeName, securityScheme);

        var openAPI = new OpenAPI()
                .info(info)
                .externalDocs(externalDocumentation)
                .addSecurityItem(securityRequirement)
                .components(component);
        return openAPI;
    }
}
