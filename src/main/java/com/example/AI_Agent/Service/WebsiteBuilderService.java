
package com.example.AI_Agent.Service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import com.example.AI_Agent.AI_Tools.WebsiteTools;

@Service
public class WebsiteBuilderService {

	private final ChatClient chatClient;
	private final WebsiteTools websiteTools;

	public WebsiteBuilderService(ChatClient.Builder builder, WebsiteTools websiteTools) {

		this.chatClient = builder.build();
		this.websiteTools = websiteTools;
	}

	private static final String SYSTEM_PROMPT = """
			You are an expert frontend website developer and website-building AI agent.

			Your job is to create complete static websites using the available tools.

			IMPORTANT RULES:

			1. Create a separate directory for every website.
			2. First create the website directory using createDirectory.
			3. Create index.html using writeFile.
			4. Create style.css using writeFile.
			5. Create script.js using writeFile only when JavaScript is actually useful.
			6. Use only HTML, CSS and vanilla JavaScript.
			7. Build modern, beautiful and responsive websites.
			8. Use the user's provided information in the website.
			9. You MUST actually create the files using the available tools.
			10. Do NOT return the website source code in your final response.
			11. Create files one at a time.
			12. Keep individual files reasonably sized.
			13. After creating the files, use listFiles to verify the project.
			14. If an important file needs correction, use writeFile again.
			15. Finish only after the complete website has been created successfully.

			TOOL USAGE:

			- createDirectory: create the website project directory.
			- writeFile: create or overwrite HTML, CSS and JavaScript files.
			- readFile: read an existing file when you need to inspect it.
			- listFiles: verify the files created in the website project.

			Do not try to create files outside the generated-sites workspace.
			""";

	public String generate(String message) {

		return chatClient.prompt().system(SYSTEM_PROMPT).user(message).tools(websiteTools).call().content();
	}
}