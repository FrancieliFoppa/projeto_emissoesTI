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
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
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
	 * Método gera um relatório em PDF com as informaões dos ativos de TI
	 */
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces("application/pdf")
	public Document relatorioAtivos(ArrayList<AtivoTI> listaDeAtivos){ //alterar para buscra no banco pelo id_usuario
		
		//cria o documento vazio
		Document relatorioEmissao = new Document();
		
		//busca o diretorio para salvar o PDF na maquina do usuário
		String path = System.getProperty("user.home") + System.getProperty("file.separator");
		
		try{
			//cria uma instacia do documento com nome e diretorio destino
			PdfWriter.getInstance(relatorioEmissao, new FileOutputStream(path + "\\testePDFtable1.pdf"));
			
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
			
			//escreve paragrafo
			relatorioEmissao.add(new Paragraph("Relatório de ativos de TI:"));
			
			//cria uma tabela
			PdfPTable table = new PdfPTable(8);
			table.setSpacingBefore(25);
			//table.setSpacingAfter(15);
				
			PdfPCell cell;
				//insere o cabeçalho
	        	for(int i = 0; i < 1; i++){
	        		
	        		Font f = new Font(FontFamily.HELVETICA, 8, Font.BOLD, GrayColor.GRAYWHITE);
	        		
	        		cell = new PdfPCell(new Phrase("Hostname", f));
	        		cell.setBackgroundColor(GrayColor.GRAY);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        		table.addCell(cell);
	        		cell = new PdfPCell(new Phrase("Fabricante", f));
	        		cell.setBackgroundColor(GrayColor.GRAY);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        		table.addCell(cell);
	        		cell = new PdfPCell(new Phrase("Modelo", f));
	        		cell.setBackgroundColor(GrayColor.GRAY);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        		table.addCell(cell);
	        		cell = new PdfPCell(new Phrase("Categoria", f));
	        		cell.setBackgroundColor(GrayColor.GRAY);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        		table.addCell(cell);
	        		cell = new PdfPCell(new Phrase("Consumo de energia", f));
	        		cell.setBackgroundColor(GrayColor.GRAY);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        		table.addCell(cell);
	        		cell = new PdfPCell(new Phrase("Horas de consumo", f));
	        		cell.setBackgroundColor(GrayColor.GRAY);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        		table.addCell(cell);
	        		cell = new PdfPCell(new Phrase("Tipo de consumo", f));
	        		cell.setBackgroundColor(GrayColor.GRAY);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        		table.addCell(cell);
	        		cell = new PdfPCell(new Phrase("Emissão de CO²", f));
	        		cell.setBackgroundColor(GrayColor.GRAY);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        		table.addCell(cell);
	        		
	        		//insere os dados da lista de ativos
	        		for(int j = 0; j<= (listaDeAtivos.size()-1); j++){
			        	
	        			Font f2 = new Font(FontFamily.COURIER, 8, Font.NORMAL, GrayColor.GRAYBLACK);
	        			
			            cell = new PdfPCell(new Phrase(listaDeAtivos.get(j).getHostName(), f2));
			            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			            table.addCell(cell);
			            cell = new PdfPCell(new Phrase(listaDeAtivos.get(j).getFabricante(), f2));
			            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			            table.addCell(cell);
			            cell = new PdfPCell(new Phrase(listaDeAtivos.get(j).getModelo(), f2));
			            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			            table.addCell(cell);
			            cell = new PdfPCell(new Phrase(listaDeAtivos.get(j).getCategoria(), f2));
			            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			            table.addCell(cell);
			            cell = new PdfPCell(new Phrase("" + listaDeAtivos.get(j).getConsumoEnergia(), f2));
			            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			            table.addCell(cell);
			            cell = new PdfPCell(new Phrase("" + listaDeAtivos.get(j).getHorasConsumo(), f2));
			            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			            table.addCell(cell);
			            cell = new PdfPCell(new Phrase(listaDeAtivos.get(j).getTipoConsumo(), f2));
			            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			            table.addCell(cell);
			            cell = new PdfPCell(new Phrase("" + listaDeAtivos.get(j).getValorEmissaoCO(), f2));
			            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			            table.addCell(cell);
           
		        	}
	        		//adiciona a tabela ao documento pdf
	        		relatorioEmissao.add(table);
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
