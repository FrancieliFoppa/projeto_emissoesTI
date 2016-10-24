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
	
	public AtivoTI_DAO(Connection connection) {
		try {
			connection = new ConnectionBD().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * M�todo insere no banco de dados MySql uma lista de objetos do tipo AtivoTI e seus respectivos atributos
	 */
	public void adiciona(ArrayList<AtivoTI> ativoList) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {

		String sql = "insert intO ativo_ti"
  				+ " (id_ativo_ti, nome_ativo, fabricante_ativo, consumo_energia_ativo, custo_energia_ativo, emissao_ativo)"
  				+ "values (?,?,?,?)"; 
  		PreparedStatement stmt;
  		
  		ResultSet tableKeys; 
				
		try {	
			
			stmt = connection.prepareStatement(sql);
			
			tableKeys =  stmt.getGeneratedKeys();
			
			for(int i = 0; (i <= ativoList.size() - 1); i++){
			 	
				tableKeys.next();
				int autoGeneratedID = tableKeys.getInt(1);
				stmt.setInt(1, autoGeneratedID );
			 	stmt.setString(2, ativoList.get(i).getHostName());
			 	stmt.setString(3, ativoList.get(i).getFabricante());
				stmt.setDouble(4, ativoList.get(i).getConsumoEnergia());
			 	stmt.setDouble(5, ativoList.get(i).getValorEmissaoCO());
			 	stmt.execute();
			
			}
		}finally{
			connection.close();
		}
	}
	
	/*
	 * M�todo busca do banco de dados MySql uma lista de objetos do tipo AtivoTI e seus respectivos atributos
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
	
	/*
	 * m�todo retorna o ativo de TI que possui maior consumo de energia, e consequentemente
	 * maior emiss�o de CO�, independente do usu�rio/empresa 
	 */
	public AtivoTI retornaMaxAtivo(){
		
		AtivoTI MaiorConsumoAtivo = new AtivoTI();
		
		//select MAX(consumo_energia_ativo)
		
		return MaiorConsumoAtivo;
	}
	
	/*
	 * m�todo retorna o ativo de TI que possui menor consumo de energia, e consequentemente
	 * menor emiss�o de CO�, independente do usu�rio/empresa 
	 */
	public AtivoTI retornaMinAtivo(){
		
		AtivoTI MenorConsumoAtivo = new AtivoTI();
		
		//select Min(consumo_energia_ativo)
		
		return MenorConsumoAtivo;
	}
}
