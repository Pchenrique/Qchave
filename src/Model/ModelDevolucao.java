package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Paulo Cesar
 */
public class ModelDevolucao {
    private int id;
    private int id_admin;
    private int id_emprestimo;
    private String data_devolucao;
    private String nome_usuario;
    private String nome_chave;
    private String nome_admin;

    public ModelDevolucao(int id_admin, int id_emprestimo, String nome_usuario, String nome_chave, String nome_admin) {
        this.id_admin = id_admin;
        this.id_emprestimo = id_emprestimo;
        this.nome_usuario = nome_usuario;
        this.nome_chave = nome_chave;
        this.nome_admin = nome_admin;
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        this.data_devolucao = formatterHora.format(agora);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public int getId_emprestimo() {
        return id_emprestimo;
    }

    public void setId_emprestimo(int id_emprestimo) {
        this.id_emprestimo = id_emprestimo;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public String getNome_chave() {
        return nome_chave;
    }

    public void setNome_chave(String nome_chave) {
        this.nome_chave = nome_chave;
    }

    public String getNome_admin() {
        return nome_admin;
    }

    public void setNome_admin(String nome_admin) {
        this.nome_admin = nome_admin;
    }

    public String getData_devolucao() {
        return data_devolucao;
    }

    public void setData_devolucao(String data_devolucao) {
        this.data_devolucao = data_devolucao;
    }
    
}
