package com.example.AI_Agent.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Map /sites/** to the generated-sites folder on disk for live previewing
        Path workspace = Path.of("generated-sites").toAbsolutePath().normalize();
        String workspaceUri = workspace.toUri().toString();
        if (!workspaceUri.endsWith("/")) {
            workspaceUri += "/";
        }

        registry.addResourceHandler("/sites/**")
                .addResourceLocations(workspaceUri);
                
        // Static resources for webapp
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}
