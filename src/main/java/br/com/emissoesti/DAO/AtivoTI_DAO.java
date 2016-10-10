package br.com.emissoesti.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.stereotype.Repository;
import br.com.emissoesti.model.AtivoTI;

@Repository
public class AtivoTI_DAO {

	private Connection connection;
	
	public AtivoTI_DAO() {
		ConnectionBD connection = new ConnectionBD();
		try {
			connection.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void adiciona(AtivoTI ativo) {
		String sql = "INSERT INTO ATIVO_TI"
				+ " (NOME_ATIVO_TI, FABRICANTE_ATIVO_TI, CONSUMO_ENERGIA_ATIVO_TI, EMISSAO_ATIVO_TI)"
				+ "values (?,?,?,?)"; 
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, ativo.getHostName());
			stmt.setString(2, ativo.getFabricante());
			stmt.setDouble(3, ativo.getConsumoEnergia());
			stmt.setDouble(4, ativo.getValorEmissaoCO());
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//Listar ativoTI
	
	
}
