package Relatorios;

import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Test;
import br.com.emissoesti.controller.Relatorios_Controller;
import br.com.emissoesti.model.AtivoTI;

public class TestRelatorios {

	@Test
	public void test() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		Relatorios_Controller testRelatorio = new Relatorios_Controller();
		
		//testRelatorio.relatorioAtivosConsumoEnergia(testList);	
		//testRelatorio.relatorioAtivos(1);
		//testRelatorio.relatorioConsumoAtivos(1);
		testRelatorio.relatorioEmissaoCOConsumoEnergiaAtivos(1);
		
		//testRelatorio.relatorioPropostasResultados();
	}
}
