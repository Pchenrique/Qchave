package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.User;

public class UserDAO {
Conexao qq = new Conexao();
	
	Connection conect = (Connection) qq.conectar();
	
	public void inserirUser(User user) throws SQLException {
		
		String sql = "insert into user (nome,email,matricula,tipo_user)values (?,?,?,?)";
		PreparedStatement stmt =conect.prepareStatement(sql);
		stmt.setString(1, user.getNome());
		stmt.setString(2, user.getEmail());
		stmt.setLong(3, user.getMatricula());
		stmt.setString(4, user.getTipo_user());
		stmt.execute();
		stmt.close();
		conect.close();
	}
}
