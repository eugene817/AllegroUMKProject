package com.example.repo_info.Config;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI repoInfoOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Repo Info API")
                        .version("1.0.0")
                        .description("Service to get info of the github repos")
                        .contact(new Contact()
                                .name("Yauheni Luferchyk")
                                .email("316721@stud.umk.pl")
                        )
                );
    }
}
