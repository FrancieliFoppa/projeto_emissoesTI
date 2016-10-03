package br.com.emissoesti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//Esta anota��o avisa o Spring que esta classe implementa um Controller
@Controller
public class OlaMundoController {

	/*
	 * Anota��o que mapeia a requisi��o com url �/testeOlaMundo� para encontrar o m�todo OlaMundo()
	 */
	@RequestMapping("/testeOla")
	public String OlaMundo(){
		return "ola";
	}
	
}
