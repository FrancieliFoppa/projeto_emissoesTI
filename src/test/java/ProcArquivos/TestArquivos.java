package ProcArquivos;

import java.sql.SQLException;

import org.junit.Test;
import br.com.emissoesti.controller.AtivoTI_Controller;
import junit.framework.TestCase;

public class TestArquivos extends TestCase {

	@Test
	public void test() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		AtivoTI_Controller testAtivo = new AtivoTI_Controller();
		
		testAtivo.processaCSV("C:\\ativos_teste.csv");
		//assertNotNull(testAtivo.registra(ativoTI, validacao));	
		
		//testAtivo.processaXML("C:\\testes\\arquivoXML.xml");
	}
	
}
