package Model;

import java.sql.Date;

public class ModelEmprestimo{
    private int id;
    private int id_user;
    private int id_admin;
    private int id_chave;
    private Date data_emprestimo;
    private String status;
    
    @SuppressWarnings("empty-statement")
     public ModelEmprestimo(int id_user, int id_chave, int id_admin) {
        this.id_user = id_user;
        this.id_chave = id_chave;
        this.id_admin = id_admin;
        this.status = "Emprestada";
        this.data_emprestimo = new Date(System.currentTimeMillis());
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

    public Date getData_emprestimo() {
        return data_emprestimo;
    }

    public void setData_emprestimo(Date data_emprestimo) {
        this.data_emprestimo = data_emprestimo;
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
   
}
