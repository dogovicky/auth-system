package com.capricon.Authentication_System.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                WebMvcConfigurer.super.addCorsMappings(registry);
                registry.addMapping("/**") // adjust for your routes
                        .allowedOrigins("http://localhost:5173") // front-end origin
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // methods to allow
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }

}
