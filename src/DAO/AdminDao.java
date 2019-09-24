package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.ModelAdmin;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {

    Conexao qq = new Conexao();

    Connection conect = (Connection) qq.conectar();

    public void inserirAdmin(ModelAdmin admin) throws SQLException {

        String sql = "insert into administradores (id,nome,cpf)values (?,?,?)";
        PreparedStatement stmt = conect.prepareStatement(sql);
        stmt.setInt(1, admin.getId());
        stmt.setString(2, admin.getNome());
        stmt.setString(3, admin.getCpf());
        stmt.execute();
        stmt.close();
        conect.close();
    }

    public void editar(ModelAdmin admin, int id) throws SQLException {

        String sql = "UPDATE administradores set nome=?, cpf=? where id=?";
        PreparedStatement stmt = conect.prepareStatement(sql);
        stmt.setString(1, admin.getNome());
        stmt.setString(2, admin.getCpf());
        stmt.setInt(3, id);
        stmt.executeUpdate();
    }

    public void excluir(ModelAdmin admin) throws SQLException {
        String sql = "DELETE FROM administradores where id=?";
        PreparedStatement stmt = conect.prepareStatement(sql);
        stmt.setInt(1, admin.getId());
        stmt.execute();
        stmt.close();
        conect.close();
    }

    public List<ModelAdmin> listar() throws SQLException {

        ResultSet rs = null;

        List<ModelAdmin> admins = new ArrayList<>();

        try {
            String sql = "SELECT * FROM administradores";
            PreparedStatement stmt = conect.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                ModelAdmin admin = new ModelAdmin();
                admin.setId(rs.getInt("id"));
                admin.setNome(rs.getString("nome"));
                admin.setCpf(rs.getString("cpf"));
                admins.add(admin);
            }

        } catch (SQLException e) {
            System.out.println("Erro:" + e.getMessage());
        } finally {
            conect.close();
        }

        return admins;
    }
    
    public ModelAdmin buscarAdmin(String cpf) throws SQLException{
        ResultSet result = null;
        ModelAdmin admin = new ModelAdmin();
        
        try{
            String sql = "SELECT * FROM administradores where cpf='"+cpf+"'";
            PreparedStatement stmt = conect.prepareStatement(sql);
            result = stmt.executeQuery();

             if(result != null && result.next()){
                admin.setId(result.getInt("id"));
                admin.setNome(result.getString("nome"));
                admin.setCpf(result.getString("cpf"));
             }
         
        } catch (SQLException e) {
            System.out.println("Erro:" + e.getMessage());
        } finally {
            conect.close();
        }
 
        return admin;
    }
}
