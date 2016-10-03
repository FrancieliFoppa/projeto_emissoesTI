package br.com.emissoesti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//Esta anotação avisa o Spring que esta classe implementa um Controller
@Controller
public class OlaMundoController {

	/*
	 * Anotação que mapeia a requisição com url “/testeOlaMundo” para encontrar o método OlaMundo()
	 */
	@RequestMapping("/testeOla")
	public String OlaMundo(){
		return "ola";
	}
	
}
