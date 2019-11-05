package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Model.ModelUser;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    Conexao qq = new Conexao();

    Connection conect = (Connection) qq.conectar();

    public void inserirUser(ModelUser user) throws SQLException {

        String sql = "insert into usuarios (id, nome,email,matricula,tipo_usuario)values (?,?,?,?,?)";
        PreparedStatement stmt = conect.prepareStatement(sql);
        stmt.setInt(1, user.getId());
        stmt.setString(2, user.getNome());
        stmt.setString(3, user.getEmail());
        stmt.setLong(4, user.getMatricula());
        stmt.setString(5, user.getTipo_user());
        stmt.execute();
        stmt.close();
        conect.close();
    }

    public void editar(ModelUser user, int id) throws SQLException {

        String sql = "UPDATE usuarios set nome=?, email=?, matricula=?, tipo_usuario=? where id=?";
        PreparedStatement stmt = conect.prepareStatement(sql);
        stmt.setString(1, user.getNome());
        stmt.setString(2, user.getEmail());
        stmt.setLong(3, user.getMatricula());
        stmt.setString(4, user.getTipo_user());
        stmt.setLong(5, id);
        stmt.executeUpdate();
    }

    public void excluir(ModelUser usuario) throws SQLException {
        String sql = "DELETE FROM usuarios where id = ?";
        PreparedStatement stmt = conect.prepareStatement(sql);
        stmt.setLong(1, usuario.getId());
        stmt.execute();
        stmt.close();
        conect.close();
    }

    public List<ModelUser> listar() throws SQLException {

        ResultSet rs = null;

        List<ModelUser> users = new ArrayList<>();

        try {
            String sql = "SELECT * FROM usuarios ORDER BY id ASC";
            PreparedStatement stmt = conect.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                ModelUser user = new ModelUser();
                user.setId(rs.getInt("id"));
                user.setNome(rs.getString("nome"));
                user.setEmail(rs.getString("email"));
                user.setMatricula(rs.getLong("matricula"));
                user.setTipo_user(rs.getString("tipo_usuario"));
                users.add(user);
            }

        } catch (Exception e) {
            System.out.println("Erro:" + e.getMessage());
        } finally {
            conect.close();
        }

        return users;
    }
    
    public List<ModelUser> listarOrdenado() throws SQLException {

        ResultSet rs = null;

        List<ModelUser> users = new ArrayList<>();

        try {
            String sql = "SELECT * FROM usuarios ORDER BY nome ASC";
            PreparedStatement stmt = conect.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                ModelUser user = new ModelUser();
                user.setId(rs.getInt("id"));
                user.setNome(rs.getString("nome"));
                user.setEmail(rs.getString("email"));
                user.setMatricula(rs.getLong("matricula"));
                user.setTipo_user(rs.getString("tipo_usuario"));
                users.add(user);
            }

        } catch (Exception e) {
            System.out.println("Erro:" + e.getMessage());
        } finally {
            conect.close();
        }

        return users;
    }
    
    public List<ModelUser> listarServidor() throws SQLException {

        ResultSet rs = null;

        List<ModelUser> users = new ArrayList<>();

        try {
            String sql = "SELECT * FROM usuarios WHERE tipo_usuario = 'Servidor' ORDER BY nome ASC";
            PreparedStatement stmt = conect.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                ModelUser user = new ModelUser();
                user.setId(rs.getInt("id"));
                user.setNome(rs.getString("nome"));
                user.setEmail(rs.getString("email"));
                user.setMatricula(rs.getLong("matricula"));
                user.setTipo_user(rs.getString("tipo_usuario"));
                users.add(user);
            }

        } catch (Exception e) {
            System.out.println("Erro:" + e.getMessage());
        } finally {
            conect.close();
        }

        return users;
    }
    
    public ModelUser buscarUser(long matricula) throws SQLException{
        ResultSet result;
        ModelUser user = new ModelUser();
        try { 
            String sql = "SELECT * FROM usuarios WHERE matricula='"+matricula+"'";
            PreparedStatement stmt = conect.prepareStatement(sql);
            result = stmt.executeQuery();
            
            if(result != null && result.next()){
                user.setId(result.getInt("id"));
                user.setNome(result.getString("nome"));
                user.setEmail(result.getString("email"));
                user.setMatricula(result.getLong("matricula"));
                user.setTipo_user(result.getString("tipo_usuario"));
            }
        }catch(Exception e){
            System.out.println("Erro:" + e.getMessage());
        }finally{
            conect.close();
        }
        return user;
    }
    public ModelUser buscarUser(int id) throws SQLException{
        ResultSet result;
        ModelUser user = new ModelUser();
        try { 
            String sql = "SELECT * FROM usuarios WHERE id='"+id+"'";
            PreparedStatement stmt = conect.prepareStatement(sql);
            result = stmt.executeQuery();
            
            if(result != null && result.next()){
                user.setId(result.getInt("id"));
                user.setNome(result.getString("nome"));
                user.setEmail(result.getString("email"));
                user.setMatricula(result.getLong("matricula"));
                user.setTipo_user(result.getString("tipo_usuario"));
            }
        }catch(Exception e){
            System.out.println("Erro:" + e.getMessage());
        }finally{
            conect.close();
        }
        return user;
    }
}
