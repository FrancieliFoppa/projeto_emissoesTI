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
		
		AtivoTI ativoTest = new AtivoTI("NomeTest", "HP", 1.0, 12.3);
		AtivoTI ativoTest2 = new AtivoTI("NomeTest2", "IBM", 2.0, 45.6);
		
		testList.add(ativoTest);
		testList.add(ativoTest2);
		
		testRelatorio.relatorioAtivosConsumoEnergia(testList);	
		testRelatorio.relatorioAtivosEmissao(testList);
	}
}
