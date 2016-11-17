package Graficos;

import org.junit.Test;
import br.com.emissoesti.controller.Graficos_Controller;

public class TestGraficos {

	@Test
	public void test() {
		
		Graficos_Controller demo = new Graficos_Controller("Comparação", "Qual sistema operacional você está usando?");
        demo.setVisible(true);
        
	}

}
