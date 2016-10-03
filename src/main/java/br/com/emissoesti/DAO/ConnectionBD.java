package br.com.emissoesti.DAO;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBD {
	
	public ConnectionBD(){}

	public ConnectionBD getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}
		return (ConnectionBD) DriverManager.getConnection("jdbc:mysql://127.0.0.1/bdem","root", "passROOT");
	}

}


