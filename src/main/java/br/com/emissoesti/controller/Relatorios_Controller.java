package br.com.emissoesti.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
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
	public Document relatorioAtivos(int idUsuario) throws SQLException{ //alterar para buscra no banco pelo id_usuario
		
		ArrayList<AtivoTI> listaDeAtivos = new ArrayList<AtivoTI>();
		AtivoTI_DAO ativoDAO = new AtivoTI_DAO();
		
		//chama o método que busa os dados no banco
		listaDeAtivos = ativoDAO.listaAtivoInfGerais(idUsuario);
		
		//cria o documento vazio
		Document relatorioAtivos = new Document();
		
		//busca o diretorio para salvar o PDF na maquina do usuário
		String path = System.getProperty("user.home") + System.getProperty("file.separator");
		
		try{
			//cria uma instacia do documento com nome e diretorio destino
			PdfWriter.getInstance(relatorioAtivos, new FileOutputStream(path + "\\relatorioAtivos_user1.pdf"));
			
			//abri o docuemnto
			relatorioAtivos.open();
			
			//seta tamanho da página do relatório
			relatorioAtivos.setPageSize(PageSize.A4);
			
			//escreve cabeçalho
			relatorioAtivos.addHeader("Cabecalho", "Texto Cabeçalho");
			
			//cria imagem para o relatório
			Image logo = Image.getInstance("C:\\testes\\logo.png");
			
			//seta o tamanho da imagem
			logo.scaleToFit(150, 40);
			
			//adiciona a imagem ao relatório
			relatorioAtivos.add(logo);
			
			//escreve paragrafo
			relatorioAtivos.add(new Paragraph("Relatório de ativos de TI:"));
			
			//cria uma tabela
			PdfPTable table = new PdfPTable(7);
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
	        		cell = new PdfPCell(new Phrase("Dias de consumo", f));
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
			            cell = new PdfPCell(new Phrase("" + listaDeAtivos.get(j).getConsumoEnergia(), f2));//colocar a unidade
			            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			            table.addCell(cell);
			            cell = new PdfPCell(new Phrase("" + listaDeAtivos.get(j).getHorasConsumoDiario(), f2)); //colocar a unidade
			            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			            table.addCell(cell);
			            cell = new PdfPCell(new Phrase("" + listaDeAtivos.get(j).getDiasConsumo(), f2)); //colocar a unidade
			            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			            table.addCell(cell);
           
		        	}
	        		//adiciona a tabela ao documento pdf
	        		relatorioAtivos.add(table);
	        	}
	        	
		}catch(DocumentException de){
			System.out.println("Erro ao abrir documento");
		}catch(IOException io){
			System.out.println("Erro ao criar documento");
		}finally{
			relatorioAtivos.close();
		}
		return relatorioAtivos;
	}
	
	/*
	 * Método gera um relatório em PDF com as informaões referente a emissão de CO² dos ativos de TI
	 */
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces("application/pdf")
	public Document relatorioEmissaoAtivos(int idUsuario) throws SQLException{ //alterar para buscra no banco pelo id_usuario
		
		ArrayList<AtivoTI> listaDeAtivos = new ArrayList<AtivoTI>();
		AtivoTI_DAO ativoDAO = new AtivoTI_DAO();
		
		//chama o método que busa os dados no banco
		listaDeAtivos = ativoDAO.listaAtivoInfEmissao(idUsuario);
		
		//cria o documento vazio
		Document relatorioEmissao = new Document();
		
		//busca o diretorio para salvar o PDF na maquina do usuário
		String path = System.getProperty("user.home") + System.getProperty("file.separator");
		
		try{
			//cria uma instacia do documento com nome e diretorio destino
			PdfWriter.getInstance(relatorioEmissao, new FileOutputStream(path + "\\relatorioEmissao_user1.pdf"));
			
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
			relatorioEmissao.add(new Paragraph("Relatório de emissões de C02 dos ativos:"));
			
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
	        		cell = new PdfPCell(new Phrase("Emissão CO² diária", f));
	        		cell.setBackgroundColor(GrayColor.GRAY);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        		table.addCell(cell);
	        		cell = new PdfPCell(new Phrase("Emissão CO² semanal", f));
	        		cell.setBackgroundColor(GrayColor.GRAY);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        		table.addCell(cell);
	        		cell = new PdfPCell(new Phrase("Emissão CO² mensal", f));
	        		cell.setBackgroundColor(GrayColor.GRAY);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        		table.addCell(cell);
	        		cell = new PdfPCell(new Phrase("Emissão CO² anual", f));
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
			            cell = new PdfPCell(new Phrase("" + listaDeAtivos.get(j).getValorEmissaoCODiario(), f2));//colocar a unidade
			            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			            table.addCell(cell);
			            cell = new PdfPCell(new Phrase("" + listaDeAtivos.get(j).getValorEmissaoCOSemanal(), f2)); //colocar a unidade
			            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			            table.addCell(cell);
			            cell = new PdfPCell(new Phrase("" + listaDeAtivos.get(j).getValorEmissaoCOMensal(), f2)); //colocar a unidade
			            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			            table.addCell(cell);
			            cell = new PdfPCell(new Phrase("" + listaDeAtivos.get(j).getValorEmissaoCOAnual(), f2)); //colocar a unidade
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
	 * Método gera um relatório em PDF com as informaões referente ao consumo de energia dos ativos de TI
	 */
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces("application/pdf")
	public Document relatorioConsumoAtivos(int idUsuario) throws SQLException{ //alterar para buscra no banco pelo id_usuario
		
		ArrayList<AtivoTI> listaDeAtivos = new ArrayList<AtivoTI>();
		AtivoTI_DAO ativoDAO = new AtivoTI_DAO();
		
		//chama o método que busa os dados no banco
		listaDeAtivos = ativoDAO.listaAtivoInfConsumo(idUsuario);
		
		//cria o documento vazio
		Document relatorioConsumo = new Document();
		
		//busca o diretorio para salvar o PDF na maquina do usuário
		String path = System.getProperty("user.home") + System.getProperty("file.separator");
		
		try{
			//cria uma instacia do documento com nome e diretorio destino
			PdfWriter.getInstance(relatorioConsumo, new FileOutputStream(path + "\\relatorioConsumo_user1.pdf"));
			
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
			
			//escreve paragrafo
			relatorioConsumo.add(new Paragraph("Relatório de consumo de energia dos ativos:"));
			
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
	        		cell = new PdfPCell(new Phrase("Consumo energia diário", f));
	        		cell.setBackgroundColor(GrayColor.GRAY);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        		table.addCell(cell);
	        		cell = new PdfPCell(new Phrase("Consumo energia semanal", f));
	        		cell.setBackgroundColor(GrayColor.GRAY);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        		table.addCell(cell);
	        		cell = new PdfPCell(new Phrase("Consumo energia mensal", f));
	        		cell.setBackgroundColor(GrayColor.GRAY);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        		table.addCell(cell);
	        		cell = new PdfPCell(new Phrase("Consumo energia anual", f));
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
			            cell = new PdfPCell(new Phrase("" + listaDeAtivos.get(j).getConsumoEnergiaDiario(), f2));//colocar a unidade
			            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			            table.addCell(cell);
			            cell = new PdfPCell(new Phrase("" + listaDeAtivos.get(j).getConsumoEnergiaSemanal(), f2)); //colocar a unidade
			            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			            table.addCell(cell);
			            cell = new PdfPCell(new Phrase("" + listaDeAtivos.get(j).getConsumoEnergiaMensal(), f2)); //colocar a unidade
			            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			            table.addCell(cell);
			            cell = new PdfPCell(new Phrase("" + listaDeAtivos.get(j).getConsumoEnergiaAnual(), f2)); //colocar a unidade
			            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			            table.addCell(cell);
           
		        	}
	        		//adiciona a tabela ao documento pdf
	        		relatorioConsumo.add(table);
	        	}
	        	
		}catch(DocumentException de){
			System.out.println("Erro ao abrir documento");
		}catch(IOException io){
			System.out.println("Erro ao criar documento");
		}finally{
			relatorioConsumo.close();
		}
		return relatorioConsumo;
	}
	
	/*
	 * Método gera um relatório em PDF com as informaões referente ao consumo de energia e emissão de CO² dos ativos de TI
	 */
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces("application/pdf")
	public Document relatorioEmissaoCOConsumoEnergiaAtivos(ArrayList<AtivoTI> listaDeAtivos){ //alterar para buscra no banco pelo id_usuario
		
		//cria o documento vazio
		Document relatorioEmissaoCOConsumoEnergia = new Document();
		
		//busca o diretorio para salvar o PDF na maquina do usuário
		String path = System.getProperty("user.home") + System.getProperty("file.separator");
		
		try{
			//cria uma instacia do documento com nome e diretorio destino
			PdfWriter.getInstance(relatorioEmissaoCOConsumoEnergia, new FileOutputStream(path + "\\testePDFtable1.pdf"));
			
			//abri o docuemnto
			relatorioEmissaoCOConsumoEnergia.open();
			
			//seta tamanho da página do relatório
			relatorioEmissaoCOConsumoEnergia.setPageSize(PageSize.A4);
			
			//escreve cabeçalho
			relatorioEmissaoCOConsumoEnergia.addHeader("Cabecalho", "Texto Cabeçalho");
			
			//cria imagem para o relatório
			Image logo = Image.getInstance("C:\\testes\\logo.png");
			
			//seta o tamanho da imagem
			logo.scaleToFit(150, 40);
			
			//adiciona a imagem ao relatório
			relatorioEmissaoCOConsumoEnergia.add(logo);
			
			//escreve paragrafo
			relatorioEmissaoCOConsumoEnergia.add(new Paragraph("Relatório de ativos de TI:"));
			
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
	        		cell = new PdfPCell(new Phrase("Consumo energia diário", f));
	        		cell.setBackgroundColor(GrayColor.GRAY);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        		table.addCell(cell);
	        		cell = new PdfPCell(new Phrase("Emissão CO² diária", f));
	        		cell.setBackgroundColor(GrayColor.GRAY);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        		table.addCell(cell);
	        		cell = new PdfPCell(new Phrase("Consumo energia semanal", f));
	        		cell.setBackgroundColor(GrayColor.GRAY);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        		table.addCell(cell);
	        		cell = new PdfPCell(new Phrase("Emissão CO² semanal", f));
	        		cell.setBackgroundColor(GrayColor.GRAY);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        		table.addCell(cell);
	        		cell = new PdfPCell(new Phrase("Consumo energia mensal", f));
	        		cell.setBackgroundColor(GrayColor.GRAY);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        		table.addCell(cell);
	        		cell = new PdfPCell(new Phrase("Emissão CO² mensal", f));
	        		cell.setBackgroundColor(GrayColor.GRAY);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        		table.addCell(cell);
	        		cell = new PdfPCell(new Phrase("Consumo energia anual", f));
	        		cell.setBackgroundColor(GrayColor.GRAY);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        		table.addCell(cell);        		
	        		cell = new PdfPCell(new Phrase("Emissão CO² anual", f));
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
			            cell = new PdfPCell(new Phrase("" + listaDeAtivos.get(j).getConsumoEnergiaDiario(), f2));//colocar a unidade
			            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			            table.addCell(cell);
			            cell = new PdfPCell(new Phrase("" + listaDeAtivos.get(j).getValorEmissaoCODiario(), f2));//colocar a unidade
			            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			            table.addCell(cell);
			            cell = new PdfPCell(new Phrase("" + listaDeAtivos.get(j).getConsumoEnergiaSemanal(), f2)); //colocar a unidade
			            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			            table.addCell(cell);
			            cell = new PdfPCell(new Phrase("" + listaDeAtivos.get(j).getValorEmissaoCOSemanal(), f2)); //colocar a unidade
			            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			            table.addCell(cell);
			            cell = new PdfPCell(new Phrase("" + listaDeAtivos.get(j).getConsumoEnergiaMensal(), f2)); //colocar a unidade
			            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			            table.addCell(cell);
			            cell = new PdfPCell(new Phrase("" + listaDeAtivos.get(j).getValorEmissaoCOMensal(), f2)); //colocar a unidade
			            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			            table.addCell(cell);
			            cell = new PdfPCell(new Phrase("" + listaDeAtivos.get(j).getConsumoEnergiaAnual(), f2)); //colocar a unidade
			            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			            table.addCell(cell);
			            cell = new PdfPCell(new Phrase("" + listaDeAtivos.get(j).getValorEmissaoCOAnual(), f2)); //colocar a unidade
			            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			            table.addCell(cell);
           
		        	}
	        		//adiciona a tabela ao documento pdf
	        		relatorioEmissaoCOConsumoEnergia.add(table);
	        	}
	        	
		}catch(DocumentException de){
			System.out.println("Erro ao abrir documento");
		}catch(IOException io){
			System.out.println("Erro ao criar documento");
		}finally{
			relatorioEmissaoCOConsumoEnergia.close();
		}
		return relatorioEmissaoCOConsumoEnergia;
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
