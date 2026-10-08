package com.example.AI_Agent.Controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AI_Agent.Service.WebsiteBuilderService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/website")
public class WebsiteBuilderController {

    private final WebsiteBuilderService websiteService;
    private final Path workspace = Path.of("generated-sites").toAbsolutePath().normalize();

    public WebsiteBuilderController(WebsiteBuilderService websiteService) {
        this.websiteService = websiteService;
        try {
            Files.createDirectories(workspace);
        } catch (IOException ignored) {
        }
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE, "*/*"})
    public ResponseEntity<Map<String, Object>> generateWebsite(@RequestBody(required = false) Object payload) {
        String message = "";
        if (payload instanceof Map<?, ?> map) {
            Object msgObj = map.get("message");
            message = msgObj != null ? msgObj.toString() : "";
        } else if (payload instanceof String str) {
            if (str.trim().startsWith("{") && str.contains("\"message\"")) {
                try {
                    int startIdx = str.indexOf("\"message\"");
                    int colonIdx = str.indexOf(":", startIdx);
                    int firstQuote = str.indexOf("\"", colonIdx);
                    int lastQuote = str.lastIndexOf("\"");
                    if (firstQuote != -1 && lastQuote > firstQuote) {
                        message = str.substring(firstQuote + 1, lastQuote);
                    } else {
                        message = str;
                    }
                } catch (Exception e) {
                    message = str;
                }
            } else {
                message = str;
            }
        }

        Map<String, Object> response = new HashMap<>();
        if (message == null || message.trim().isEmpty()) {
            response.put("success", false);
            response.put("error", "Website prompt is required");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            String result = websiteService.generate(message);
            response.put("success", true);
            response.put("response", result);
            
            // Look for recently created project folders
            List<Map<String, Object>> projects = getProjectsList();
            response.put("projects", projects);
            if (!projects.isEmpty()) {
                response.put("latestProject", projects.get(0).get("name"));
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping("/projects")
    public ResponseEntity<List<Map<String, Object>>> listProjects() {
        return ResponseEntity.ok(getProjectsList());
    }

    @GetMapping("/project/{name}/files")
    public ResponseEntity<Map<String, Object>> getProjectFiles(@PathVariable("name") String name) {
        Map<String, Object> result = new HashMap<>();
        Path projectPath = workspace.resolve(name).normalize();

        if (!projectPath.startsWith(workspace) || !Files.exists(projectPath) || !Files.isDirectory(projectPath)) {
            result.put("error", "Project not found");
            return ResponseEntity.badRequest().body(result);
        }

        Map<String, String> filesContent = new HashMap<>();
        List<String> fileList = new ArrayList<>();

        try (Stream<Path> stream = Files.walk(projectPath)) {
            stream.filter(Files::isRegularFile).forEach(path -> {
                String relative = projectPath.relativize(path).toString().replace("\\", "/");
                fileList.add(relative);
                try {
                    String content = Files.readString(path, StandardCharsets.UTF_8);
                    filesContent.put(relative, content);
                } catch (IOException e) {
                    filesContent.put(relative, "/* Error reading file: " + e.getMessage() + " */");
                }
            });
        } catch (IOException e) {
            result.put("error", e.getMessage());
            return ResponseEntity.internalServerError().body(result);
        }

        result.put("projectName", name);
        result.put("previewUrl", "/sites/" + name + "/index.html");
        result.put("files", fileList);
        result.put("contents", filesContent);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/project/{name}")
    public ResponseEntity<Map<String, Object>> deleteProject(@PathVariable("name") String name) {
        Map<String, Object> result = new HashMap<>();
        Path projectPath = workspace.resolve(name).normalize();

        if (!projectPath.startsWith(workspace) || !Files.exists(projectPath)) {
            result.put("error", "Project not found");
            return ResponseEntity.badRequest().body(result);
        }

        try (Stream<Path> stream = Files.walk(projectPath)) {
            stream.sorted(Comparator.reverseOrder()).forEach(p -> {
                try {
                    Files.deleteIfExists(p);
                } catch (IOException ignored) {
                }
            });
            result.put("success", true);
            result.put("message", "Project " + name + " deleted successfully.");
            return ResponseEntity.ok(result);
        } catch (IOException e) {
            result.put("error", e.getMessage());
            return ResponseEntity.internalServerError().body(result);
        }
    }

    private List<Map<String, Object>> getProjectsList() {
        List<Map<String, Object>> projects = new ArrayList<>();
        if (!Files.exists(workspace)) {
            return projects;
        }

        try (Stream<Path> stream = Files.list(workspace)) {
            stream.filter(Files::isDirectory).forEach(dir -> {
                Map<String, Object> info = new HashMap<>();
                String dirName = dir.getFileName().toString();
                info.put("name", dirName);
                info.put("previewUrl", "/sites/" + dirName + "/index.html");
                
                try {
                    long fileCount;
                    try (Stream<Path> fileStream = Files.walk(dir)) {
                        fileCount = fileStream.filter(Files::isRegularFile).count();
                    }
                    info.put("fileCount", fileCount);
                    info.put("hasIndex", Files.exists(dir.resolve("index.html")));
                    info.put("hasCss", Files.exists(dir.resolve("style.css")));
                    info.put("hasJs", Files.exists(dir.resolve("script.js")));
                    info.put("lastModified", Files.getLastModifiedTime(dir).toMillis());
                } catch (Exception e) {
                    info.put("fileCount", 0);
                    info.put("hasIndex", false);
                }
                projects.add(info);
            });
        } catch (IOException ignored) {
        }

        projects.sort((a, b) -> Long.compare((Long) b.getOrDefault("lastModified", 0L), (Long) a.getOrDefault("lastModified", 0L)));
        return projects;
    }
}