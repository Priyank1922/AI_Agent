package com.example.AI_Agent.AI_Tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

@Component
public class CalculatorTool {

	@Tool(description = """
			Performs arithmetic calculations.
			Supported operations: add, subtract, multiply, divide, mod, power.
			""")
	public double calculate(
			@ToolParam(description = "Operation: add, subtract, multiply, divide, mod, power") String operation,

			@ToolParam(description = "First number") double a,

			@ToolParam(description = "Second number") double b) {

		System.out.println("Calculator tool called: " + operation + " with a=" + a + ", b=" + b);

		if (operation == null) {
			throw new IllegalArgumentException("Operation cannot be null");
		}

		String op = operation.trim().toLowerCase();

		if (op.equals("add") || op.equals("+") || op.equals("addition") || op.equals("plus") || op.equals("sum")) {
			return a + b;
		} else if (op.equals("subtract") || op.equals("-") || op.equals("subtraction") || op.equals("minus")) {
			return a - b;
		} else if (op.equals("multiply") || op.equals("*") || op.equals("x") || op.equals("multiplication") || op.equals("times") || op.equals("product")) {
			return a * b;
		} else if (op.equals("divide") || op.equals("/") || op.equals("division")) {
			if (b == 0) {
				throw new IllegalArgumentException("Cannot divide by 0");
			}
			return a / b;
		} else if (op.equals("mod") || op.equals("%") || op.equals("modulo") || op.equals("remainder")) {
			if (b == 0) {
				throw new IllegalArgumentException("Cannot calculate mod by 0");
			}
			return a % b;
		} else if (op.equals("power") || op.equals("^") || op.equals("pow") || op.equals("exponent")) {
			return Math.pow(a, b);
		} else {
			throw new IllegalArgumentException("Unsupported operation: " + operation);
		}
	}
}