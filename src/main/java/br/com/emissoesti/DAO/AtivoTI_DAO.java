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
		  				+ " (nome_ativo, fabricante_ativo, consumo_energia_ativo, modelo_ativo, categoria_ativo, horas_consumo_ativo, tipo_consumo_ativo)"
		  				+ "values (?,?,?,?,?,?,?)"; 
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			
				for(int i = 0; (i <= ativoList.size() - 1); i++){
				 					
				 	stmt.setString(1, ativoList.get(i).getHostName());
				 	stmt.setString(2, ativoList.get(i).getFabricante());
					stmt.setDouble(3, ativoList.get(i).getConsumoEnergia());
					stmt.setString(4, ativoList.get(i).getModelo());
					stmt.setString(5, ativoList.get(i).getCategoria());
					stmt.setDouble(6, ativoList.get(i).getHorasConsumo());
				 	stmt.setString(7, ativoList.get(i).getTipoConsumo());
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
		//System.out.println(ativoList.get(3).getIdAtivo() + " " + ativoList.get(3).getHostName() + " " + ativoList.get(3).getFabricante() + " " + ativoList.get(3).getConsumoEnergia());
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
		
		AtivoTI ativoMaiorConsumo = null;
		ResultSet res = null;	
		
		String sql = "select id_ativo_ti, fabricante_ativo, modelo_ativo, MIN(consumo_energia_ativo) from ativo_ti " 
					+ "group by id_ativo_ti, fabricante_ativo, modelo_ativo, consumo_energia_ativo";
		PreparedStatement stmt;
				
		try {
				stmt = connection.prepareStatement(sql);
				res = stmt.executeQuery();
				
				//res.beforeFirst();
				while(res.next()){ 
					ativoMaiorConsumo = new AtivoTI();					
					ativoMaiorConsumo.setIdAtivo(res.getInt(1));
					ativoMaiorConsumo.setFabricante(res.getString(2));
					ativoMaiorConsumo.setModelo(res.getString(3));
					ativoMaiorConsumo.setConsumoEnergia(res.getDouble(4));
					
				}
			
			}catch (SQLException e){
				throw new RuntimeException(e);
			}finally{
				//connection.close();
			}
		System.out.println(ativoMaiorConsumo.getFabricante() + " " + ativoMaiorConsumo.getModelo() + " " + ativoMaiorConsumo.getConsumoEnergia());
		return ativoMaiorConsumo;
	}
	
	/*
	 * método retorna o ativo de TI que possui menor consumo de energia, e consequentemente
	 * menor emissão de CO², independente do usuário/empresa 
	 */
	public AtivoTI retornaMinAtivo(){
				
		AtivoTI ativoMenorConsumo = null;
		ResultSet res = null;	
		
		String sql = "select id_ativo_ti, fabricante_ativo, modelo_ativo, MAX(consumo_energia_ativo) from ativo_ti " 
					+ "group by id_ativo_ti, fabricante_ativo, modelo_ativo, consumo_energia_ativo";
		PreparedStatement stmt;
				
		try {
				stmt = connection.prepareStatement(sql);
				res = stmt.executeQuery();
				
				//res.beforeFirst();
				while(res.next()){ 
					ativoMenorConsumo = new AtivoTI();					
					ativoMenorConsumo.setIdAtivo(res.getInt(1));
					ativoMenorConsumo.setFabricante(res.getString(2));
					ativoMenorConsumo.setModelo(res.getString(3));
					ativoMenorConsumo.setConsumoEnergia(res.getDouble(4));
					
				}
			
			}catch (SQLException e){
				throw new RuntimeException(e);
			}finally{
				//connection.close();
			}
		System.out.println(ativoMenorConsumo.getFabricante() + " " + ativoMenorConsumo.getModelo() + " " + ativoMenorConsumo.getConsumoEnergia());
		return ativoMenorConsumo;
	}
}
