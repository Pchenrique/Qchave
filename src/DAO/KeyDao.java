package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.Key;

public class KeyDao {

Conexao qq = new Conexao();
	
	Connection conect = (Connection) qq.conectar();
	
	public void inserirKey(Key chave) throws SQLException {
		
		String sql = "insert into chave (nome_sala,codigo_chave,bloco,status)values (?,?,?,?)";
		PreparedStatement stmt =conect.prepareStatement(sql);
		stmt.setString(1, chave.getNome_sala());
		stmt.setInt(2, chave.getCodigo_chave());
		stmt.setString(3, chave.getBloco());
		stmt.setBoolean(4, chave.getStatus());
	
		
		stmt.execute();
		stmt.close();
		conect.close();
	}
}
