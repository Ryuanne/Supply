package org.example.demo.config;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import springfox.documentation.spi.DocumentationType;

import springfox.documentation.spring.web.plugins.Docket;

import springfox.documentation.builders.PathSelectors;

import springfox.documentation.builders.RequestHandlerSelectors;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
@Configuration

@EnableSwagger2

public class SwaggerConfig {

    @Bean

    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)

                .select()

                .apis(RequestHandlerSelectors.basePackage("org.example.demo.controller"))

                .paths(PathSelectors.any())

                .build();

    }

}




