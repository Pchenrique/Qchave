/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.ModelKeyPermission;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paulo Cesar
 */
public class KeyPermissionDao {
    Conexao qq = new Conexao();

    Connection conect = (Connection) qq.conectar();

    public void inserirPermission(ModelKeyPermission key) throws SQLException {

        String sql = "insert into chaves_permitidas (id_usuario,id_chave,nome_chave,nome_usuario,data_confirmacao)values (?,?,?,?,?)";
        PreparedStatement stmt = conect.prepareStatement(sql);
        stmt.setInt(1, key.getId_usuario());
        stmt.setInt(2, key.getId_chave());
        stmt.setString(3, key.getNome_chave());
        stmt.setString(4, key.getNome_usuario());        
        stmt.setString(5, key.getData_confirmacao());
        stmt.execute();
        stmt.close();
        conect.close();
    }
    
    public List<ModelKeyPermission> listarPorUsuario(int id_user) throws SQLException {

        ResultSet rs = null;

        List<ModelKeyPermission> keys = new ArrayList<>();

        try {
            String sql = "SELECT * FROM chaves_permitidas where id_usuario='"+id_user+"'";
            PreparedStatement stmt = conect.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                ModelKeyPermission key = new ModelKeyPermission(rs.getInt("id_usuario"), rs.getInt("id_chave"), rs.getString("nome_chave"), rs.getString("nome_usuario"));
                key.setId(rs.getInt("id"));
                key.setId_chave(rs.getInt("id_chave"));
                key.setId_usuario(rs.getInt("id_usuario"));
                key.setNome_chave(rs.getString("nome_chave"));
                key.setNome_usuario(rs.getString("nome_usuario"));
                key.setData_confirmacao(rs.getString("data_confirmacao"));
                
                keys.add(key);
            }

        } catch (SQLException e) {
            System.out.println("Erro:" + e.getMessage());
        } finally {
            conect.close();
        }

        return keys;
    }
    
    public void excluir(ModelKeyPermission chavepermitida) throws SQLException {
        String sql = "DELETE FROM chaves_permitidas where id = ?";
        PreparedStatement stmt = conect.prepareStatement(sql);
        stmt.setInt(1, chavepermitida.getId());
        stmt.execute();
        stmt.close();
        conect.close();
    }
    
}
