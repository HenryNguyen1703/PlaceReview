package vn.ngochieu.com.common.config.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    final String jwtScheme = "bearerAuth";

    @Bean
    public OpenAPI customOpenAPI() {
        Info info = new Info()
                .title("Place Review")
                .version("1.0")
                .description("A platform that allows users to discover places, share reviews, and explore trusted ratings.");
        return new OpenAPI().info(info).addSecurityItem(new SecurityRequirement()
                        .addList(jwtScheme)
                )
                .components(new io.swagger.v3.oas.models.Components()
                        // Jwt (Bearer) Auth
                        .addSecuritySchemes(jwtScheme,
                                new io.swagger.v3.oas.models.security.SecurityScheme()
                                        .name(jwtScheme)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT"))
                );
    }
}
