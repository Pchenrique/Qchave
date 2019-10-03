package Model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ModelEmprestimo{
    private int id;
    private int id_user;
    private int id_admin;
    private int id_chave;
    private String horaFormatada;
    private String status;
    private String nome_usuario;
    private String nome_chave;
    private String nome_admin;
    
    @SuppressWarnings("empty-statement")
     public ModelEmprestimo(int id_user, String nome_usuario, int id_chave, String nome_chave ,int id_admin, String nome_admin) {
        this.id_user = id_user;
        this.id_chave = id_chave;
        this.id_admin = id_admin;
        this.status = "Emprestada";
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        this.horaFormatada = formatterHora.format(agora);
        this.nome_usuario = nome_usuario;
        this.nome_chave = nome_chave;
        this.nome_admin = nome_admin;
    }
    
    public ModelEmprestimo(){
        
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public int getId_chave() {
        return id_chave;
    }

    public void setId_chave(int id_chave) {
        this.id_chave = id_chave;
    }

    public String getData_emprestimo() {
        return horaFormatada;
    }

    public void setData_emprestimo(String data_emprestimo) {
        this.horaFormatada = data_emprestimo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
   
}
