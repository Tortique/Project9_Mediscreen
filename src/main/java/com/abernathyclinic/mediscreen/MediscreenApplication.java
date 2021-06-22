package com.abernathyclinic.mediscreen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication
@EnableFeignClients("com.abernathyclinic.mediscreen.service")
public class MediscreenApplication extends SpringBootServletInitializer {
    @Bean
    public InternalResourceViewResolver jspViewResolver() {
        InternalResourceViewResolver resourceViewResolver = new InternalResourceViewResolver();
        resourceViewResolver.setPrefix("/WEB-INF/");
        resourceViewResolver.setSuffix(".jsp");
        return resourceViewResolver;
    }

    public static void main(String[] args) {
        SpringApplication.run(MediscreenApplication.class, args);
    }
}
