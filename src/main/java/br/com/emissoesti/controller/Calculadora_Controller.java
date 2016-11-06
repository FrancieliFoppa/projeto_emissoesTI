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
		super(taxaEnergia, listaDeAtivos);
		// TODO Auto-generated constructor stub
	}

	/*
	 * método que calcula a quantidade de CO² emitida, atravéz da quatidade de hardware, o valor de emissão
	 *por hardware, que pode ser médio ou exato, multiplicado pelo fator de emissão de CO².
	 */
	@POST	
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public ArrayList<AtivoTI> calculaEmissao(double fatorEmissaoCO, ArrayList<AtivoTI> listaDeAtivos){
			
		double resultadoEmissao = 0.0;
		for(int i = 0; i <= (listaDeAtivos.size()-1); i++){
			resultadoEmissao = listaDeAtivos.get(i).getConsumoEnergia() * fatorEmissaoCO;
			listaDeAtivos.get(i).setValorEmissaoCO(resultadoEmissao);
			System.out.println("Enissão: " + listaDeAtivos.get(i).getValorEmissaoCO());
		}
				
		return listaDeAtivos;
	}
	
	public ArrayList<AtivoTI> calculaEmissao(double fatorEmissaoCO, int idUsuario) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		
		ArrayList<AtivoTI> listaDeAtivos = new ArrayList<AtivoTI>();
		AtivoTI_DAO ativoDAO = new AtivoTI_DAO();
		
		listaDeAtivos = ativoDAO.listaAtivo(idUsuario);
		
		double resultadoEmissao = 0.0;
		for(int i = 0; i <= (listaDeAtivos.size()-1); i++){
			resultadoEmissao = listaDeAtivos.get(i).getConsumoEnergia() * fatorEmissaoCO;
			listaDeAtivos.get(i).setValorEmissaoCO(resultadoEmissao);
			System.out.println(listaDeAtivos.get(i).getIdAtivo() + " Emissão: " + listaDeAtivos.get(i).getValorEmissaoCO());
		}
				
		ativoDAO.atualizaEmissao(listaDeAtivos, idUsuario);
		return listaDeAtivos;
		
	}
		
	/*
	 * método que calcula o custo de utilização de enegia pelos hardwares, apartir do consumo informado,
	 *medio ou exato, multiplicado pela taxa de energia da cidade onde os haerdwares estão alocados. 
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
