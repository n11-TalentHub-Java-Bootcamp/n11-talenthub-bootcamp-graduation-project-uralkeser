package com.loanapplication.library;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.info.Info;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("loan-application-api")
                .pathsToMatch("/osmanuralkeser/**")
                .build();
    }
    @Bean
    public OpenAPI customOpenAPI(@Value("Loan Application Api") String description, @Value("1.0") String version) {
        return new OpenAPI()
                .info(new Info()
                        .title("Loan Application Api")
                        .version(version)
                        .description(description)
                        .license(new License().name("Loan Application API licence")));
    }

}