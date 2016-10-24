package br.com.emissoesti.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.emissoesti.DAO.AtivoTI_DAO;
import br.com.emissoesti.model.AtivoTI;
import br.com.emissoesti.model.Relatorios;

@Controller
public class Relatorios_Controller extends Relatorios{

	public Relatorios_Controller() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * M�todo gera um relat�rio em PDF com as informa�es dos ativos de TI e suas respectivas
	 *  quantidades de emiss�o CO�
	 */
	public Document relatorioAtivosEmissao(ArrayList<AtivoTI> listaDeAtivos){
		
		//cria o documento vazio
		Document relatorioEmissao = new Document();
		
		try{
			//cria uma instacia do documento com nome e diretorio destino
			PdfWriter.getInstance(relatorioEmissao, new FileOutputStream("C:\\testes\\relatorioEmissaoC02IMG2.pdf"));
			
			//abri o docuemnto
			relatorioEmissao.open();
			
			//seta tamanho da p�gina do relat�rio
			relatorioEmissao.setPageSize(PageSize.A4);
			
			//escreve cabe�alho
			relatorioEmissao.addHeader("Cabecalho", "Texto Cabe�alho");
			
			//cria imagem para o relat�rio
			Image logo = Image.getInstance("C:\\testes\\logo.png");
			
			//seta o tamanho da imagem
			logo.scaleToFit(150, 40);
			
			//adiciona a imagem ao relat�rio
			relatorioEmissao.add(logo);
			
			//escreve t�tulo
			relatorioEmissao.addTitle("Relat�rio de Emiss�es dos Ativos de TI");
			
			//escreve paragrafo
			relatorioEmissao.add(new Paragraph("Lista dos ativos de TI e seus respctivos valores de Emiss�o de CO�:"));
			
			//percorre a lista de ativos
			for(int i = 0; i <= (listaDeAtivos.size()-1); i++){
				
				//escreve no relat�rio as infroma��es sobre cada ativo da lista
				relatorioEmissao.add(new Paragraph("Ativo: " + listaDeAtivos.get(i).getHostName()));
				relatorioEmissao.add(new Paragraph("Fabricante: " + listaDeAtivos.get(i).getFabricante()));
				relatorioEmissao.add(new Paragraph("Valor Emiss�o CO�: " + listaDeAtivos.get(i).getValorEmissaoCO()));
						
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
	 * M�todo gera um relat�rio em PDF com as informa�es dos ativos de TI e seus respectivos
	 *  cosumos de energia
	 */
	public Document relatorioAtivosConsumoEnergia(ArrayList<AtivoTI> listaDeAtivos){
		
		//cria o documento vazio
		Document relatorioConsumo = new Document();
		
		try{
			//cria uma instacia do documento com nome e diretorio destino
			PdfWriter.getInstance(relatorioConsumo, new FileOutputStream("C:\\testes\\relatorioConsumoEnergiaIMG2.pdf"));
			
			//abri o docuemnto
			relatorioConsumo.open();
			
			//seta tamanho da p�gina do relat�rio
			relatorioConsumo.setPageSize(PageSize.A4);
			
			//escreve cabe�alho
			relatorioConsumo.addHeader("Cabecalho", "Texto Cabe�alho");
			
			//cria imagem para o relat�rio
			Image logo = Image.getInstance("C:\\testes\\logo.png");
			
			//seta o tamanho da imagem
			logo.scaleToFit(150, 40);
			
			//adiciona a imagem ao relat�rio
			relatorioConsumo.add(logo);
			
			//escreve t�tulo
			relatorioConsumo.addTitle("Relat�rio de Emiss�es dos Ativos de TI");
			
			//escreve paragrafo
			relatorioConsumo.add(new Paragraph("Lista dos ativos de TI e seus respctivos valores de consumo de energia:"));
			
			//percorre a lista de ativos
			for(int i = 0; i <= (listaDeAtivos.size()-1); i++){
				
				//escreve no relat�rio as infroma��es sobre cada ativo da lista
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
	 * M�todo gera um relat�rio em PDF contendo propostas de mudan�as e sugest�es de melhorias 
	 * relacionadas aos ativos de uma determinado usu�rio/empresa a partir do conhecimento obtido
	 * na base de dados, contendo tamb�m os resultados a serem obtidos com a aplica��o destas mudan�as
	 */
	public Document relatorioPropostasResultados(){
		
		//cria o documento vazio
		Document relatorioPropostasResultados = new Document();
		AtivoTI_DAO consultaAtivo = new AtivoTI_DAO(null);
		
		try{
			//cria uma instacia do documento com nome e diretorio destino
			PdfWriter.getInstance(relatorioPropostasResultados, new FileOutputStream("C:\\testes\\relatorioPropostasResultados.pdf"));
			
			//abri o docuemnto
			relatorioPropostasResultados.open();
			
			//seta tamanho da p�gina do relat�rio
			relatorioPropostasResultados.setPageSize(PageSize.A4);
			
			//escreve cabe�alho
			relatorioPropostasResultados.addHeader("Cabecalho", "Texto Cabe�alho");
			
			//cria imagem para o relat�rio
			Image logo = Image.getInstance("C:\\testes\\logo.png");
			
			//seta o tamanho da imagem
			logo.scaleToFit(150, 40);
			
			//adiciona a imagem ao relat�rio
			relatorioPropostasResultados.add(logo);
			
			//escreve t�tulo
			relatorioPropostasResultados.addTitle("Prosposta de melhoria no ambiente de ativos");
			
			//escreve paragrafo
			relatorioPropostasResultados.add(new Paragraph("Sugest�o de Ativo cujo consumo de energia est� abaixo:"));
				
			//escreve no relat�rio as infroma��es sobre cada ativo da lista
			relatorioPropostasResultados.add((Element) consultaAtivo.retornaMinAtivo());	
						
			
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
