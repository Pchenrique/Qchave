/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.ModelEmprestimo;
import Model.ModelKey;
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

        String sql = "insert into emprestimos (id_chave,id_usuario,id_administrador,data_emprestimo)values (?,?,?,?)";
        PreparedStatement stmt = conect.prepareStatement(sql);
   
        stmt.setInt(1, emprestimo.getId_chave());
        stmt.setInt(2, emprestimo.getId_user());
        stmt.setInt(3, emprestimo.getId_admin());
        stmt.setDate(4, emprestimo.getData_emprestimo());
        stmt.execute();
        stmt.close();
        conect.close();
    }
    
    public List<ModelEmprestimo> listar() throws SQLException {

        ResultSet rs = null;

        List<ModelEmprestimo> emprestimos = new ArrayList<>();

        try {
            String sql = "SELECT * FROM emprestimos";
            PreparedStatement stmt = conect.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                ModelEmprestimo emprestimo = new ModelEmprestimo();
                emprestimo.setId_chave(rs.getInt("id_chave"));
                emprestimo.setId_user(rs.getInt("id_usuario"));
                emprestimo.setId_admin(rs.getInt("id_administrador"));
                emprestimos.add(emprestimo);
            }

        } catch (SQLException e) {
            System.out.println("Erro:" + e.getMessage());
        } finally {
            conect.close();
        }

        return emprestimos;
    }
}
