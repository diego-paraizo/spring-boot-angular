package com.paraizo.spring_boot_server.configuration;

import com.paraizo.spring_boot_server.model.User;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gestão de Usuários - Paraizo")
                        .version("1.0")
                        .description("Sistema para cadastro de usuários com vínculo de origem (Magistrados, Funcionários, etc.)")
                        );
    }
}
