package br.com.emissoesti.controller;

import java.io.File;
import java.util.Scanner;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.emissoesti.DAO.AtivoTI_DAO;
import br.com.emissoesti.model.AtivoTI;

@ControllerAdvice
public class AtivoTI_Controller {
	
	private AtivoTI ativoTI;
	private AtivoTI_DAO ativoDAO;

	public AtivoTI_Controller() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * método lê as linhas de um arquivo CSV
	 * layout do arquivo -> nome;energia;custo;fabricante
	 */
	@RequestMapping
	public void processaCSV(String path){

		//instacia o ativoTI
		ativoTI = new AtivoTI();
		
		//cria variavel do tipo File
		File arquivoCSV = new File(path);
		
		try{
			String linhasArquivo;
			//leitor do arquivo
			Scanner leitor = new Scanner(arquivoCSV); 
			//ignora o cabeçalho do arquivo
			leitor.nextLine();
			
			//le cada linha do arquivo
			while(leitor.hasNext()){
				linhasArquivo = leitor.nextLine();
				//salva cada campo separado por ponto e virgula em um array
				String [] valores = linhasArquivo.split(";");
				ativoTI.setHostName(valores[0]);
				ativoTI.setConsumoEnergia(Double.parseDouble(valores[1]));
				ativoTI.setValorEmissaoCO(Double.parseDouble(valores[2]));
				ativoTI.setFabricante(valores[3]);
				
				leitor.close();
				
				this.registra(ativoTI);
			}	
		}catch(java.io.FileNotFoundException x){
         System.out.println("O arquivo não existe");
        }catch(java.io.IOException es){
         System.out.println("Erro ao abrir o arquivo");
        }
	}
	
	@RequestMapping("/registraProduto")
	public String registra(@Validated AtivoTI ativoTI){ //valid ou validated
		BindingResult validacao=null; //deve entrar como parametro, mas o que recebe? string?
		System.out.println(ativoTI.getHostName());
		if (validacao.hasErrors()) {
			return "ativo_novo";
		}else{
			this.ativoDAO.adiciona(ativoTI);
			return "ativo_sucesso";
		}
	}
}
