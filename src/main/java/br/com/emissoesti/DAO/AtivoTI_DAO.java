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
	 * Método insere no banco de dados MySql uma lista de objetos do tipo AtivoTI e seus respectivos atributos
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
	 * Método busca do banco de dados MySql uma lista de objetos do tipo AtivoTI e seus respectivos atributos
	 */
	public ArrayList<AtivoTI> listaAtivo(int idUsuario) throws SQLException {
			
		ResultSet res = null;	
	
		String sql = "select id_ativo_ti, consumo_energia_ativo from ativo_ti where id_usuario = " + idUsuario;
		PreparedStatement stmt;
		
		ArrayList<AtivoTI> ativoList = new ArrayList<AtivoTI>();
		
		
		try {
				stmt = connection.prepareStatement(sql);
				res = stmt.executeQuery();
				
				res.beforeFirst();
				while(res.next()){ 
					AtivoTI itemAtivo = new AtivoTI();
					//System.out.println(res.getInt(1));
					//System.out.println(res.getDouble(2));
					
					itemAtivo.setIdAtivo(res.getInt(1));
					itemAtivo.setConsumoEnergia(res.getDouble(2));
					ativoList.add(itemAtivo);
				}
			
			}catch (SQLException e){
				throw new RuntimeException(e);
			}finally{
				//connection.close();
			}
		//System.out.println(ativoList.get(0).getIdAtivo() + " " + ativoList.get(0).getHostName() + " " + ativoList.get(0).getFabricante() + " " + ativoList.get(0).getConsumoEnergia());
			return ativoList;
	}	
	
	/*
	 * Método atualiza no banco de dados MySql a lista de objetos do tipo AtivoTI com seus respctivos valores de emissão de co²
	 */
	public void atualizaEmissao(ArrayList<AtivoTI> ativoList, int idUsuario) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {

		try {	
			
			String sql = "update ativo_ti set emissao_ativo = ? where id_usuario = " + idUsuario + " and id_ativo_ti = ?"; 
			
			PreparedStatement stmt = connection.prepareStatement(sql);			
			
				for(int i = 0; (i <= ativoList.size() - 1); i++){
				 					
				 	stmt.setDouble(1, ativoList.get(i).getValorEmissaoCO());
				 	stmt.setInt(2, ativoList.get(i).getIdAtivo());
				 	stmt.executeUpdate();
				}
				
		}catch (SQLException e){
			throw new RuntimeException(e);
		}finally{
			connection.close();
		}
	}
	
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
