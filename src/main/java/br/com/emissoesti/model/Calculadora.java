package br.com.emissoesti.model;

import java.util.ArrayList;

public class Calculadora {
	
	private static double fatorEmissaoCO = 0.0789; //fator de emissão em tCO²/MWh
	private double taxaEnergia;
	private ArrayList<AtivoTI> listaDeAtivos;

	public Calculadora(){}
	
	public Calculadora(double taxaEnergia, ArrayList<AtivoTI> listaDeAtivos ) {
		
		this.taxaEnergia = taxaEnergia;
		this.listaDeAtivos = new ArrayList<AtivoTI>();

	}	
	
	//métodos Geters e Seters
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
