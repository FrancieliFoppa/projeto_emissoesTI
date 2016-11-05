package br.com.emissoesti.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import br.com.emissoesti.model.AtivoTI;

public class AtivoTI_DAO {

	private Connection connection;
	
	public AtivoTI_DAO() {
		try {
			this.connection = new ConnectionBD().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * M�todo insere no banco de dados MySql uma lista de objetos do tipo AtivoTI e seus respectivos atributos
	 */
	public void adiciona(ArrayList<AtivoTI> ativoList) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {

		try {	
			
			String sql = "insert into ativo_ti"
	  				+ " (nome_ativo, fabricante_ativo, consumo_energia_ativo, modelo_ativo)"
	  				+ "values (?,?,?,?)"; 
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			
				for(int i = 0; (i <= ativoList.size() - 1); i++){
				 					
				 	stmt.setString(1, ativoList.get(i).getHostName());
				 	stmt.setString(2, ativoList.get(i).getFabricante());
					stmt.setDouble(3, ativoList.get(i).getConsumoEnergia());
					stmt.setString(4, ativoList.get(i).getModelo());
					//stmt.setDouble(4, ativoList.get(i).getCustoEnergia());
				 	//stmt.setDouble(6, ativoList.get(i).getValorEmissaoCO());
				 	stmt.execute();
				
				}
			}finally{
				connection.close();
			}
	}
	
	/*
	 * M�todo busca do banco de dados MySql uma lista de objetos do tipo AtivoTI e seus respectivos atributos
	 */
	public ArrayList<AtivoTI> listaAtivo(int idUsuario) throws SQLException {
			
		ResultSet res = null;	
	
		String sql = "select * from ativo_ti"
				+ "where id_usuario = " + idUsuario;
		PreparedStatement stmt;
		
		ArrayList<AtivoTI> ativoList = new ArrayList<AtivoTI>();
		AtivoTI itemAtivo = new AtivoTI();
		
		try {
				stmt = connection.prepareStatement(sql);
				res = stmt.executeQuery();
				
				while(res.next()){
					
					//for(int i = 0; (i <= ativoList.size() - 1); i++){
						
						int count = 1;
	
						itemAtivo.setHostName(res.getString(count++));
						itemAtivo.setFabricante(res.getString(count++));
						itemAtivo.setConsumoEnergia(res.getDouble(count++));
						itemAtivo.setValorEmissaoCO(res.getDouble(count++));
						
						ativoList.add(itemAtivo);
						
					//}
					
				}
			
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}finally{
				connection.close();
			}
			return ativoList;
	}	
	
	/*
	 * M�todo atualiza no banco de dados MySql a lista de objetos do tipo AtivoTI com seus respctivos valores de emiss�o de co�
	 */
	public void atualizaEmissao(ArrayList<AtivoTI> ativoList, int idUsuario) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {

		try {	
			
				String sql = "update into ativo_ti"
		  				+ " (emissao_ativo)"
		  				+ "values (?)"
						+ "where id_usuario = " + idUsuario; 
				
				PreparedStatement stmt = connection.prepareStatement(sql);			
				
					for(int i = 0; (i <= ativoList.size() - 1); i++){
					 					
					 	stmt.setDouble(6, ativoList.get(i).getValorEmissaoCO());
					 	stmt.execute();
				
					}
			}finally{
				connection.close();
			}
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
