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

        String sql = "insert into chaves (nome_sala,bloco,status)values (?,?,?)";
        PreparedStatement stmt = conect.prepareStatement(sql);
        stmt.setString(1, chave.getNome_sala());
        stmt.setString(2, chave.getBloco());
        stmt.setString(3, chave.getStatus());
        stmt.execute();
        stmt.close();
        conect.close();
    }

    public void editar(ModelKey chave, int id) throws SQLException {

        String sql = "update chaves set nome_sala=?, bloco=?, status=? where id=?";
        PreparedStatement stmt = conect.prepareStatement(sql);
        stmt.setString(1, chave.getNome_sala());
        stmt.setString(2, chave.getBloco());
        stmt.setString(3, chave.getStatus());
        stmt.setInt(4, id);
        stmt.executeUpdate();
    }

    public void excluir(ModelKey chave) throws SQLException {
        String sql = "DELETE FROM chaves where id = ?";
        PreparedStatement stmt = conect.prepareStatement(sql);
        stmt.setInt(1, chave.getId());
        stmt.execute();
        stmt.close();
        conect.close();
    }

    public List<ModelKey> listar() throws SQLException {

        ResultSet rs = null;

        List<ModelKey> keys = new ArrayList<>();

        try {
            String sql = "SELECT * FROM chaves";
            PreparedStatement stmt = conect.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                ModelKey key = new ModelKey();
                key.setId(rs.getInt("id"));
                key.setNome_sala(rs.getString("nome_sala"));
                key.setBloco(rs.getString("bloco"));
                key.setStatus(rs.getString("status"));
                keys.add(key);
            }

        } catch (SQLException e) {
            System.out.println("Erro:" + e.getMessage());
        } finally {
            conect.close();
        }

        return keys;
    }
    
    public List<ModelKey> chavesDisponivel() throws SQLException {

        ResultSet rs = null;

        List<ModelKey> keys = new ArrayList<>();

        try {
            String sql = "SELECT * FROM chaves where status = 'Disponivel'";
            PreparedStatement stmt = conect.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                ModelKey key = new ModelKey();
                key.setId(rs.getInt("id"));
                keys.add(key);
            }

        } catch (SQLException e) {
            System.out.println("Erro:" + e.getMessage());
        } finally {
            conect.close();
        }

        return keys;
    }
    
    public ModelKey buscarKey(int id) throws SQLException{
        ResultSet result = null;
        ModelKey key = new ModelKey();
        
        try{
            String sql = "SELECT * FROM chaves where id='"+id+"'";
            PreparedStatement stmt = conect.prepareStatement(sql);
            result = stmt.executeQuery();

             if(result != null && result.next()){
                key.setId(result.getInt("id"));
                key.setNome_sala(result.getString("nome_sala"));
                key.setBloco(result.getString("bloco"));
                key.setStatus(result.getString("status"));
             }
         
        } catch (SQLException e) {
            System.out.println("Erro:" + e.getMessage());
        } finally {
            conect.close();
        }
 
        return key;
    }
}
