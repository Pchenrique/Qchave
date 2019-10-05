package Model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Paulo Cesar
 */
public class ModelKeyPermission {
    
    private int id;
    private int id_usuario;
    private int id_chave;
    private String nome_chave;
    private String nome_usuario;
    private String data_confirmacao;

    public ModelKeyPermission(int id_usuario, int id_chave, String nome_chave, String nome_usuario) {
        this.id_usuario = id_usuario;
        this.id_chave = id_chave;
        this.nome_chave = nome_chave;
        this.nome_usuario = nome_usuario;
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        this.data_confirmacao = formatterHora.format(agora);
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

    public String getNome_chave() {
        return nome_chave;
    }

    public void setNome_chave(String nome_chave) {
        this.nome_chave = nome_chave;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public String getData_confirmacao() {
        return data_confirmacao;
    }

    public void setData_confirmacao(String data_confirmacao) {
        this.data_confirmacao = data_confirmacao;
    }
    
}
