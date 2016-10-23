package br.com.emissoesti.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import br.com.emissoesti.model.AtivoTI;

@Repository
public class AtivoTI_DAO {


	
	public AtivoTI_DAO() {}
	
	/*
	 * Método insere no banco de dados MySql uma lista de objetos do tipo AtivoTI e seus respectivos atributos
	 */

	public void adiciona(ArrayList<AtivoTI> ativoList) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		
			
		ConnectionBD connection = new ConnectionBD();
				
		try {

			connection.conectar();

			for(int i = 0; (i <= ativoList.size() - 1); i++){
				//stmt.getGeneratedKeys();
				String sql = "INSERT INTO ATIVO_TI"
						+ " (NOME_ATIVO_TI, FABRICANTE_ATIVO_TI, CONSUMO_ENERGIA_ATIVO_TI, EMISSAO_ATIVO_TI)"
						+ "values ("
						+ "'" + ativoList.get(i).getHostName() + "',"
						+ "'" + ativoList.get(i).getFabricante() + "',"
						+ 	    ativoList.get(i).getConsumoEnergia()
						+		ativoList.get(i).getValorEmissaoCO()
						+ ");";
				
				connection.insertSQL(sql);
				
			}
		}finally{
			connection.fecharConexao();
		}
	}
	
	/*
	 * Método busca do banco de dados MySql uma lista de objetos do tipo AtivoTI e seus respectivos atributos
	 
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
	}*/
		
	
	/*
	 * método retorna o ativo de TI que possui maior consumo de energia, e consequentemente
	 * maior emissão de CO², independente do usuário/empresa 
	 */
	public AtivoTI retornaMaxAtivo(){
		
		AtivoTI MaiorConsumoAtivo = new AtivoTI();
		
		//select MAX(consumo_energia_ativo)
		
		return MaiorConsumoAtivo;
	}
	
	/*
	 * método retorna o ativo de TI que possui menor consumo de energia, e consequentemente
	 * menor emissão de CO², independente do usuário/empresa 
	 */
	public AtivoTI retornaMinAtivo(){
		
		AtivoTI MenorConsumoAtivo = new AtivoTI();
		
		//select Min(consumo_energia_ativo)
		
		return MenorConsumoAtivo;
	}
}
