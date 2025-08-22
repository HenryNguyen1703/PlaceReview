package vn.ngochieu.com.config.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Info info = new Info()
                .title("Place Review")
                .version("1.0")
                .description("A platform that allows users to discover places, share reviews, and explore trusted ratings.");
        return new OpenAPI().info(info);
    }
}
