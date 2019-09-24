/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.AdminDao;
import DAO.EmprestarDAO;
import DAO.KeyDao;
import DAO.UserDAO;
import Model.ModelAdmin;
import Model.ModelEmprestimo;
import Model.ModelKey;
import Model.ModelUser;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Samuel Lima
 */
public class KeyLoanController implements Initializable {

    @FXML
    private TextField cpf_administrador;
    @FXML
    private Button btn_emprestar_chave;
    @FXML
    private TextField nome_usuario;
    
    private static Model.ModelKey selected;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void confirmEmprestimo(ActionEvent event) throws SQLException, Exception {
        
        EmprestarDAO banco = new EmprestarDAO();
        UserDAO banco_userdao = new UserDAO();
        AdminDao banco_admindao = new AdminDao();
        KeyDao banco_key = new KeyDao();
        
        long matricula_user = Long.parseLong(this.nome_usuario.getText());
        String cpf_administrador = this.cpf_administrador.getText();
        
        ModelUser usuario = banco_userdao.buscarUser(matricula_user);
        ModelAdmin admin = banco_admindao.buscarAdmin(cpf_administrador);
        
        ModelEmprestimo emprestimo = new ModelEmprestimo(usuario.getId(), selected.getId(), admin.getId());
        
        banco.inserirEmprestimo(emprestimo);
        
        selected.setStatus(false);
        banco_key.editar(selected, selected.getId());
        
        JOptionPane.showMessageDialog(null, "Chave Emprestada com sucesso!");
    }
    
    public static ModelKey getSelected() {
        return selected;
    }

    public static void setSelected(ModelKey selected) {
        KeyLoanController.selected = selected;
    }
    
}
