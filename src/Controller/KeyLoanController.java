/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Classe.Key;
import Classe.KeyLoan;
import Classe.Loan;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private TextField nome_usuario;

    private static Model.ModelKey selected;
    
    @FXML
    private Button btn_emprestar_chave;
    @FXML
    private Label Label_Id_Chave;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }
    
    @FXML
    private void confirmEmprestimo(ActionEvent event) throws SQLException {
        EmprestarDAO banco = new EmprestarDAO();
        UserDAO banco_userdao = new UserDAO();
        AdminDao banco_admindao = new AdminDao();
        KeyDao banco_key = new KeyDao();

        long matricula_user = Long.parseLong(this.nome_usuario.getText());
        String cpf_administrador = this.cpf_administrador.getText();

        ModelUser usuario = banco_userdao.buscarUser(matricula_user);
        ModelAdmin admin = banco_admindao.buscarAdmin(cpf_administrador);

        ModelEmprestimo emprestimo = new ModelEmprestimo(usuario.getId(), usuario.getNome(), selected.getId(), selected.getNome_sala(), admin.getId(), admin.getNome());
        banco.inserirEmprestimo(emprestimo);

        selected.setStatus("Emprestada");
        banco_key.editar(selected, selected.getId());

        JOptionPane.showMessageDialog(null, "Chave Emprestada com sucesso!");
        
        KeyLoan.getStage().close();
        Key newFrame = new Key();
        try {
            newFrame.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(KeyLoanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ModelKey getSelected() {
        return selected;
    }

    public static void setSelected(ModelKey selected) {
       KeyLoanController.selected = selected;
    }

    @FXML
    private void backPage(ActionEvent event) throws Exception {
        KeyLoan.getStage().close();

        Key newFrame = new Key();
        newFrame.start(new Stage());
    }
}
