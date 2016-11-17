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
	 * Método busca do banco de dados MySql uma lista de objetos do tipo AtivoTI e seus respectivos atributos
	 */
	public ArrayList<AtivoTI> listaAtivoInfCalcConsumo(int idUsuario) throws SQLException {
			
		ResultSet res = null;	
	
		String sql = "select id_ativo_ti, consumo_energia_ativo, horas_consumo_diario, dias_consumo from ativo_ti where id_usuario = " + idUsuario;
		PreparedStatement stmt;
		
		ArrayList<AtivoTI> ativoList = new ArrayList<AtivoTI>();
		
		try {
				stmt = connection.prepareStatement(sql);
				res = stmt.executeQuery();
				
				res.beforeFirst();
				while(res.next()){ 
					AtivoTI itemAtivo = new AtivoTI();					
					itemAtivo.setIdAtivo(res.getInt(1));
					itemAtivo.setConsumoEnergia(res.getDouble(2));
					itemAtivo.sethorasConsumoDiario(res.getDouble(3));
					itemAtivo.setDiasConsumo(res.getInt(4));
					ativoList.add(itemAtivo);
				}
			
			}catch (SQLException e){
				throw new RuntimeException(e);
			}

			return ativoList;
	}	
	
	public ArrayList<AtivoTI> listaAtivoInfConsumo(int idUsuario) throws SQLException {
		
		ResultSet res = null;	
	
		String sql = "select id_ativo_ti, nome_ativo, fabricante_ativo, modelo_ativo, categoria_ativo, "
				+ "consumo_energia_diario, consumo_energia_semanal, consumo_energia_mensal, consumo_energia_anual, tipo_consumo from ativo_ti where id_usuario = " + idUsuario;
		PreparedStatement stmt;
		
		ArrayList<AtivoTI> ativoList = new ArrayList<AtivoTI>();
		
		try {
				stmt = connection.prepareStatement(sql);
				res = stmt.executeQuery();
				
				res.beforeFirst();
				while(res.next()){ 
					AtivoTI itemAtivo = new AtivoTI();					
					itemAtivo.setIdAtivo(res.getInt(1));
					itemAtivo.setHostName(res.getString(2));
					itemAtivo.setFabricante(res.getString(3));
					itemAtivo.setModelo(res.getString(4));
					itemAtivo.setCategoria(res.getString(5));
					itemAtivo.setConsumoEnergiaDiario(res.getDouble(6));
					itemAtivo.setConsumoEnergiaSemanal(res.getDouble(7));
					itemAtivo.setConsumoEnergiaMensal(res.getDouble(8));
					itemAtivo.setConsumoEnergiaAnual(res.getDouble(9));
					itemAtivo.setTipoConsumo(res.getString(10));
					ativoList.add(itemAtivo);
				}
			
			}catch (SQLException e){
				throw new RuntimeException(e);
			}

			return ativoList;
	}
	
public ArrayList<AtivoTI> listaAtivoInfEmissao(int idUsuario) throws SQLException {
		
		ResultSet res = null;	
	
		String sql = "select id_ativo_ti, nome_ativo, fabricante_ativo, modelo_ativo, categoria_ativo, "
				+ "emissao_ativo_diario, emissao_ativo_semanal, emissao_ativo_mensal, emissao_ativo_anual, tipo_consumo from ativo_ti where id_usuario = " + idUsuario;
		PreparedStatement stmt;
		
		ArrayList<AtivoTI> ativoList = new ArrayList<AtivoTI>();
		
		try {
				stmt = connection.prepareStatement(sql);
				res = stmt.executeQuery();
				
				res.beforeFirst();
				while(res.next()){ 
					AtivoTI itemAtivo = new AtivoTI();					
					itemAtivo.setIdAtivo(res.getInt(1));
					itemAtivo.setHostName(res.getString(2));
					itemAtivo.setFabricante(res.getString(3));
					itemAtivo.setModelo(res.getString(4));
					itemAtivo.setCategoria(res.getString(5));
					itemAtivo.setValorEmissaoCODiario(res.getDouble(6));
					itemAtivo.setValorEmissaoCOSemanal(res.getDouble(7));
					itemAtivo.setValorEmissaoCOMensal(res.getDouble(8));
					itemAtivo.setValorEmissaoCOAnual(res.getDouble(9));
					itemAtivo.setTipoConsumo(res.getString(10));
					ativoList.add(itemAtivo);
				}
			
			}catch (SQLException e){
				throw new RuntimeException(e);
			}

			return ativoList;
	}
	
	public ArrayList<AtivoTI> listaAtivoInfGerais(int idUsuario) throws SQLException {
		
		ResultSet res = null;	
	
		String sql = "select nome_ativo, fabricante_ativo, modelo_ativo, categoria_ativo, consumo_energia_ativo, "
				+ "horas_consumo_diario, dias_consumo, tipo_consumo from ativo_ti where id_usuario = " + idUsuario;
		PreparedStatement stmt;
		
		ArrayList<AtivoTI> ativoList = new ArrayList<AtivoTI>();
		
		try {
				stmt = connection.prepareStatement(sql);
				res = stmt.executeQuery();
				
				res.beforeFirst();
				while(res.next()){ 
					AtivoTI itemAtivo = new AtivoTI();					
					itemAtivo.setHostName(res.getString(1));
					itemAtivo.setFabricante(res.getString(2));
					itemAtivo.setModelo(res.getString(3));
					itemAtivo.setCategoria(res.getString(4));
					itemAtivo.setConsumoEnergia(res.getDouble(5));
					itemAtivo.sethorasConsumoDiario(res.getDouble(6));
					itemAtivo.setDiasConsumo(res.getInt(7));
					itemAtivo.setTipoConsumo(res.getString(8));
					ativoList.add(itemAtivo);
				}
							
			}catch (SQLException e){
				throw new RuntimeException(e);
			}

			return ativoList;
	}	
	
public ArrayList<AtivoTI> listaAtivoInfCompleta(int idUsuario) throws SQLException {
		
		ResultSet res = null;	
	
		String sql = "select nome_ativo, fabricante_ativo, modelo_ativo, consumo_energia_diario, "
				+ "consumo_energia_semanal, consumo_energia_mensal, consumo_energia_anual, "
				+ "emissao_ativo_diario, emissao_ativo_semanal, emissao_ativo_mensal, emissao_ativo_anual, tipo_consumo from ativo_ti where id_usuario = " + idUsuario;
		PreparedStatement stmt;
		
		ArrayList<AtivoTI> ativoList = new ArrayList<AtivoTI>();
		
		try {
				stmt = connection.prepareStatement(sql);
				res = stmt.executeQuery();
				
				res.beforeFirst();
				while(res.next()){ 
					AtivoTI itemAtivo = new AtivoTI();					
					itemAtivo.setHostName(res.getString(1));
					itemAtivo.setFabricante(res.getString(2));
					itemAtivo.setModelo(res.getString(3));
					itemAtivo.setConsumoEnergiaDiario(res.getDouble(4));
					itemAtivo.setConsumoEnergiaSemanal(res.getDouble(5));
					itemAtivo.setConsumoEnergiaMensal(res.getDouble(6));
					itemAtivo.setConsumoEnergiaAnual(res.getDouble(7));
					itemAtivo.setvalorEmissaoCODiario(res.getDouble(8));
					itemAtivo.setValorEmissaoCOSemanal(res.getDouble(9));
					itemAtivo.setValorEmissaoCOMensal(res.getDouble(10));
					itemAtivo.setValorEmissaoCOAnual(res.getDouble(11));
					itemAtivo.setTipoConsumo(res.getString(12));
					ativoList.add(itemAtivo);
				}
							
			}catch (SQLException e){
				throw new RuntimeException(e);
			}

			return ativoList;
	}
	
	/*
	 * Método insere no banco de dados MySql uma lista de objetos do tipo AtivoTI e seus respectivos atributos
	 */
	public void insereAtivo(ArrayList<AtivoTI> ativoList, int idUsuario) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {

		try {	
			
			String sql = "insert into ativo_ti"
					+ " (nome_ativo, fabricante_ativo, modelo_ativo, categoria_ativo, consumo_energia_ativo, horas_consumo_diario, dias_consumo, tipo_consumo, id_usuario)values (?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			
				for(int i = 0; (i <= ativoList.size() - 1); i++){
				 					
				 	stmt.setString(1, ativoList.get(i).getHostName());
				 	stmt.setString(2, ativoList.get(i).getFabricante());
					stmt.setString(3, ativoList.get(i).getModelo());
					stmt.setString(4, ativoList.get(i).getCategoria());
					stmt.setDouble(5, ativoList.get(i).getConsumoEnergia());
					stmt.setDouble(6, ativoList.get(i).getHorasConsumoDiario());
					stmt.setInt(7, ativoList.get(i).getDiasConsumo());
					stmt.setString(8, ativoList.get(i).getTipoConsumo());
					stmt.setInt(9, idUsuario);
				 	
				 	stmt.execute();
				
				}
			}finally{
				connection.close();
			}
	}
	
	/*
	 * Método atualiza no banco de dados MySql a lista de objetos do tipo AtivoTI com seus respctivos valores de emissão de co²
	 */
	public void atualizaEmissao(ArrayList<AtivoTI> ativoList, int idUsuario) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {

		try {	
			
			String sql = "update ativo_ti set emissao_ativo_diario = ?, "
					+ "emissao_ativo_semanal = ?, "
					+ "emissao_ativo_mensal = ?, "
					+ "emissao_ativo_anual = ? where id_usuario = " + idUsuario + " and id_ativo_ti = ?"; 
			
			PreparedStatement stmt = connection.prepareStatement(sql);			
			
				for(int i = 0; (i <= ativoList.size() - 1); i++){
				 					
				 	stmt.setDouble(1, ativoList.get(i).getValorEmissaoCODiario());
				 	stmt.setDouble(2, ativoList.get(i).getValorEmissaoCOSemanal());
				 	stmt.setDouble(3, ativoList.get(i).getValorEmissaoCOMensal());
				 	stmt.setDouble(4, ativoList.get(i).getValorEmissaoCOAnual());
				 	stmt.setInt(5, ativoList.get(i).getIdAtivo());
				 	stmt.executeUpdate();
				}
				
		}catch (SQLException e){
			throw new RuntimeException(e);
		}finally{
			connection.close();
		}
	}
	
	/*
	 * Método atualiza no banco de dados MySql a lista de objetos do tipo AtivoTI com seus respctivos valores de consumo de energia
	 */	
	public void atualizaConsumo(ArrayList<AtivoTI> ativoList, int idUsuario) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {

		try {	
			
			String sql = "update ativo_ti set consumo_energia_diario = ?, "
					+ "consumo_energia_semanal = ?, "
					+ "consumo_energia_mensal = ?, "
					+ "consumo_energia_anual = ? where id_usuario = " + idUsuario + " and id_ativo_ti = ?"; 
			
			PreparedStatement stmt = connection.prepareStatement(sql);			
			
				for(int i = 0; (i <= ativoList.size() - 1); i++){
				 					
				 	stmt.setDouble(1, ativoList.get(i).getConsumoEnergiaDiario());
				 	stmt.setDouble(2, ativoList.get(i).getConsumoEnergiaSemanal());
				 	stmt.setDouble(3, ativoList.get(i).getConsumoEnergiaMensal());
				 	stmt.setDouble(4, ativoList.get(i).getConsumoEnergiaAnual());
				 	stmt.setInt(5, ativoList.get(i).getIdAtivo());
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
