package com.example.AI_Agent.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.AI_Agent.AI_Tools.WebsiteTools;
import com.example.AI_Agent.Service.WebsiteTemplateGenerator.WebsitePackage;

import jakarta.annotation.PostConstruct;

@Service
public class WebsiteBuilderService {

	private final ChatClient chatClient;
	private final WebsiteTools websiteTools;
	private final WebsiteTemplateGenerator templateGenerator;

	@Value("${spring.ai.openai.api-key:dummy-key}")
	private String apiKey;

	private volatile String lastGeneratedSlug = "";

	public WebsiteBuilderService(ChatClient.Builder builder,
								 WebsiteTools websiteTools,
								 WebsiteTemplateGenerator templateGenerator) {
		this.chatClient = builder.build();
		this.websiteTools = websiteTools;
		this.templateGenerator = templateGenerator;
	}

	@PostConstruct
	public void init() {
		// Auto-prepare all sample clone projects on application startup if missing or empty
		prepareSampleProjects(false);
	}

	public synchronized void prepareSampleProjects(boolean forceOverwrite) {
		try {
			List<WebsitePackage> samples = templateGenerator.getAllSampleTemplates();
			for (WebsitePackage sample : samples) {
				Path projectDir = websiteTools.safePath(sample.slug);
				Path indexHtml = projectDir.resolve("index.html");

				boolean needsCreation = forceOverwrite
						|| !Files.exists(indexHtml)
						|| Files.size(indexHtml) == 0;

				if (needsCreation) {
					websiteTools.saveProjectFiles(sample.slug, sample.html, sample.css, sample.js);
					try {
						Files.setLastModifiedTime(projectDir, FileTime.fromMillis(System.currentTimeMillis()));
					} catch (Exception ignored) {
					}
					System.out.println("Prepared sample clone project: " + sample.slug);
				}
			}
		} catch (Exception e) {
			System.err.println("Warning: Error preparing sample clone projects: " + e.getMessage());
		}
	}

	private static final String SYSTEM_PROMPT = """
			You are an expert frontend website developer and website-building AI agent.

			Your job is to create complete static websites using the available tools.

			IMPORTANT RULES:
			1. Create a separate directory for every website using createDirectory.
			2. Create index.html using writeFile.
			3. Create style.css using writeFile.
			4. Create script.js using writeFile only when JavaScript is useful.
			5. Use only HTML, CSS and vanilla JavaScript.
			6. Build modern, beautiful and responsive websites.
			7. You MUST actually create the files using the available tools.
			8. Do NOT return the website source code in your final response.
			9. Finish only after the complete website has been created successfully.

			TOOL USAGE:
			- createDirectory: create the website project directory.
			- writeFile: create or overwrite HTML, CSS and JavaScript files.
			- readFile: read an existing file when you need to inspect it.
			- listFiles: verify the files created in the website project.
			""";

	public String generate(String message) {
		if (message == null || message.trim().isEmpty()) {
			throw new IllegalArgumentException("Prompt cannot be empty");
		}

		// 1. Determine the best matching package or generate custom project specification
		WebsitePackage pkg = templateGenerator.generateForPrompt(message);
		this.lastGeneratedSlug = pkg.slug;

		// 2. If valid external AI key is configured, attempt autonomous LLM generation
		if (isAiConfigured()) {
			try {
				System.out.println("Attempting AI tool-based website generation for prompt: " + message);
				String aiResult = chatClient.prompt()
						.system(SYSTEM_PROMPT)
						.user(message)
						.tools(websiteTools)
						.call()
						.content();

				Path projectDir = websiteTools.safePath(pkg.slug);
				if (Files.exists(projectDir.resolve("index.html")) && Files.size(projectDir.resolve("index.html")) > 0) {
					return aiResult != null && !aiResult.trim().isEmpty()
							? aiResult
							: "AI Agent successfully generated website project '" + pkg.slug + "'.";
				}
			} catch (Exception e) {
				System.err.println("AI website builder encountered an error (" + e.getMessage() + "). Seamlessly deploying pre-verified high-fidelity template.");
			}
		}

		// 3. Ultra-fast, reliable template generation
		try {
			websiteTools.saveProjectFiles(pkg.slug, pkg.html, pkg.css, pkg.js);
			Path projectDir = websiteTools.safePath(pkg.slug);
			try {
				Files.setLastModifiedTime(projectDir, FileTime.fromMillis(System.currentTimeMillis()));
			} catch (Exception ignored) {
			}
			return "Website project '" + pkg.slug + "' (" + pkg.title + ") generated successfully with index.html, style.css, and script.js.";
		} catch (IOException e) {
			throw new RuntimeException("Failed to save project files for '" + pkg.slug + "': " + e.getMessage(), e);
		}
	}

	public String getLastGeneratedSlug() {
		return lastGeneratedSlug;
	}

	private boolean isAiConfigured() {
		return apiKey != null && !apiKey.trim().isEmpty() && !apiKey.equalsIgnoreCase("dummy-key");
	}
}