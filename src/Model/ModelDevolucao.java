package Model;

import java.sql.Date;
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

    public ModelDevolucao(int id_admin, int id_emprestimo) {
        this.id_admin = id_admin;
        this.id_emprestimo = id_emprestimo;
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

    public String getData_devolucao() {
        return data_devolucao;
    }

    public void setData_devolucao(String data_devolucao) {
        this.data_devolucao = data_devolucao;
    }
    
}
