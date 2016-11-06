package br.com.emissoesti.model;

public class AtivoTI {
	
	private int idAtivo;
	private String hostName;
	private String fabricante;
	private String modelo;
	private double valorEmissaoCO;
	private double consumoEnergia;
	private double custoEnergia;
	
	public AtivoTI() {}

	public AtivoTI(String hostName) {
		this.hostName = hostName;
	}
	
	public AtivoTI(String hostName, String fabricante) {
		this.hostName = hostName;
		this.fabricante = fabricante;
	}

	public AtivoTI(String hostName, String fabricante, double consumoEnergia) {
		this.hostName = hostName;
		this.fabricante = fabricante;
	}
	
	public AtivoTI(String hostName, String fabricante, double valorEmissaoCO, double consumoEnergia) {
		this.hostName = hostName;
		this.fabricante = fabricante;
		this.valorEmissaoCO = valorEmissaoCO;
		this.consumoEnergia = consumoEnergia;
	}

	//métodos Geters e Seters	
	public int getIdAtivo() {
		return idAtivo;
	}

	public void setIdAtivo(int idAtivo) {
		this.idAtivo = idAtivo;
	}
	
	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public double getValorEmissaoCO() {
		return valorEmissaoCO;
	}

	public void setValorEmissaoCO(double valorEmissaoCO) {
		this.valorEmissaoCO = valorEmissaoCO;
	}

	public double getConsumoEnergia() {
		return consumoEnergia;
	}

	public void setConsumoEnergia(double consumoEnergia) {
		this.consumoEnergia = consumoEnergia;
	}

	public double getCustoEnergia() {
		return custoEnergia;
	}

	public void setCustoEnergia(double custoEnergia) {
		this.custoEnergia = custoEnergia;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	
}
