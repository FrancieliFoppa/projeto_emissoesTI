package br.com.emissoesti.controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import br.com.emissoesti.DAO.AtivoTI_DAO;
import br.com.emissoesti.model.AtivoTI;

@Path("/ativoti")
public class AtivoTI_Controller {
	
	private AtivoTI_DAO ativoDAO;

	public AtivoTI_Controller() {
		ativoDAO = new AtivoTI_DAO();
	}
	
	/*
	 * método lê as linhas de um arquivo CSV
	 * layout do arquivo -> hostname;fabricante;consumoEnergia;custoEnergia
	 */
	//---->(chamar a view)
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public ArrayList<AtivoTI> processaCSV(String path) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		
		//instancia uma lista de ativos
		ArrayList<AtivoTI> listaAtivos = new ArrayList<AtivoTI>();
		
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
				
				//instancia o ativoTI
				AtivoTI ativoTI = new AtivoTI();
				
				//próxima linha do arquivo
				linhasArquivo = leitor.nextLine();
				
				//salva cada campo separado por ponto e virgula em um array
				String valores[] = linhasArquivo.split(";");
					
					//atribui os valores veindos do arquivo para os atributos do objeto ativoTI
					ativoTI.setHostName(valores[0]);
					ativoTI.setFabricante(valores[1]);
					ativoTI.setModelo(valores[2]);
					ativoTI.setCategoria(valores[3]);
					ativoTI.setConsumoEnergia(Double.parseDouble(valores[4]));
					ativoTI.setHorasConsumo(Double.parseDouble(valores[5]));
					ativoTI.setTipoConsumo(valores[6]);
					
					listaAtivos.add(ativoTI);
				
					System.out.println("Nome: " + ativoTI.getHostName() +" Fabricante: " +  ativoTI.getFabricante() + "Modelo: " + ativoTI.getModelo() + "Categoria: " + ativoTI.getCategoria() +
							" Consumo: " + ativoTI.getConsumoEnergia() + "Hrs Consumo: " + ativoTI.getHorasConsumo() + "Tipo Consumo: " + ativoTI.getTipoConsumo());
								
			}	
			//fecha o Scanner
			leitor.close();
			
		}catch(java.io.FileNotFoundException x){
         System.out.println("O arquivo não existe");
        }
		//registrar no banco
		this.registra(listaAtivos);
		
		return listaAtivos;
	}
	
	/*
	 * método lê as linhas de um arquivo XML
	 * layout do arquivo -> <ativos>
			<ativoTI>
				<hostName></hostName>
				<fabricante></fabricante>
				<consumoEnergia></consumoEnergia>
			</ativoTI>
	 */
	 //----->(chamar a view)
	@POST
	@Consumes(MediaType.APPLICATION_ATOM_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public ArrayList<AtivoTI> processaXML(String path) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		
		ArrayList<AtivoTI> listaAtivos = new ArrayList<AtivoTI>();
		
		try{
			FileReader reader = new FileReader(path);
			//instacia um obejto do tipo XStrem
			XStream xstream = new XStream(new DomDriver());
			//procura a tag ativoTI
			xstream.alias("ativoTI", AtivoTI.class);
			//atribui os atributos da tag ao objeto ativoTI
			AtivoTI ativoTI = (AtivoTI) xstream.fromXML(reader);
			//adiciona o obejto a lista
			listaAtivos.add(ativoTI);
			
			System.out.println("Nome: " + ativoTI.getHostName() +" Fabricante: " +  ativoTI.getFabricante() + "Modelo: " + ativoTI.getModelo() + "Categoria: " + ativoTI.getCategoria() +
					" Consumo: " + ativoTI.getConsumoEnergia() + "Hrs Consumo: " + ativoTI.getHorasConsumo() + "Tipo Consumo: " + ativoTI.getTipoConsumo());
			
			registra(listaAtivos);
			
		}catch(IOException io){
			System.out.println("Erro ao abrir XML");
		}
		return listaAtivos;
	}

	//chama o método que faz a conexão com o banco 
	private String registra(ArrayList<AtivoTI> ativoTIList) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		this.ativoDAO.adiciona(ativoTIList);
		return "ativo_sucesso";
	}
	
	//método busca o ativo com maior consumo de energia
	//("/maiorAtivoTI")
	public String retornaMaior(){
		this.ativoDAO.retornaMaxAtivo();
		return ""; //view ou relatório?
	}
	/*
	@RequestMapping("/buscaListaAtivoTI")
	public String RetornaListaAtivo(Usuario codigoUsuario) throws SQLException{
		this.ativoDAO.listaAtivo(codigoUsuario);
		return "";	//view ou relatório?
	}
	*/
}
