package com.ontariotechu.sofe3980U;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BinaryController {

	@GetMapping("/")
	public String getCalculator(@RequestParam(name="operand1", required=false, defaultValue="") String operand1, Model model) {
		model.addAttribute("operand1", operand1);
		model.addAttribute("operand1Focused", operand1.length() > 0);
		return "calculator";
	}

	@PostMapping("/")
	public String result(@RequestParam(name="operand1", required=false, defaultValue="") String operand1,
						 @RequestParam(name="operator", required=false, defaultValue="") String operator,
						 @RequestParam(name="operand2", required=false, defaultValue="") String operand2, Model model) {

		// Add operands and operator to model for the view
		model.addAttribute("operand1", operand1);
		model.addAttribute("operator", operator);
		model.addAttribute("operand2", operand2);

		// Create Binary objects from the input strings
		Binary number1 = new Binary(operand1);
		Binary number2 = new Binary(operand2);
		Binary result = null;

		// Switch based on the operator
		switch (operator) {
			case "+":
				result = Binary.add(number1, number2);
				break;
			case "&":
				result = Binary.and(number1, number2);  // AND operation
				break;
			case "|":
				result = Binary.or(number1, number2);  // OR operation
				break;
			case "*":
				result = Binary.multiply(number1, number2);  // Multiplication operation
				break;
			default:
				model.addAttribute("error", "Invalid operator: " + operator);  // Invalid operator handling
				return "Error";  // Return error view
		}

		// Add the result to the model and return the result view
		model.addAttribute("result", result.getValue());
		return "result";
	}
}
