package com.smartvoting.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.smartvoting.controller"))
                .paths(PathSelectors.any()).build().apiInfo(apiInfo());
//                .securitySchemes(Collections.singletonList(apiKey()));
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("REST API Document").description("description for api")
                .version("1.0").build();
    }
//    private ApiKey apiKey() {
//        return new ApiKey("apiKey", "Authorization",
//                "header"); //`apiKey` is the name of the APIKey, `Authorization` is the key in the request header
//    }
}
