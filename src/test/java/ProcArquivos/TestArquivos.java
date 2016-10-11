package ProcArquivos;

import org.junit.Test;

import br.com.emissoesti.controller.AtivoTI_Controller;
import junit.framework.TestCase;

public class TestArquivos extends TestCase {

	@Test
	public void test() {
		
		AtivoTI_Controller testAtivo = new AtivoTI_Controller();
		
		testAtivo.processaCSV("C:\\arquivoCSV.csv");
		//assertNotNull(testAtivo.registra(ativoTI, validacao));	
		
		testAtivo.processaXML("C:\\arquivoXML.xml");
	}
	
}
