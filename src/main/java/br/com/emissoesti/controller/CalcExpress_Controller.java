package br.com.emissoesti.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.emissoesti.model.AtivoTI;

@Controller
public class CalcExpress_Controller {

	private Object ativoDAO;

	@RequestMapping("/registraProduto")
	public String registra(@Validated AtivoTI ativoTI){ //valid ou validated
		BindingResult validacao=null; //deve entrar como parametro, mas o que recebe? string?
		System.out.println(ativoTI.getHostName());
		if (validacao.hasErrors()) {
			return "ativo_novo";
		}else{
			return "ativo_sucesso";
		}
	}

}
