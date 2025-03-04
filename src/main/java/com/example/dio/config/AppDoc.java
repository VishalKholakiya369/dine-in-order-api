package com.example.dio.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class AppDoc {


    @Bean
     OpenAPI openAPI(){
        return new OpenAPI().info(info());
    }

    private  Info info() {
        return new Info()
                .title("Dine In Order API")
                .description("""
                        ## Description
                        **Dine In Order** is an API built using Spring Boot under REST Architecture.
                        The API is designe to seve as a backend to a application tht deals in ordering food online.
                        
                        ## Tech-Stack
                        - Java 8
                        - Spring Boot
                        - Spring Data JPA
                        - MySQL Database
                        - Spring Security
                        """)
                .version("v1")
                .contact(contact());
    }

    private Contact contact(){
        return new Contact().email("vishalkholakiya369@gmail.com")
                .url("https://google.com")
                .name("google");
    }

}
