package com.example.AI_Agent.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {

    @Value("${spring.ai.openai.chat.model:openrouter/free}")
    private String modelName;

    @GetMapping({"/", "/index", "/dashboard"})
    public String index(Model model, @RequestParam(value = "tab", defaultValue = "chat") String tab) {
        model.addAttribute("activeTab", tab);
        model.addAttribute("modelName", modelName);
        model.addAttribute("appTitle", "Nexus AI • Autonomous Multi-Tool Agent");
        return "index";
    }

    @GetMapping("/chat")
    public String chat(Model model) {
        return index(model, "chat");
    }

    @GetMapping("/builder")
    public String builder(Model model) {
        return index(model, "builder");
    }

    @GetMapping("/tools")
    public String tools(Model model) {
        return index(model, "tools");
    }

    @GetMapping("/weather")
    public String weather(Model model) {
        return index(model, "weather");
    }

    @GetMapping("/currency")
    public String currency(Model model) {
        return index(model, "currency");
    }
}
