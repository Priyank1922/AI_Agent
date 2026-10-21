package com.example.AI_Agent.AI_Tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

@Component
public class WebsiteTools {

	private final Path workspace = Path.of("generated-sites").toAbsolutePath().normalize();

	public WebsiteTools() {
		try {
			Files.createDirectories(workspace);
		} catch (IOException e) {
			throw new IllegalStateException("Could not create website workspace", e);
		}
	}

	public Path getWorkspace() {
		return workspace;
	}

	@Tool(description = "Creates a new directory inside the website workspace.")
	public String createDirectory(
			@ToolParam(description = "Relative directory path, for example spotify or portfolio") String path) {

		try {
			Path directory = safePath(path);
			Files.createDirectories(directory);
			return "Directory created successfully: " + path;
		} catch (Exception e) {
			return "Failed to create directory: " + e.getMessage();
		}
	}

	@Tool(description = """
			Creates or overwrites a text file inside the website workspace.
			Use this to create HTML, CSS and JavaScript files.
			""")
	public String writeFile(
			@ToolParam(description = "Relative file path, for example spotify/index.html") String path,
			@ToolParam(description = "Complete content that should be written into the file") String content) {

		try {
			Path file = safePath(path);
			if (file.getParent() != null) {
				Files.createDirectories(file.getParent());
			}
			Files.writeString(file, content != null ? content : "", StandardCharsets.UTF_8);
			return "File written successfully: " + path;
		} catch (Exception e) {
			return "Failed to write file: " + e.getMessage();
		}
	}

	@Tool(description = "Reads the contents of an existing file from the website workspace.")
	public String readFile(@ToolParam(description = "Relative file path, for example spotify/index.html") String path) {

		try {
			Path file = safePath(path);
			if (!Files.exists(file)) {
				return "File does not exist: " + path;
			}
			return Files.readString(file, StandardCharsets.UTF_8);
		} catch (Exception e) {
			return "Failed to read file: " + e.getMessage();
		}
	}

	@Tool(description = "Lists all files and directories inside a website project.")
	public String listFiles(@ToolParam(description = "Relative directory path, for example spotify") String path) {

		try {
			Path directory = safePath(path);

			if (!Files.exists(directory)) {
				return "Directory does not exist: " + path;
			}

			try (var files = Files.walk(directory)) {
				return files.filter(file -> !file.equals(directory))
						.map(workspace::relativize)
						.map(Path::toString)
						.map(p -> p.replace("\\", "/"))
						.collect(Collectors.joining("\n"));
			}
		} catch (Exception e) {
			return "Failed to list files: " + e.getMessage();
		}
	}

	public void saveProjectFiles(String slug, String html, String css, String js) throws IOException {
		Path projectDir = safePath(slug);
		Files.createDirectories(projectDir);

		if (html != null && !html.trim().isEmpty()) {
			Files.writeString(projectDir.resolve("index.html"), html, StandardCharsets.UTF_8);
		}
		if (css != null && !css.trim().isEmpty()) {
			Files.writeString(projectDir.resolve("style.css"), css, StandardCharsets.UTF_8);
		}
		if (js != null && !js.trim().isEmpty()) {
			Files.writeString(projectDir.resolve("script.js"), js, StandardCharsets.UTF_8);
		}
	}

	public Path safePath(String path) {
		if (path == null || path.trim().isEmpty()) {
			return workspace;
		}

		String cleanPath = path.trim().replace("\\", "/");
		// Strip leading slashes to prevent Windows Path.resolve from escaping workspace root
		while (cleanPath.startsWith("/")) {
			cleanPath = cleanPath.substring(1);
		}

		if (cleanPath.isEmpty()) {
			return workspace;
		}

		Path resolved = workspace.resolve(cleanPath).normalize();

		if (!resolved.startsWith(workspace)) {
			throw new IllegalArgumentException("Access outside generated-sites is not allowed: " + path);
		}

		return resolved;
	}
}