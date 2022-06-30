package com.douglas.algafood;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MeuPrimeiroControle {
	@GetMapping("/hello")
	@ResponseBody
	public String helo() {
		return "Mundo";
	}

}
