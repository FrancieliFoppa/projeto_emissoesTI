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
	
	public Document relatorioAtivosEmissao(ArrayList<AtivoTI> listaDeAtivos){
		
		//cria o documento vazio
		Document relatorioEmissao = new Document();
		
		try{
			//cria uma instacia do documento com nome e diretorio destino
			PdfWriter.getInstance(relatorioEmissao, new FileOutputStream("C:\\testes\\relatorioEmissaoC02.pdf"));
			
			//abri o docuemnto
			relatorioEmissao.open();
			
			//seta tamanho da p�gina do relat�rio
			relatorioEmissao.setPageSize(PageSize.A4);
			
			//escreve cabe�alho
			relatorioEmissao.addHeader("", "");
			
			//escreve t�tulo
			relatorioEmissao.addTitle("Relat�rio de Emiss�es dos Ativos de TI");
			
			//escreve paragrafo
			relatorioEmissao.add(new Paragraph("Lista dos ativos de TI e seus respctivos valores de Emiss�o de CO�:"));
			
			//percorre a lista de ativos
			for(int i = 0; i <= (listaDeAtivos.size()+1); i++){
				
				//escreve no relat�rio as infroma��es sobre cada ativo da lista
				relatorioEmissao.add(new Paragraph("Ativo: " + listaDeAtivos.get(i).getHostName()));
				relatorioEmissao.add(new Paragraph("Fabricante: " + listaDeAtivos.get(i).getFabricante()));
				relatorioEmissao.add(new Paragraph("Valor Emiss�o CO�: " + listaDeAtivos.get(i).getValorEmissaoCO()));
						
			}
			
		}catch(DocumentException de){
			System.out.println("Erro ao criar documento");
		}catch(IOException io){
			System.out.println("Erro ao abrir documento");
		}
		return relatorioEmissao;
	}
	
public Document relatorioAtivosConsumoEnergia(ArrayList<AtivoTI> listaDeAtivos){
		
		//cria o documento vazio
		Document relatorioEmissao = new Document();
		
		try{
			//cria uma instacia do documento com nome e diretorio destino
			PdfWriter.getInstance(relatorioEmissao, new FileOutputStream("C:\\testes\\relatorioConsumoEnergia.pdf"));
			
			//abri o docuemnto
			relatorioEmissao.open();
			
			//seta tamanho da p�gina do relat�rio
			relatorioEmissao.setPageSize(PageSize.A4);
			
			//escreve cabe�alho
			relatorioEmissao.addHeader("Cabecalho", "Texto Cabe�alho");
			
			//escreve t�tulo
			relatorioEmissao.addTitle("Relat�rio de Emiss�es dos Ativos de TI");
			
			//escreve paragrafo
			relatorioEmissao.add(new Paragraph("Lista dos ativos de TI e seus respctivos valores de consumo de energia:"));
			
			//percorre a lista de ativos
			for(int i = 0; i <= listaDeAtivos.size(); i++){
				
				//escreve no relat�rio as infroma��es sobre cada ativo da lista
				relatorioEmissao.add(new Paragraph("Ativo: " + listaDeAtivos.get(i).getHostName()));
				relatorioEmissao.add(new Paragraph("Fabricante: " + listaDeAtivos.get(i).getFabricante()));
				relatorioEmissao.add(new Paragraph("Valor Consumo Energia: " + listaDeAtivos.get(i).getConsumoEnergia()));
						
			}
			
		}catch(DocumentException de){
			System.out.println("Erro ao criar documento");
		}catch(IOException io){
			System.out.println("Erro ao abrir documento");
		}
		return relatorioEmissao;
	}

}
