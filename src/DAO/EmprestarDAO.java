/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.ModelEmprestimo;
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
public class EmprestarDAO {
    Conexao qq = new Conexao();

    Connection conect = (Connection) qq.conectar();
    
    public void inserirEmprestimo(ModelEmprestimo emprestimo) throws SQLException {

        String sql = "insert into emprestimos (id_chave,nome_chave,id_usuario,nome_usuario,id_administrador,nome_admin,data_emprestimo,status)values (?,?,?,?,?,?,?,?)";
        PreparedStatement stmt = conect.prepareStatement(sql);
   
        stmt.setInt(1, emprestimo.getId_chave());
        stmt.setString(2, emprestimo.getNome_chave());
        stmt.setInt(3, emprestimo.getId_user());
        stmt.setString(4, emprestimo.getNome_usuario());
        stmt.setInt(5, emprestimo.getId_admin());
        stmt.setString(6, emprestimo.getNome_admin());
        stmt.setDate(7, emprestimo.getData_emprestimo());
        stmt.setString(8, emprestimo.getStatus());
        stmt.execute();
        stmt.close();
        conect.close();
    }
    
    public void editar(ModelEmprestimo emprestimo, int id) throws SQLException {

        String sql = "update emprestimos set id_chave=?,nome_chave=?,id_usuario=?,nome_usuario=?,id_administrador=?,nome_admin=?,data_emprestimo=?,status=? where id=?";
        PreparedStatement stmt = conect.prepareStatement(sql);
        
        stmt.setInt(1, emprestimo.getId_chave());
        stmt.setString(2, emprestimo.getNome_chave());
        stmt.setInt(3, emprestimo.getId_user());
        stmt.setString(4, emprestimo.getNome_usuario());
        stmt.setInt(5, emprestimo.getId_admin());
        stmt.setString(6, emprestimo.getNome_admin());
        stmt.setDate(7, emprestimo.getData_emprestimo());
        stmt.setString(8, emprestimo.getStatus());
        stmt.setInt(9, id);
        stmt.executeUpdate();
    }
    
    public List<ModelEmprestimo> listar() throws SQLException {

        ResultSet rs = null;

        List<ModelEmprestimo> emprestimos = new ArrayList<>();

        try {
            String sql = "SELECT * FROM emprestimos WHERE status='Emprestada' ORDER BY id DESC";
            PreparedStatement stmt = conect.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                ModelEmprestimo emprestimo = new ModelEmprestimo();
                emprestimo.setId(rs.getInt("id"));
                emprestimo.setId_chave(rs.getInt("id_chave"));
                emprestimo.setId_user(rs.getInt("id_usuario"));
                emprestimo.setId_admin(rs.getInt("id_administrador"));
                emprestimo.setData_emprestimo(rs.getDate("data_emprestimo"));
                emprestimo.setStatus(rs.getString("status"));
                emprestimo.setNome_usuario(rs.getString("nome_usuario"));
                emprestimo.setNome_chave(rs.getString("nome_chave"));
                emprestimo.setNome_admin(rs.getString("nome_admin"));
                emprestimos.add(emprestimo);
            }

        } catch (SQLException e) {
            System.out.println("Erro:" + e.getMessage());
        } finally {
            conect.close();
        }

        return emprestimos;
    }
    public ModelEmprestimo buscarEmprestimo(int id) throws SQLException{
        ResultSet result;
        ModelEmprestimo emprestimo = new ModelEmprestimo();
        try { 
            String sql = "SELECT * FROM usuarios WHERE id='"+id+"'";
            PreparedStatement stmt = conect.prepareStatement(sql);
            result = stmt.executeQuery();
            
            if(result != null && result.next()){
                emprestimo.setId(result.getInt("id"));
                emprestimo.setId_chave(result.getInt("id_chave"));
                emprestimo.setId_user(result.getInt("id_usuario"));
                emprestimo.setId_admin(result.getInt("id_administrador"));
                emprestimo.setStatus(result.getString("status"));
            }
        }catch(Exception e){
            System.out.println("Erro:" + e.getMessage());
        }finally{
            conect.close();
        }
        return emprestimo;
    }
    
}
