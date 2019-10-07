package Model;

import static jdk.nashorn.internal.runtime.Debug.id;

/**
 *
 * @author Paulo Cesar
 */
public class ModelReservas {
    private int id;
    private int id_usuario;
    private String nome_usuario;
    private int id_chave;
    private String nome_chave;
    private int id_admin;
    private String nome_admin;
    private String data_saida;
    private String hora_saida;
    private String data_devolucao;
    private String hora_devolucao;
    private String status;

    public ModelReservas(int id_usuario, String nome_usuario, int id_chave, String nome_chave, int id_admin, String nome_admin, String data_saida, String hora_saida, String data_devolucao, String hora_devolucao) {
        this.id_usuario = id_usuario;
        this.nome_usuario = nome_usuario;
        this.id_chave = id_chave;
        this.nome_chave = nome_chave;
        this.id_admin = id_admin;
        this.nome_admin = nome_admin;
        this.data_saida = data_saida;
        this.hora_saida = hora_saida;
        this.data_devolucao = data_devolucao;
        this.hora_devolucao = hora_devolucao;
        this.status = "Ativa";
    }
    
    public ModelReservas(){
        
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

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
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

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public String getNome_admin() {
        return nome_admin;
    }

    public void setNome_admin(String nome_admin) {
        this.nome_admin = nome_admin;
    }

    public String getData_saida() {
        return data_saida;
    }

    public void setData_saida(String data_saida) {
        this.data_saida = data_saida;
    }

    public String getData_devolucao() {
        return data_devolucao;
    }

    public void setData_devolucao(String data_devolucao) {
        this.data_devolucao = data_devolucao;
    }

    public String getHora_saida() {
        return hora_saida;
    }

    public void setHora_saida(String hora_saida) {
        this.hora_saida = hora_saida;
    }

    public String getHora_devolucao() {
        return hora_devolucao;
    }

    public void setHora_devolucao(String hora_devolucao) {
        this.hora_devolucao = hora_devolucao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
 
}
