package br.com.emissoesti.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;
import br.com.emissoesti.model.AtivoTI;
import br.com.emissoesti.model.Usuario;

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
	/*
	 * Método insere no banco de dados MySql uma lista de objetos do tipo AtivoTI e seus respectivos atributos
	 */
	public void adiciona(ArrayList<AtivoTI> ativoList) throws SQLException {
		
		String sql = "INSERT INTO ATIVO_TI"
				+ " (NOME_ATIVO_TI, FABRICANTE_ATIVO_TI, CONSUMO_ENERGIA_ATIVO_TI, EMISSAO_ATIVO_TI)"
				+ "values (?,?,?,?)"; 
		PreparedStatement stmt;
		
		try {
			stmt = connection.prepareStatement(sql);
			
			for(int i = 0; (i <= ativoList.size() - 1); i++){
				
				stmt.getGeneratedKeys();
				stmt.setString(1, ativoList.get(i).getHostName());
				stmt.setString(2, ativoList.get(i).getFabricante());
				stmt.setDouble(3, ativoList.get(i).getConsumoEnergia());
				stmt.setDouble(4, ativoList.get(i).getValorEmissaoCO());
				stmt.execute();
				
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			connection.close();
		}
	}
	
	/*
	 * Listar ativoTI
	 */
	public ArrayList<AtivoTI> listaAtivo(Usuario codigoUsuario) throws SQLException {
			
		ResultSet res = null;	
	
		String sql = "SELECT * FROM ATIVO_TI"
				+ "WHERE ID_USUARIO = " + codigoUsuario;
		PreparedStatement stmt;
		
		ArrayList<AtivoTI> ativoList = new ArrayList<AtivoTI>();
		AtivoTI itemAtivo = new AtivoTI();
		
		try {
			stmt = connection.prepareStatement(sql);
			res = stmt.executeQuery();
			
			while(res.next()){
				
				for(int i = 0; (i <= ativoList.size() - 1); i++){
					
					//stmt.getGeneratedKeys();
					int count = 1;

					itemAtivo.setHostName(res.getString(count++));
					itemAtivo.setFabricante(res.getString(count++));
					itemAtivo.setConsumoEnergia(res.getDouble(count++));
					itemAtivo.setValorEmissaoCO(res.getDouble(count++));
					
					ativoList.add(itemAtivo);
					
				}
				
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			connection.close();
		}
		return ativoList;
	}
		
	
	//select MAX(consumo_energia_ativo)
	public AtivoTI retornaMaxAtivo(){
		
		
		
		return null;
	}
}
