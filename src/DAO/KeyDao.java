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
        stmt.setBoolean(3, chave.getStatus());
        stmt.execute();
        stmt.close();
        conect.close();
    }

    public void editar(ModelKey chave, int id) throws SQLException {

        String sql = "update chaves set nome_sala=?, bloco=? where id=?";
        PreparedStatement stmt = conect.prepareStatement(sql);
        stmt.setString(1, chave.getNome_sala());
        stmt.setString(2, chave.getBloco());
        stmt.setInt(3, id);
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
                key.setStatus(rs.getBoolean("status"));
                keys.add(key);
            }

        } catch (SQLException e) {
            System.out.println("Erro:" + e.getMessage());
        } finally {
            conect.close();
        }

        return keys;
    }
}
