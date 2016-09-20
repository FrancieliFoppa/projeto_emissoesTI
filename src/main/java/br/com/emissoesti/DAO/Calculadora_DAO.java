package br.com.emissoesti.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.emissoesti.model.AtivoTI;
import br.com.emissoesti.model.Calculadora;

public class Calculadora_DAO {
	
	private Calculadora calc;

	public Calculadora_DAO() {
		// TODO Auto-generated constructor stub
	}
	

	public Calculadora_DAO() {
		try {
			connection = new Connection().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void adiciona(Calculadora calc) {
		String sql = "insert into produto (nome, valor) values (?,?)"; //tabela do banco????
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			//stmt.setString(1, ativo.getHostName());
			//stmt.setDouble(2, ativo.getConsumoEnergia());
			//stmt.setDouble(2, ativo.getValorEmissaoCO());
			//stmt.setString(2, ativo.getFabricante());
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
