package one.digitalinnovation.personapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
@RequestMapping("/")
public class HomeController {
	
	@GetMapping
	public String redirectToSwagger() {
		return "redirect:swagger-ui/";
	}
	

}
