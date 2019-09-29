package Model;

import java.sql.Date;

/**
 *
 * @author Paulo Cesar
 */
public class ModelKeyPermission {
    
    private int id;
    private int id_usuario;
    private int id_chave;
    private Date data_confirmacao;

    public ModelKeyPermission(int id_usuario, int id_chave) {
        this.id_usuario = id_usuario;
        this.id_chave = id_chave;
        this.data_confirmacao = new Date(System.currentTimeMillis());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_chave() {
        return id_chave;
    }

    public void setId_chave(int id_chave) {
        this.id_chave = id_chave;
    }

    public Date getData_confirmacao() {
        return data_confirmacao;
    }

    public void setData_confirmacao(Date data_confirmacao) {
        this.data_confirmacao = data_confirmacao;
    }
    
}
