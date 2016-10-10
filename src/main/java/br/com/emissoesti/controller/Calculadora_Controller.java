package br.com.emissoesti.controller;

import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import br.com.emissoesti.model.AtivoTI;
import br.com.emissoesti.model.Calculadora;

@Controller
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
	public ArrayList<AtivoTI> calculaEmissao(double fatorEmissaoCO, ArrayList<AtivoTI> listaDeAtivos){
			
		double resultadoEmissao = 0.0;
		for(int i = 0; i <= (listaDeAtivos.size()+1); i++){
			resultadoEmissao = resultadoEmissao + (listaDeAtivos.get(i).getConsumoEnergia() * fatorEmissaoCO);
			listaDeAtivos.get(i).setValorEmissaoCO(resultadoEmissao);
		}
				
		return listaDeAtivos;
	}
		
	/*
	 * método que calcula o custo de utilização de enegia pelos hardwares, apartir do consumo informado,
	 *medio ou exato, multiplicado pela taxa de energia da cidade onde os haerdwares estão alocados. 
	 */
	public ArrayList<AtivoTI> calculaCustoEnergia(double taxaEnergia, ArrayList<AtivoTI> listaDeAtivos){
		
		double resultadoCustoEnergia = 0.0;
		for(int i = 0; i <= listaDeAtivos.size(); i++){
			resultadoCustoEnergia = resultadoCustoEnergia + (listaDeAtivos.get(i).getConsumoEnergia() * taxaEnergia);
			listaDeAtivos.get(i).setConsumoEnergia(resultadoCustoEnergia);
		}
		
		return listaDeAtivos;
	}
		
}
