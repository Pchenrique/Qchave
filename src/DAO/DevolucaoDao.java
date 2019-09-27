package DAO;

import Model.ModelDevolucao;
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
    
     public List<ModelDevolucao> listar() throws SQLException {

        ResultSet rs = null;

        List<ModelDevolucao> devolucoes = new ArrayList<>();

        try {
            String sql = "SELECT * FROM devolucoes";
            PreparedStatement stmt = conect.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                ModelDevolucao dev = new ModelDevolucao(rs.getInt("id_administrador"), rs.getInt("id_emprestimo"));
                dev.setId(rs.getInt("id"));
                devolucoes.add(dev);
            }

        } catch (SQLException e) {
            System.out.println("Erro:" + e.getMessage());
        } finally {
            conect.close();
        }

        return devolucoes;
    }
}
