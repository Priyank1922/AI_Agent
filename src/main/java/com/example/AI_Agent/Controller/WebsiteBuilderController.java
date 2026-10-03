package com.example.AI_Agent.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AI_Agent.Service.WebsiteBuilderService;

@RestController
@RequestMapping("/website")
public class WebsiteBuilderController {
	private final WebsiteBuilderService websiteService;

	public WebsiteBuilderController(WebsiteBuilderService websiteService) {
		this.websiteService = websiteService;
	}

	@PostMapping
	public String generateWebsite(@RequestBody String message) {
		return websiteService.generate(message);
	}
}