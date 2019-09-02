package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Model.ModelKey;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class KeyDao {

        Conexao qq = new Conexao();
	
	Connection conect = (Connection) qq.conectar();
	
	public void inserirKey(ModelKey chave) throws SQLException {
		
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
        
        public List<ModelKey> listar() throws SQLException{
            
            ResultSet rs = null;
            
            List<ModelKey> keys = new ArrayList<>();
            
            try {
                String sql = "SELECT * FROM chave";
                PreparedStatement stmt =conect.prepareStatement(sql);
                rs = stmt.executeQuery();
                
                while (rs.next()) {                    
                    ModelKey key =  new ModelKey();
                    key.setNome_sala(rs.getString("nome_sala"));
                    key.setBloco(rs.getString("bloco"));
                    key.setCodigo_chave(rs.getInt("codigo_chave"));
                    key.setStatus(rs.getBoolean("status"));
                    keys.add(key);
                }
                        
            } catch (SQLException e) {
                System.out.println("Erro:"+e.getMessage());
            }finally{
                conect.close();
            }
      
            return keys;
        }
}
