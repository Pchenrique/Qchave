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

        String sql = "insert into chaves_permitidas (id_usuario,id_chave,data_confirmacao)values (?,?,?)";
        PreparedStatement stmt = conect.prepareStatement(sql);
        stmt.setInt(1, key.getId_usuario());
        stmt.setInt(2, key.getId_chave());
        stmt.setDate(3, key.getData_confirmacao());
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
                ModelKeyPermission key = new ModelKeyPermission(rs.getInt("id_usuario"), rs.getInt("id_chave"));
                key.setId(rs.getInt("id"));
                key.setData_confirmacao(rs.getDate("data_confirmacao"));
                
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
