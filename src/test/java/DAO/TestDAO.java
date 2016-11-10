package DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Test;
import br.com.emissoesti.DAO.AtivoTI_DAO;
import br.com.emissoesti.model.AtivoTI;

public class TestDAO {

	@Test
	public void test() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		AtivoTI_DAO ativoDAO = new AtivoTI_DAO();
		//ArrayList<AtivoTI> testList = new ArrayList<AtivoTI>();
		ativoDAO.listaAtivo(3);
		
		//ativoDAO.retornaMinAtivo();
		/*
		
		
		AtivoTI ativoTest = new AtivoTI("NomeTest", "HP", 0.0, 12.3);
		AtivoTI ativoTest2 = new AtivoTI("NomeTest2", "IBM", 0.0, 45.6);
		
		testList.add(ativoTest);
		testList.add(ativoTest2);
		*/
		//ativoDAO.atualizaEmissao(testList, 3);
	}

}
