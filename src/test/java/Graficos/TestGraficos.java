package Graficos;

import org.junit.Test;
import br.com.emissoesti.controller.Graficos_Controller;

public class TestGraficos {

	@Test
	public void test() {
		
		Graficos_Controller demo = new Graficos_Controller("Compara��o", "Qual sistema operacional voc� est� usando?");
        demo.setVisible(true);
        
	}

}
