package Model;

import java.sql.Date;

/**
 *
 * @author Paulo Cesar
 */
public class ModelDevolucao {
    private int id;
    private int id_admin;
    private int id_emprestimo;
    private Date data_devolucao;

    public ModelDevolucao(int id_admin, int id_emprestimo) {
        this.id_admin = id_admin;
        this.id_emprestimo = id_emprestimo;
        this.data_devolucao = new Date(System.currentTimeMillis());
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

    public Date getData_devolucao() {
        return data_devolucao;
    }

    public void setData_devolucao(Date data_devolucao) {
        this.data_devolucao = data_devolucao;
    }
    
}
