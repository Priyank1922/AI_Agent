package com.example.AI_Agent.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.AI_Agent.AI_Tools.CalculatorTool;
import com.example.AI_Agent.AI_Tools.CurrencyExchangeTool;
import com.example.AI_Agent.AI_Tools.WeatherTool;
import com.example.AI_Agent.Service.ChatService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ChatController {

    private final ChatService chatService;
    private final WeatherTool weatherTool;
    private final CurrencyExchangeTool currencyExchangeTool;
    private final CalculatorTool calculatorTool;

    public ChatController(ChatService chatService, 
                          WeatherTool weatherTool, 
                          CurrencyExchangeTool currencyExchangeTool, 
                          CalculatorTool calculatorTool) {
        this.chatService = chatService;
        this.weatherTool = weatherTool;
        this.currencyExchangeTool = currencyExchangeTool;
        this.calculatorTool = calculatorTool;
    }

    @PostMapping(value = "/chat", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE, "*/*"})
    public ResponseEntity<Map<String, Object>> chat(@RequestBody(required = false) Object payload) {
        String message = "";
        if (payload instanceof Map<?, ?> map) {
            Object msgObj = map.get("message");
            message = msgObj != null ? msgObj.toString() : "";
        } else if (payload instanceof String str) {
            // Check if it's a JSON string like {"message": "..."}
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
            response.put("error", "Message cannot be empty");
            response.put("response", "Please provide a prompt or message.");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            String aiResponse = chatService.chat(message);
            response.put("success", true);
            response.put("response", aiResponse);
            response.put("messageCount", chatService.getMessageCount());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", e.getMessage());
            response.put("response", "Error processing request: " + e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @PostMapping("/chat/clear")
    public ResponseEntity<Map<String, Object>> clearHistory() {
        chatService.clearHistory();
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Chat history cleared successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/weather")
    public ResponseEntity<Map<String, Object>> getWeather(@RequestParam(defaultValue = "London") String city) {
        Map<String, Object> res = new HashMap<>();
        try {
            String weatherData = weatherTool.currentWeather(city);
            res.put("success", true);
            res.put("city", city);
            res.put("data", weatherData);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.put("success", false);
            res.put("city", city);
            res.put("error", e.getMessage());
            return ResponseEntity.ok(res);
        }
    }

    @GetMapping("/currency")
    public ResponseEntity<Map<String, Object>> getCurrency(
            @RequestParam(defaultValue = "USD") String from,
            @RequestParam(defaultValue = "EUR") String to) {
        Map<String, Object> res = new HashMap<>();
        try {
            String rateData = currencyExchangeTool.getExchangeRate(from.toUpperCase(), to.toUpperCase());
            res.put("success", true);
            res.put("from", from.toUpperCase());
            res.put("to", to.toUpperCase());
            res.put("data", rateData);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.put("success", false);
            res.put("error", e.getMessage());
            return ResponseEntity.ok(res);
        }
    }

    @GetMapping("/calculator")
    public ResponseEntity<Map<String, Object>> calculate(
            @RequestParam String operation,
            @RequestParam double a,
            @RequestParam double b) {
        Map<String, Object> res = new HashMap<>();
        try {
            double result = calculatorTool.calculate(operation, a, b);
            res.put("success", true);
            res.put("operation", operation);
            res.put("a", a);
            res.put("b", b);
            res.put("result", result);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.put("success", false);
            res.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(res);
        }
    }
}