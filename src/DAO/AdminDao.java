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

        String sql = "insert into admin (nome,cpf)values (?,?)";
        PreparedStatement stmt = conect.prepareStatement(sql);
        stmt.setString(1, admin.getNome());
        stmt.setString(2, admin.getCpf());
        stmt.execute();
        stmt.close();
        conect.close();
    }

    public void editar(ModelAdmin admin) throws SQLException {

        String sql = "update admin set nome=?, cpf=? where cpf=?";
        PreparedStatement stmt = conect.prepareStatement(sql);
        stmt.setString(1, admin.getNome());
        stmt.setString(2, admin.getCpf());
        stmt.setString(3, admin.getCpf());
        stmt.executeUpdate();
    }

    public void excluir(ModelAdmin admin) throws SQLException {
        String sql = "DELETE FROM admin cpf=?";
        PreparedStatement stmt = conect.prepareStatement(sql);
        stmt.setString(1, admin.toString());
        stmt.execute();
        stmt.close();
        conect.close();
    }

    public List<ModelAdmin> listar() throws SQLException {

        ResultSet rs = null;

        List<ModelAdmin> admins = new ArrayList<>();

        try {
            String sql = "SELECT * FROM admin";
            PreparedStatement stmt = conect.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                ModelAdmin admin = new ModelAdmin();
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
}
