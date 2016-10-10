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
		String sql = "insert into produto (nome, valor) values (?,?)"; //tabela do banco????
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, ativo.getHostName());
			stmt.setDouble(2, ativo.getConsumoEnergia());
			stmt.setDouble(2, ativo.getValorEmissaoCO());
			stmt.setString(2, ativo.getFabricante());
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//Listar ativoTI
	
	
}
