package DAO;

import Model.ModelReservas;
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
public class ReservaDao {
    Conexao qq = new Conexao();

    Connection conect = (Connection) qq.conectar();
    
    public void inserirUser(ModelReservas reserva) throws SQLException {

        String sql = "insert into reservas (id_usuario,nome_usuario,id_chave,nome_chave,id_admin,nome_admin,data_saida, hora_saida,data_devolucao,hora_devolucao,status)values (?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stmt = conect.prepareStatement(sql);
        stmt.setInt(1, reserva.getId_usuario());
        stmt.setString(2, reserva.getNome_usuario());
        stmt.setInt(3, reserva.getId_chave());
        stmt.setString(4, reserva.getNome_chave());
        stmt.setInt(5, reserva.getId_admin());
        stmt.setString(6, reserva.getNome_admin());
        stmt.setString(7, reserva.getData_saida());
        stmt.setString(8, reserva.getHora_saida());
        stmt.setString(9, reserva.getData_devolucao());
        stmt.setString(10, reserva.getHora_devolucao());
        stmt.setString(11, reserva.getStatus());
        stmt.execute();
        stmt.close();
        conect.close();
    }
    
     public void editar(ModelReservas reserva, int id) throws SQLException {

        String sql = "update reservas set id_chave=?,nome_chave=?,id_usuario=?,nome_usuario=?,id_admin=?,nome_admin=?,data_saida=?,hora_saida=?,data_devolucao=?,hora_devolucao=?,status=? where id=?";
        PreparedStatement stmt = conect.prepareStatement(sql);
        
        stmt.setInt(1, reserva.getId_chave());
        stmt.setString(2, reserva.getNome_chave());
        stmt.setInt(3, reserva.getId_usuario());
        stmt.setString(4, reserva.getNome_usuario());
        stmt.setInt(5, reserva.getId_admin());
        stmt.setString(6, reserva.getNome_admin());
        stmt.setString(7, reserva.getData_saida());
        stmt.setString(8, reserva.getHora_saida());
        stmt.setString(9, reserva.getData_devolucao());
        stmt.setString(10, reserva.getHora_devolucao());
        stmt.setString(11, reserva.getStatus());
        stmt.setInt(12, id);
        stmt.executeUpdate();
    }
    
    public List<ModelReservas> listaDisponivel() throws SQLException {

        ResultSet rs = null;

        List<ModelReservas> reservas = new ArrayList<>();

        try {
            String sql = "SELECT * FROM reservas where status = 'Ativa' ORDER BY id DESC";
            PreparedStatement stmt = conect.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                ModelReservas reserva = new ModelReservas();
                reserva.setId(rs.getInt("id"));
                reserva.setId_usuario(rs.getInt("id_usuario"));
                reserva.setNome_usuario(rs.getString("nome_usuario"));
                reserva.setId_chave(rs.getInt("id_chave"));
                reserva.setNome_chave(rs.getString("nome_chave"));
                reserva.setId_admin(rs.getInt("id_admin"));
                reserva.setNome_admin(rs.getString("nome_admin"));
                reserva.setData_saida(rs.getString("data_saida"));
                reserva.setHora_saida(rs.getString("hora_saida"));
                reserva.setData_devolucao(rs.getString("data_devolucao"));
                reserva.setHora_devolucao(rs.getString("hora_devolucao"));
                reserva.setStatus(rs.getString("status"));
                
                reservas.add(reserva);
            }

        } catch (SQLException e) {
            System.out.println("Erro:" + e.getMessage());
        } finally {
            conect.close();
        }

        return reservas;
    }
    
    public List<ModelReservas> lista() throws SQLException {

        ResultSet rs = null;

        List<ModelReservas> reservas = new ArrayList<>();

        try {
            String sql = "SELECT * FROM reservas ORDER BY id DESC";
            PreparedStatement stmt = conect.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                ModelReservas reserva = new ModelReservas();
                reserva.setId(rs.getInt("id"));
                reserva.setId_usuario(rs.getInt("id_usuario"));
                reserva.setNome_usuario(rs.getString("nome_usuario"));
                reserva.setId_chave(rs.getInt("id_chave"));
                reserva.setNome_chave(rs.getString("nome_chave"));
                reserva.setId_admin(rs.getInt("id_admin"));
                reserva.setNome_admin(rs.getString("nome_admin"));
                reserva.setData_saida(rs.getString("data_saida"));
                reserva.setHora_saida(rs.getString("hora_saida"));
                reserva.setData_devolucao(rs.getString("data_devolucao"));
                reserva.setHora_devolucao(rs.getString("hora_devolucao"));
                reserva.setStatus(rs.getString("status"));
                
                reservas.add(reserva);
            }

        } catch (SQLException e) {
            System.out.println("Erro:" + e.getMessage());
        } finally {
            conect.close();
        }

        return reservas;
    }
}


