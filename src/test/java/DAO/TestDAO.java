package DAO;

import java.sql.SQLException;
import org.junit.Test;
import br.com.emissoesti.DAO.AtivoTI_DAO;

public class TestDAO {

	@Test
	public void test() throws SQLException {
		
		AtivoTI_DAO ativoDAO = new AtivoTI_DAO();
		ativoDAO.listaAtivo(1);
		
	}

}
