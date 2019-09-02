package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Model.ModelUser;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
Conexao qq = new Conexao();
	
	Connection conect = (Connection) qq.conectar();
	
	public void inserirUser(ModelUser user) throws SQLException {
		
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
        
        public List<ModelUser> listar() throws SQLException{
            
            ResultSet rs = null;
            
            List<ModelUser> users = new ArrayList<>();
            
            try {
                String sql = "SELECT * FROM user";
                PreparedStatement stmt =conect.prepareStatement(sql);
                rs = stmt.executeQuery();
                
                while (rs.next()) {                    
                    ModelUser user =  new ModelUser();
                    user.setNome(rs.getString("nome"));
                    user.setEmail(rs.getString("email"));
                    user.setMatricula(rs.getLong("matricula"));
                    user.setTipo_user(rs.getString("tipo_user"));
                    users.add(user);
                }
                        
            } catch (Exception e) {
                System.out.println("Erro:"+e.getMessage());
            }finally{
                conect.close();
            }
      
            return users;
        }
}
