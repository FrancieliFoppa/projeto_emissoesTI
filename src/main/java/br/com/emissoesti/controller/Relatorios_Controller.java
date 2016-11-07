package br.com.emissoesti.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import br.com.emissoesti.DAO.AtivoTI_DAO;
import br.com.emissoesti.model.AtivoTI;
import br.com.emissoesti.model.Relatorios;

@Path("/relatorios")
public class Relatorios_Controller extends Relatorios{

	public Relatorios_Controller() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Método gera um relatório em PDF com as informaões dos ativos de TI e suas respectivas
	 *  quantidades de emissão CO²
	 */
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces("application/pdf")
	public Document relatorioAtivosEmissao(ArrayList<AtivoTI> listaDeAtivos){
		
		//cria o documento vazio
		Document relatorioEmissao = new Document();
		
		//busca o diretorio para salvar o PDF na maquina do usuário
		String path = System.getProperty("user.home") + System.getProperty("file.separator");
		
		try{
			//cria uma instacia do documento com nome e diretorio destino
			PdfWriter.getInstance(relatorioEmissao, new FileOutputStream(path + "\\testePDF.pdf"));
			
			//abri o docuemnto
			relatorioEmissao.open();
			
			//seta tamanho da página do relatório
			relatorioEmissao.setPageSize(PageSize.A4);
			
			//escreve cabeçalho
			relatorioEmissao.addHeader("Cabecalho", "Texto Cabeçalho");
			
			//cria imagem para o relatório
			Image logo = Image.getInstance("C:\\testes\\logo.png");
			
			//seta o tamanho da imagem
			logo.scaleToFit(150, 40);
			
			//adiciona a imagem ao relatório
			relatorioEmissao.add(logo);
			
			//escreve título
			relatorioEmissao.addTitle("Relatório de Emissões dos Ativos de TI");
			
			//escreve paragrafo
			relatorioEmissao.add(new Paragraph("Lista dos ativos de TI e seus respctivos valores de Emissão de CO²:"));
			
			//percorre a lista de ativos
			for(int i = 0; i <= (listaDeAtivos.size()-1); i++){
				
				//escreve no relatório as infromações sobre cada ativo da lista
				relatorioEmissao.add(new Paragraph("Ativo: " + listaDeAtivos.get(i).getHostName()));
				relatorioEmissao.add(new Paragraph("Fabricante: " + listaDeAtivos.get(i).getFabricante()));
				relatorioEmissao.add(new Paragraph("Valor Emissão CO²: " + listaDeAtivos.get(i).getValorEmissaoCO()));
						
			}
			
		}catch(DocumentException de){
			System.out.println("Erro ao abrir documento");
		}catch(IOException io){
			System.out.println("Erro ao criar documento");
		}finally{
			relatorioEmissao.close();
		}
		return relatorioEmissao;
	}
	
	/*
	 * Método gera um relatório em PDF com as informaões dos ativos de TI e seus respectivos
	 *  cosumos de energia
	 */
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces("application/pdf")
	public Document relatorioAtivosConsumoEnergia(ArrayList<AtivoTI> listaDeAtivos){
		
		//cria o documento vazio
		Document relatorioConsumo = new Document();
		
		//busca o diretorio para salvar o PDF na maquina do usuário
		String path = System.getProperty("user.home") + System.getProperty("file.separator");
		
		try{
			//cria uma instacia do documento com nome e diretorio destino
			PdfWriter.getInstance(relatorioConsumo, new FileOutputStream(path + "\\testePDF.pdf"));
			
			//abri o docuemnto
			relatorioConsumo.open();
			
			//seta tamanho da página do relatório
			relatorioConsumo.setPageSize(PageSize.A4);
			
			//escreve cabeçalho
			relatorioConsumo.addHeader("Cabecalho", "Texto Cabeçalho");
			
			//cria imagem para o relatório
			Image logo = Image.getInstance("C:\\testes\\logo.png");
			
			//seta o tamanho da imagem
			logo.scaleToFit(150, 40);
			
			//adiciona a imagem ao relatório
			relatorioConsumo.add(logo);
			
			//escreve título
			relatorioConsumo.addTitle("Relatório de Emissões dos Ativos de TI");
			
			//escreve paragrafo
			relatorioConsumo.add(new Paragraph("Lista dos ativos de TI e seus respctivos valores de consumo de energia:"));
			
			//percorre a lista de ativos
			for(int i = 0; i <= (listaDeAtivos.size()-1); i++){
				
				//escreve no relatório as infromações sobre cada ativo da lista
				relatorioConsumo.add(new Paragraph("Ativo: " + listaDeAtivos.get(i).getHostName()));
				relatorioConsumo.add(new Paragraph("Fabricante: " + listaDeAtivos.get(i).getFabricante()));
				relatorioConsumo.add(new Paragraph("Valor Consumo Energia: " + listaDeAtivos.get(i).getConsumoEnergia()));
						
			}
			
		}catch(DocumentException de){
			System.out.println("Erro ao criar documento");
		}catch(IOException io){
			System.out.println("Erro ao abrir documento");
		}finally{
			relatorioConsumo.close();
		}
		return relatorioConsumo;
	}

	/*
	 * Método gera um relatório em PDF contendo propostas de mudanças e sugestões de melhorias 
	 * relacionadas aos ativos de uma determinado usuário/empresa a partir do conhecimento obtido
	 * na base de dados, contendo também os resultados a serem obtidos com a aplicação destas mudanças
	 */
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces("application/pdf")
	public Document relatorioPropostasResultados(){
		
		//cria o documento vazio
		Document relatorioPropostasResultados = new Document();
		
		//busca o diretorio para salvar o PDF na maquina do usuário
		String path = System.getProperty("user.home") + System.getProperty("file.separator");
		
		try{
			//cria uma instacia do documento com nome e diretorio destino
			PdfWriter.getInstance(relatorioPropostasResultados, new FileOutputStream(path + "\\PropostasResultados.pdf"));
			
			//abri o docuemnto
			relatorioPropostasResultados.open();
			
			//seta tamanho da página do relatório
			relatorioPropostasResultados.setPageSize(PageSize.A4);
			
			//escreve cabeçalho
			relatorioPropostasResultados.addHeader("Cabecalho", "Texto Cabeçalho");
			
			//cria imagem para o relatório
			Image logo = Image.getInstance("C:\\testes\\logo.png");
			
			//seta o tamanho da imagem
			logo.scaleToFit(150, 40);
			
			//adiciona a imagem ao relatório
			relatorioPropostasResultados.add(logo);
			
			//escreve título
			relatorioPropostasResultados.addTitle("Prosposta de melhoria nos ativos");
			
			//escreve paragrafo
			//relatorioPropostasResultados.add(new Paragraph("Sugestão de Ativo cujo consumo de energia está abaixo:"));
			
			//escreve no relatório as informações do ativo de menor consumo de energia
			relatorioPropostasResultados.add(new Paragraph("Abaixo as informações do ativo de menor consumo de energia em nossa base de dados: "));
			
			//recebe a consulta do ativo de menor consumo de energia
			AtivoTI_DAO consultaAtivo = new AtivoTI_DAO();
			AtivoTI menorAtivo = new AtivoTI();
			menorAtivo = consultaAtivo.retornaMinAtivo();

			//insere o fabricante no relatório
			relatorioPropostasResultados.add(new Paragraph("Fabricante: " + menorAtivo.getFabricante()));
			
			//insere o modelo no relatório
			relatorioPropostasResultados.add(new Paragraph("Modelo: " + menorAtivo.getModelo()));
			
			//insere o consumo de energia atual no relatório
			relatorioPropostasResultados.add(new Paragraph("Consumo de energia atual: " + menorAtivo.getConsumoEnergia()));
						
									
		}catch(DocumentException de){
			System.out.println("Erro ao criar documento");
		}catch(IOException io){
			System.out.println("Erro ao abrir documento");
		}finally{
			relatorioPropostasResultados.close();
		}
		return relatorioPropostasResultados;
	}

}
