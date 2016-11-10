package Relatorios;

import java.util.ArrayList;
import org.junit.Test;
import br.com.emissoesti.controller.Relatorios_Controller;
import br.com.emissoesti.model.AtivoTI;

public class TestRelatorios {

	@Test
	public void test() {
		
		Relatorios_Controller testRelatorio = new Relatorios_Controller();
		
		ArrayList<AtivoTI> testList = new ArrayList<AtivoTI>();
		
		AtivoTI ativoTest = new AtivoTI("NomeTest", "HP", "DELL", "Servidor", 1.0, 12.3, 24, "Diario");
		AtivoTI ativoTest2 = new AtivoTI("NomeTest2", "IBM", "HP", "Servidor", 2.0, 45.6, 24, "Diario");
		AtivoTI ativoTest3 = new AtivoTI("NomeTest2", "IBM", "HP", "Servidor", 2.0, 45.6, 24, "Diario");
		AtivoTI ativoTest4 = new AtivoTI("NomeTest2", "IBM", "HP", "Servidor", 2.0, 45.6, 24, "Diario");
		AtivoTI ativoTest5 = new AtivoTI("NomeTest2", "IBM", "HP", "Servidor", 2.0, 45.6, 24, "Diario");
		AtivoTI ativoTest6 = new AtivoTI("NomeTest2", "IBM", "HP", "Servidor", 2.0, 45.6, 24, "Diario");
		AtivoTI ativoTest7 = new AtivoTI("NomeTest2", "IBM", "HP", "Servidor", 2.0, 45.6, 24, "Diario");
		AtivoTI ativoTest8 = new AtivoTI("NomeTest2", "IBM", "HP", "Servidor", 2.0, 45.6, 24, "Diario");
		AtivoTI ativoTest9 = new AtivoTI("NomeTest2", "IBM", "HP", "Servidor", 2.0, 45.6, 24, "Diario");
		
		testList.add(ativoTest);
		testList.add(ativoTest2);
		testList.add(ativoTest3);
		testList.add(ativoTest4);
		testList.add(ativoTest5);
		testList.add(ativoTest6);
		testList.add(ativoTest7);
		testList.add(ativoTest8);
		testList.add(ativoTest9);
				
		
		//testRelatorio.relatorioAtivosConsumoEnergia(testList);	
		testRelatorio.relatorioAtivos(testList);
		
		//testRelatorio.relatorioPropostasResultados();
	}
}
