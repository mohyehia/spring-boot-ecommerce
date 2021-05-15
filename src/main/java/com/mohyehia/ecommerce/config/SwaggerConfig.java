package com.mohyehia.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mohyehia.ecommerce.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "ECommerce Api Documentation",
                "Spring boot ecommerce api documentation",
                "1.0",
                "Terms of Service",
                new Contact("Mohammed Yehia", "https://linkedin.com/in/moh-yehia", "mohammedyehia99@gmail.com"),
                "Mit-licence",
                "https://www.google.com",
                Collections.emptyList()
        );
    }
}
