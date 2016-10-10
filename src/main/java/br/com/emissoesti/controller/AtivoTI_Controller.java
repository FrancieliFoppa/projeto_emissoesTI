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
	
	private AtivoTI_DAO ativoDAO;

	public AtivoTI_Controller() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * método lê as linhas de um arquivo CSV
	 * layout do arquivo -> hostname;fabricante;consumoEnergia;custoEnergia
	 */
	@RequestMapping //(chamar a view)
	public void processaCSV(String path){

		//instacia o ativoTI
		AtivoTI ativoTI = new AtivoTI();
		
		//cria variavel do tipo File
		File arquivoCSV;
		
		try{
			arquivoCSV = new File(path);
			
			String linhasArquivo = new String();
			//leitor do arquivo
			Scanner leitor = new Scanner(arquivoCSV); 
			//ignora o cabeçalho do arquivo
			leitor.nextLine();
			
			//le cada linha do arquivo
			while(leitor.hasNext()){

				linhasArquivo = leitor.nextLine();
				//salva cada campo separado por ponto e virgula em um array
				String valores[] = linhasArquivo.split(";");
					
					//atribui os valores veindos do arquivo para os atributos do objeto ativoTI
					ativoTI.setHostName(valores[0]);
					ativoTI.setFabricante(valores[1]);
					ativoTI.setConsumoEnergia(Double.parseDouble(valores[2]));
				
					System.out.println(" nome: " + ativoTI.getHostName() +" Frabricante: " +  ativoTI.getFabricante() + " Conumo: " + ativoTI.getConsumoEnergia());
					
				//this.registra(ativoTI);
				
			}	
			//fecha o Scanner
			leitor.close();
			
		}catch(java.io.FileNotFoundException x){
         System.out.println("O arquivo não existe");
        }catch(java.io.IOException es){
         System.out.println("Erro ao abrir o arquivo");
        }
	}
	
	
	//registra um ativo de TI
	@RequestMapping("/registraAtivoTI")
	public String registra(@Validated AtivoTI ativoTI, BindingResult validacao){
		System.out.println(ativoTI.getHostName());
		if (validacao.hasErrors()) {
			return "ativo_novo";
		}else{
			this.ativoDAO.adiciona(ativoTI);
			return "ativo_sucesso";
		}
	}

	//sobrecarga do método registra
	private String registra(AtivoTI ativoTI){
		this.ativoDAO.adiciona(ativoTI);
		return "ativo_sucesso";
	}
}
