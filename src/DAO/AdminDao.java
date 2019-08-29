package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.Admin;


public class AdminDao{
	
	Conexao qq = new Conexao();
	
	Connection conect = (Connection) qq.conectar();
	
	public void inserirAdmin(Admin admin) throws SQLException {
		
		String sql = "insert into admin (nome,cpf)values (?,?)";
		PreparedStatement stmt =conect.prepareStatement(sql);
		stmt.setString(1, admin.getNome());
		stmt.setString(2, admin.getCpf());
		
		stmt.execute();
		stmt.close();
		conect.close();
	}
	
	public void excluirAdmin(int id) throws SQLException {
		String sql = "DELETE FROM admin where admin.id = ?";
		PreparedStatement stmt =conect.prepareStatement(sql);
		
		stmt.setInt(1, id);
		stmt.execute();
		stmt.close();
		conect.close();
	}
	public void listar() throws SQLException {
		Conexao conectar = new Conexao();
		Connection conect = (Connection) conectar.conectar(); 
		
		Connection connection =(Connection) conectar.conectar();
		String  sql = "select * from admin";
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			String nome = rs.getString("nome");
			String cpf = rs.getString("cpf");
			
			System.out.println(nome+"-"+cpf+"");
	}
}
}