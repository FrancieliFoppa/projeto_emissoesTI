package br.com.emissoesti.DAO;

import java.sql.Connection;
import java.sql.SQLException;

public class Calculadora_DAO {
	
	public Calculadora_DAO(Connection connection) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		try {
			connection = new ConnectionBD().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
