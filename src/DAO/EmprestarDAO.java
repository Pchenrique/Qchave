/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.ModelEmprestimo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
