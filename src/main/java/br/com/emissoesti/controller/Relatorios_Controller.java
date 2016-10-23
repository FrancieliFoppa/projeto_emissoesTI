package br.com.emissoesti.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import br.com.emissoesti.model.AtivoTI;
import br.com.emissoesti.model.Relatorios;

@Controller
public class Relatorios_Controller extends Relatorios{

	public Relatorios_Controller() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Método gera um relatório em PDF com as informaões dos ativos de TI e suas respectivas
	 *  quantidades de emissão CO²
	 */
	public Document relatorioAtivosEmissao(ArrayList<AtivoTI> listaDeAtivos){
		
		//cria o documento vazio
		Document relatorioEmissao = new Document();
		
		try{
			//cria uma instacia do documento com nome e diretorio destino
			PdfWriter.getInstance(relatorioEmissao, new FileOutputStream("C:\\testes\\relatorioEmissaoC02IMG2.pdf"));
			
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
	public Document relatorioAtivosConsumoEnergia(ArrayList<AtivoTI> listaDeAtivos){
		
		//cria o documento vazio
		Document relatorioConsumo = new Document();
		
		try{
			//cria uma instacia do documento com nome e diretorio destino
			PdfWriter.getInstance(relatorioConsumo, new FileOutputStream("C:\\testes\\relatorioConsumoEnergiaIMG2.pdf"));
			
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
	public Document relatorioPropostasResultados(ArrayList<AtivoTI> listaDeAtivos){
		
		
		return null;
		
	}

}
