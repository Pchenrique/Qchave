/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Classe.EditKey;
import Classe.Key;
import DAO.AdminDao;
import DAO.EmprestarDAO;
import DAO.UserDAO;
import Model.ModelAdmin;
import Model.ModelEmprestimo;
import Model.ModelKey;
import Model.ModelUser;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
    
    private ModelKey selected;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void confirmEmprestimo(ActionEvent event) throws SQLException, Exception {
        System.out.println("esta imprimindo");
        EmprestarDAO banco = new EmprestarDAO();
        UserDAO banco_userdao = new UserDAO();
        AdminDao banco_admindao = new AdminDao();
        
        long matricula_user = Long.parseLong(this.nome_usuario.getText());
        String cpf_administrador = this.cpf_administrador.getText();
        
        ModelUser usuario = banco_userdao.buscarUser(matricula_user);
        ModelAdmin admin = banco_admindao.buscarAdmin(cpf_administrador);
        
        ModelEmprestimo emprestimo = new ModelEmprestimo(usuario.getId(), 3, admin.getId());
        
        banco.inserirEmprestimo(emprestimo);
        
        JOptionPane.showMessageDialog(null, "Chave Emprestada!");
    }
    
}
