package DAO;

import Model.ModelDevolucao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Paulo Cesar
 */
public class DevolucaoDao {
    Conexao qq = new Conexao();

    Connection conect = (Connection) qq.conectar();
    
    public void inserirDevolucao(ModelDevolucao devolucao) throws SQLException {

        String sql = "insert into devolucoes(data_devolucao,id_emprestimo,id_administrador) values(?,?,?)";
        PreparedStatement stmt = conect.prepareStatement(sql); 
        stmt.setDate(1, devolucao.getData_devolucao());
        stmt.setInt(2, devolucao.getId_emprestimo());
        stmt.setInt(3, devolucao.getId_admin());
        stmt.execute();
        stmt.close();
        conect.close();
    }
    
}
