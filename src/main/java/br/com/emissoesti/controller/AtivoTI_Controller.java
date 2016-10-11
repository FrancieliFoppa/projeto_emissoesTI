package br.com.emissoesti.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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

		//instancia o ativoTI
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
				
				//registrar no banco
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
	
	/*
	 * método lê as linhas de um arquivo XML
	 * layout do arquivo -> hostname;fabricante;consumoEnergia;custoEnergia
	 */
	@RequestMapping //(chamar a view)
	public void processaXML(String path){
		
		//instancia o ativoTI
		AtivoTI ativoTI = new AtivoTI();
		
		ArrayList<AtivoTI> listaAtivos;
		
		try{
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			//cria o documento XML
			Document arquivoXML = builder.parse(path);
			
			//instacia um array de objetos ativoTI
			listaAtivos = new ArrayList<AtivoTI>();
			
			//instacia um NodeList para salvar os dados do xml
			NodeList infAtivos = arquivoXML.getElementsByTagName("ativoTI");
			
			//percorre o NodeList e salva no array de ativosTI
			for(int i = 0; i <= infAtivos.getLength(); i++){
				
				Node nodoAtivo = infAtivos.item(i);
				
				//valida se o nodo não é apenas texto
				if(nodoAtivo.getNodeType() == Node.ELEMENT_NODE){
					
					//transforma o nodo em elemento
					Element elementoAtivoTI = (Element) nodoAtivo;
					
					//seta os parâmetros da classe AtivoTI com os dados vindos do XML
					ativoTI.setHostName(elementoAtivoTI.getAttribute("nome"));
					ativoTI.setFabricante(elementoAtivoTI.getAttribute("fabricante"));
					ativoTI.setConsumoEnergia(Double.parseDouble(elementoAtivoTI.getAttribute("consumoEnergia")));
					
					//salvar os elementos como obejtos AtivoTI
					listaAtivos.add(ativoTI);
					
					//registrar no banco
					//this.registra(ativoTI);
				}
			}
		}catch(ParserConfigurationException ex){
			System.out.println("Erro...");
		}catch(IOException io){
			System.out.println("Erro ao abrir XML");
		}catch(SAXException sx){
			System.out.println("Erro ao criar XML");
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
