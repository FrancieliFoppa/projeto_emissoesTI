package br.com.emissoesti.model;

public class AtivoTI {
	
	private int idAtivo;
	private String hostName;
	private String fabricante;
	private String modelo;
	private String categoria;
	private double valorEmissaoCODiario;
	private double valorEmissaoCOSemanal;
	private double valorEmissaoCOMensal;
	private double valorEmissaoCOAnual;
	private double consumoEnergia;
	private double consumoEnergiaDiario;
	private double consumoEnergiaSemanal;
	private double consumoEnergiaMensal;
	private double consumoEnergiaAnual;
	private double custoEnergia;
	private double horasConsumoDiario;
	private int diasConsumo;
	
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
		this.consumoEnergia = consumoEnergia;
	}
	
	public AtivoTI(String hostName, String fabricante, double valorEmissaoCODiario, double consumoEnergia) {
		this.hostName = hostName;
		this.fabricante = fabricante;
		this.valorEmissaoCODiario = valorEmissaoCODiario;
		this.consumoEnergia = consumoEnergia;
	}
	
	public AtivoTI(String hostName, String fabricante, String modelo, String categoria, double valorEmissaoCODiario, double consumoEnergia, double horasConsumoDiario, int diasConsumo) {
		this.hostName = hostName;
		this.fabricante = fabricante;
		this.modelo = modelo;
		this.categoria = categoria;
		this.valorEmissaoCODiario = valorEmissaoCODiario;
		this.consumoEnergia = consumoEnergia;
		this.consumoEnergia = consumoEnergia;
		this.horasConsumoDiario = horasConsumoDiario;
		this.diasConsumo = diasConsumo;
				
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

	public double getValorEmissaoCODiario() {
		return valorEmissaoCODiario;
	}

	public void setvalorEmissaoCODiario(double valorEmissaoCODiario) {
		this.valorEmissaoCODiario = valorEmissaoCODiario;
	}
	
	public double getValorEmissaoCOSemanal() {
		return valorEmissaoCOSemanal;
	}

	public void setValorEmissaoCOSemanal(double valorEmissaoCOSemanal) {
		this.valorEmissaoCOSemanal = valorEmissaoCOSemanal;
	}

	public double getValorEmissaoCOMensal() {
		return valorEmissaoCOMensal;
	}

	public void setValorEmissaoCOMensal(double valorEmissaoCOMensal) {
		this.valorEmissaoCOMensal = valorEmissaoCOMensal;
	}

	public double getValorEmissaoCOAnual() {
		return valorEmissaoCOAnual;
	}

	public void setValorEmissaoCOAnual(double valorEmissaoCOAnual) {
		this.valorEmissaoCOAnual = valorEmissaoCOAnual;
	}

	public void setValorEmissaoCODiario(double valorEmissaoCODiario) {
		this.valorEmissaoCODiario = valorEmissaoCODiario;
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

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public double getHorasConsumoDiario() {
		return horasConsumoDiario;
	}

	public void sethorasConsumoDiario(double horasConsumoDiario) {
		this.horasConsumoDiario = horasConsumoDiario;
	}

	public double getConsumoEnergiaDiario() {
		return consumoEnergiaDiario;
	}

	public void setConsumoEnergiaDiario(double consumoEnergiaDiario) {
		this.consumoEnergiaDiario = consumoEnergiaDiario;
	}

	public double getConsumoEnergiaSemanal() {
		return consumoEnergiaSemanal;
	}

	public void setConsumoEnergiaSemanal(double consumoEnergiaSemanal) {
		this.consumoEnergiaSemanal = consumoEnergiaSemanal;
	}

	public double getConsumoEnergiaMensal() {
		return consumoEnergiaMensal;
	}

	public void setConsumoEnergiaMensal(double consumoEnergiaMensal) {
		this.consumoEnergiaMensal = consumoEnergiaMensal;
	}

	public double getConsumoEnergiaAnual() {
		return consumoEnergiaAnual;
	}

	public void setConsumoEnergiaAnual(double consumoEnergiaAnual) {
		this.consumoEnergiaAnual = consumoEnergiaAnual;
	}

	public int getDiasConsumo() {
		return diasConsumo;
	}

	public void setDiasConsumo(int diasConsumo) {
		this.diasConsumo = diasConsumo;
	}

}
