package br.com.emissoesti.model;

public class CalculadoraExpress {
	
	private static double fatorEmissaoCO = 0.5317;
	private double consumoEnergia;
	
	
	public CalculadoraExpress(double consumoEnergia) {
		this.setConsumoEnergia(consumoEnergia);
	}

	/*
	 * Metodo realiza o calcula da emissão de apenas 1 ativo de TI
	 */
	public double CalculaExpress(double consumoEnergia){
		
		double resultadoEmissao;		
		resultadoEmissao = (consumoEnergia * fatorEmissaoCO);
				
		return resultadoEmissao;		
	}

	//métodos Geters e Seters
	public double getConsumoEnergia() {
		return consumoEnergia;
	}

	public void setConsumoEnergia(double consumoEnergia) {
		this.consumoEnergia = consumoEnergia;
	}

	public static double getFatorEmissaoCO() {
		return fatorEmissaoCO;
	}

	public static void setFatorEmissaoCO(double fatorEmissaoCO) {
		CalculadoraExpress.fatorEmissaoCO = fatorEmissaoCO;
	}
	

}
