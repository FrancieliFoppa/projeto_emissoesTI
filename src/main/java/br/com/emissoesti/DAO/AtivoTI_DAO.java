package br.com.emissoesti.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	public void adiciona(ArrayList<AtivoTI> ativoList) {
		
		String sql = "INSERT INTO ATIVO_TI"
				+ " (NOME_ATIVO_TI, FABRICANTE_ATIVO_TI, CONSUMO_ENERGIA_ATIVO_TI, EMISSAO_ATIVO_TI)"
				+ "values (?,?,?,?)"; 
		PreparedStatement stmt;
		
		try {
			stmt = connection.prepareStatement(sql);
			
			for(int i = 0; (i <= ativoList.size() - 1); i++){
				
				stmt.setString(1, ativoList.get(i).getHostName());
				stmt.setString(2, ativoList.get(i).getFabricante());
				stmt.setDouble(3, ativoList.get(i).getConsumoEnergia());
				stmt.setDouble(4, ativoList.get(i).getValorEmissaoCO());
				stmt.execute();
				
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//Listar ativoTI
	
	
	//select MAX(consumo_energia_ativo)
	public AtivoTI retornaMaxAtivo(){
		
		
		
		return null;
	}
}
