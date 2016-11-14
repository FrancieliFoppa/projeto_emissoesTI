package br.com.emissoesti.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import br.com.emissoesti.DAO.AtivoTI_DAO;
import br.com.emissoesti.model.AtivoTI;
import br.com.emissoesti.model.Calculadora;

@Path("/calc")
public class Calculadora_Controller extends Calculadora{
	
	public Calculadora_Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Calculadora_Controller(double taxaEnergia, ArrayList<AtivoTI> listaDeAtivos) {
		//super(taxaEnergia, listaDeAtivos);
	}
	
	/*
	 * método calcula a quantidade de CO² emitida atavés do consumo de energia por hardware
	 * multiplicado pelo fator de emissão de CO².
	 */
	@POST	
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public ArrayList<AtivoTI> calculaEmissao(double fatorEmissaoCO, int idUsuario) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		
		ArrayList<AtivoTI> listaDeAtivos = new ArrayList<AtivoTI>();
		AtivoTI_DAO ativoDAO = new AtivoTI_DAO();
		
		listaDeAtivos = ativoDAO.listaAtivoInfConsumo(idUsuario);

		double resultadoEmissao = 0.0;
		
		//percorre a lista de ativos
		for(int i = 0; i <= (listaDeAtivos.size()-1); i++){
		
			//multiplica pelo consumo diario
			resultadoEmissao = (listaDeAtivos.get(i).getConsumoEnergiaDiario() * fatorEmissaoCO);
			listaDeAtivos.get(i).setValorEmissaoCODiario(resultadoEmissao);
			System.out.println(listaDeAtivos.get(i).getIdAtivo() + " Emissão diaria: " + listaDeAtivos.get(i).getValorEmissaoCODiario());
			
			//multiplica pelo consumo semanal
			resultadoEmissao = (listaDeAtivos.get(i).getConsumoEnergiaSemanal() * fatorEmissaoCO);
			listaDeAtivos.get(i).setValorEmissaoCOSemanal(resultadoEmissao);
			System.out.println(listaDeAtivos.get(i).getIdAtivo() + " Emissão semanal: " + listaDeAtivos.get(i).getValorEmissaoCOSemanal());
			
			//multiplica pelo consumo mensal
			resultadoEmissao = (listaDeAtivos.get(i).getConsumoEnergiaMensal() * fatorEmissaoCO);
			listaDeAtivos.get(i).setValorEmissaoCOMensal(resultadoEmissao);
			System.out.println(listaDeAtivos.get(i).getIdAtivo() + " Emissão mensal: " + listaDeAtivos.get(i).getValorEmissaoCOMensal());
			
			//multiplica pelo consumo anual
			resultadoEmissao = (listaDeAtivos.get(i).getConsumoEnergiaAnual() * fatorEmissaoCO);
			listaDeAtivos.get(i).setValorEmissaoCOAnual(resultadoEmissao);
			System.out.println(listaDeAtivos.get(i).getIdAtivo() + " Emissão anual: " + listaDeAtivos.get(i).getValorEmissaoCOAnual());
			
		}
				
		ativoDAO.atualizaEmissao(listaDeAtivos, idUsuario);
		return listaDeAtivos;
		
	}
		
	/*
	 * método calcula o consumo de energia através do consumo por hora do hardware
	 * multiplicado pela quantidade de dias que permance ligado e pelo consumo de Watts de energia
	 */
	@POST	
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public ArrayList<AtivoTI> calculaConsumoEnergia(int idUsuario) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		
		ArrayList<AtivoTI> listaDeAtivos = new ArrayList<AtivoTI>();
		AtivoTI_DAO ativoDAO = new AtivoTI_DAO();
		
		listaDeAtivos = ativoDAO.listaAtivoInfConsumo(idUsuario);
		
		double consumoMegaWatt = 0.0;
		double resultadoConsumo = 0.0;
		//percorre a lista de ativos
		for(int i = 0; i <= (listaDeAtivos.size()-1); i++){
			
			//converte o consumo de energia de Watts para MegaWatt
			consumoMegaWatt = (listaDeAtivos.get(i).getConsumoEnergia() / 1000000); 
			System.out.println(consumoMegaWatt);
			//multiplica pelo consumo diario
			resultadoConsumo = (consumoMegaWatt * listaDeAtivos.get(i).getHorasConsumoDiario());
			listaDeAtivos.get(i).setConsumoEnergiaDiario(resultadoConsumo);
			System.out.println(listaDeAtivos.get(i).getIdAtivo() + " Consumo diario: " + listaDeAtivos.get(i).getConsumoEnergiaDiario());
			
			//multiplica pelo consumo semanal
			double HrsconsumoSemanal = 0.0;
			HrsconsumoSemanal = (listaDeAtivos.get(i).getHorasConsumoDiario() * listaDeAtivos.get(i).getDiasConsumo());
			resultadoConsumo = (consumoMegaWatt * HrsconsumoSemanal);
			listaDeAtivos.get(i).setConsumoEnergiaSemanal(resultadoConsumo);
			System.out.println(listaDeAtivos.get(i).getIdAtivo() + " Consumo semanal: " + listaDeAtivos.get(i).getConsumoEnergiaSemanal());
			
			//multiplica pelo consumo mensal
			double HrsconsumoMensal = 0.0;
			HrsconsumoMensal = (HrsconsumoSemanal * 4);
			resultadoConsumo = (consumoMegaWatt * HrsconsumoMensal);
			listaDeAtivos.get(i).setConsumoEnergiaMensal(resultadoConsumo);
			System.out.println(listaDeAtivos.get(i).getIdAtivo() + " Consumo mensal: " + listaDeAtivos.get(i).getConsumoEnergiaMensal());
			
			//multiplica pelo consumo anual
			double HrsconsumoAnual = 0.0;
			HrsconsumoAnual = (HrsconsumoMensal * 12);
			resultadoConsumo = (consumoMegaWatt * HrsconsumoAnual);
			listaDeAtivos.get(i).setConsumoEnergiaAnual(resultadoConsumo);
			System.out.println(listaDeAtivos.get(i).getIdAtivo() + " Consumo anual: " + listaDeAtivos.get(i).getConsumoEnergiaAnual());
			
		}
				
		ativoDAO.atualizaConsumo(listaDeAtivos, idUsuario);
		return listaDeAtivos;
		
	}
	
		
	/*
	 * método que calcula o custo de utilização de enegia pelos hardwares, apartir do consumo informado,
	 * medio ou exato, multiplicado pela taxa de energia da cidade onde os haerdwares estão alocados. 
	 */
	@POST	
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public ArrayList<AtivoTI> calculaCustoEnergia(double taxaEnergia, ArrayList<AtivoTI> listaDeAtivos){
		
		double resultadoCustoEnergia = 0.0;
		for(int i = 0; i <= (listaDeAtivos.size()-1); i++){
			resultadoCustoEnergia = resultadoCustoEnergia + (listaDeAtivos.get(i).getConsumoEnergia() * taxaEnergia);
			listaDeAtivos.get(i).setConsumoEnergia(resultadoCustoEnergia);
		}
		
		return listaDeAtivos;
	}
		
}
