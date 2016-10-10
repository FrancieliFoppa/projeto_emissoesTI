package br.com.emissoesti.model;

import java.util.ArrayList;

public class Calculadora {
	
	private int qtdeAtivos = 0;
	private static double fatorEmissaoCO = 0.5317;
	private double taxaEnergia;
	private ArrayList<AtivoTI> listaDeAtivos;

	public Calculadora(){}
	
	public Calculadora(int qtdeAtivos, double taxaEnergia, ArrayList<AtivoTI> listaDeAtivos ) {
		
		this.qtdeAtivos = qtdeAtivos;
		this.taxaEnergia = taxaEnergia;
		this.listaDeAtivos = new ArrayList<AtivoTI>();

	}
	
	/*
	 * m�todo que calcula a quantidade de CO� emitida, atrav�z da quatidade de hardware, o valor de emiss�o
	 *por hardware, que pode ser m�dio ou exato, multiplicado pelo fator de emiss�o de CO�.
	 */
	public double calculaEmissao(double valorEmissaoCO, double fatorEmissaoCO, ArrayList<AtivoTI> listaDeAtivos){
		
		double resultadoEmissao = 0.0;
		for(int i = 0; i <= listaDeAtivos.size(); i++){
			resultadoEmissao = resultadoEmissao + (listaDeAtivos.get(i).getValorEmissaoCO() * fatorEmissaoCO);
		}
				
		return resultadoEmissao;
	}
	
	/*
	 * m�todo que calcula o custo de utiliza��o de enegia pelos hardwares, apartir do consumo informado,
	 *medio ou exato, multiplicado pela taxa de energia da cidade onde os haerdwares est�o alocados. 
	 */
	public double calculaCustoEnergia(double consumoEnergia, double taxaEnergia, ArrayList<AtivoTI> listaDeAtivos){

		double resultadoCustoEnergia = 0.0;
		for(int i = 0; i <= listaDeAtivos.size(); i++){
			resultadoCustoEnergia = resultadoCustoEnergia + (listaDeAtivos.get(i).getConsumoEnergia() * taxaEnergia);
		}
		
		return resultadoCustoEnergia;
	}
	
	
	//m�todos Geters e Seters
	public int getQtdeAtivos() {
		return qtdeAtivos;
	}

	public void setQtdeAtivos(int qtdeAtivos) {
		this.qtdeAtivos = qtdeAtivos;
	}

	public static double getFatorEmissaoCO() {
		return fatorEmissaoCO;
	}

	public static void setFatorEmissaoCO(double fatorEmissaoCO) {
		Calculadora.fatorEmissaoCO = fatorEmissaoCO;
	}

	public double getTaxaEnergia() {
		return taxaEnergia;
	}

	public void setTaxaEnergia(double taxaEnergia) {
		this.taxaEnergia = taxaEnergia;
	}

	public ArrayList<AtivoTI> getListaDeAtivos() {
		return listaDeAtivos;
	}

	public void setListaDeAtivos(ArrayList<AtivoTI> listaDeAtivos) {
		this.listaDeAtivos = listaDeAtivos;
	}
	
	
	
}
