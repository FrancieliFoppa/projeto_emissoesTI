package br.com.emissoesti.controller;

import javax.ws.rs.Path;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

@Path("/graficos")
public class Graficos_Controller {

	private static final long serialVersionUID = 1L;
	
	public Graficos_Controller() {}

	public Graficos_Controller(String applicationTitle, String chartTitle) {
		super();
		// Isso ir� criar o conjunto de dados
		PieDataset dataset = createDataset();

		// com base no conjunto de dados que criamos o gr�fico
		JFreeChart chart = createChart(dataset, chartTitle);

		// vamos colocar o gr�fico em um painel
		ChartPanel chartPanel = new ChartPanel(chart);

		// default tamanho
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));

		// adiciona a nossa aplica��o
		//setContentPane(chartPanel);

	}

	/**
	 * Cria um conjunto de dados de amostra 
	 */

	private PieDataset createDataset() {
		DefaultPieDataset result = new DefaultPieDataset();
		result.setValue("Linux", 29);
		result.setValue("Mac", 20);
		result.setValue("Windows", 51);
		return result;

	}

	/**
	 * Cria um gr�fico 
	 */

	private JFreeChart createChart(PieDataset dataset, String title) {

		JFreeChart chart = ChartFactory.createPieChart3D(title, // t�tulo / gr�fico 
				dataset, // dados 
				true, // include lenda
				true, false);

		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setStartAngle(290);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.5f);
		return chart;

	}

}
