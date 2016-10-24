package br.com.emissoesti.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import br.com.emissoesti.model.Calculadora;

public class Calculadora_DAO {
	
	private Calculadora calc;

	public Calculadora_DAO(Connection connection) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		try {
			connection = new ConnectionBD().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/*
	public void adiciona(Calculadora calc) {
		String sql = "insert into ativo_ti (nome, valor) values (?,?)"; 
		PreparedStatement stmt;
		try {
			System.out.println("TESTE");
			   //stmt = connection.prepareStatement(sql); ---> classe CONNECTION
			//stmt.setString(1, ativo.getHostName());
			//stmt.setDouble(2, ativo.getConsumoEnergia());
			//stmt.setDouble(2, ativo.getValorEmissaoCO());
			//stmt.setString(2, ativo.getFabricante());
			   //stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}*/

}
