package com.example.AI_Agent.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path workspace = Path.of("generated-sites").toAbsolutePath().normalize();
        try {
            Files.createDirectories(workspace);
        } catch (IOException ignored) {
        }

        String workspaceUri = workspace.toUri().toString();
        if (!workspaceUri.endsWith("/")) {
            workspaceUri += "/";
        }
        String workspaceFilePath = "file:" + workspace.toString().replace("\\", "/") + "/";

        // Map /sites/** to generated-sites workspace directory
        registry.addResourceHandler("/sites/**")
                .addResourceLocations(workspaceUri, workspaceFilePath)
                .setCachePeriod(0);

        // Static resources for webapp
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}
