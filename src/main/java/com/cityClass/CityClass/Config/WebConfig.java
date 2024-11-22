package com.cityClass.CityClass.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Разрешаем все пути
                .allowedOrigins("http://127.0.0.1:5500") // Разрешаем только ваш фронтенд
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Разрешаем только указанные методы
                .allowedHeaders("*"); // Разрешаем все заголовки
    }
}