package Calculos;

import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Test;
import br.com.emissoesti.controller.Calculadora_Controller;
import br.com.emissoesti.model.AtivoTI;

public class TestCalculos {

	@Test
	public void test() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		Calculadora_Controller testCalc = new Calculadora_Controller();
		
		//ArrayList<AtivoTI> testList = new ArrayList<AtivoTI>();
		
		//AtivoTI ativoTest = new AtivoTI("NomeTest", "HP", 0.0, 12.3);
		//AtivoTI ativoTest2 = new AtivoTI("NomeTest2", "IBM", 0.0, 45.6);
		
		//testList.add(ativoTest);
		//testList.add(ativoTest2);
		
		//testCalc.calculaEmissao(0.5317, testList);
		
		testCalc.calculaEmissao(531.7, 1);
				
	}

}
