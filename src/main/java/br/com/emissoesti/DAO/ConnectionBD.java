package br.com.emissoesti.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBD{
    
    public Connection getConnection() throws SQLException{
    	
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Conexão criada!");
			return DriverManager.getConnection("jdbc:mysql://127.0.0.1/dbem","root", "");
			
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}
    }
}