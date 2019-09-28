/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Classe.KeyReturn;
import Classe.Loan;
import DAO.AdminDao;
import DAO.DevolucaoDao;
import DAO.EmprestarDAO;
import DAO.KeyDao;
import DAO.UserDAO;
import Model.ModelAdmin;
import Model.ModelDevolucao;
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
public class KeyReturnController implements Initializable {

    @FXML
    private TextField cpf_administrador;
    @FXML
    private Button btn_emprestar_chave;
    @FXML
    private Label label_Nome_Responsavel;
    @FXML
    private Label label_Nome_Chave;
    
    private static Model.ModelEmprestimo selected;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
             KeyDao banco_key = new KeyDao();
            UserDAO banco_user = new UserDAO();
            ModelKey key = banco_key.buscarKey(selected.getId_chave());
            ModelUser user = banco_user.buscarUser(selected.getId_user());
            
            this.label_Nome_Chave.setText(key.getNome_sala());
            this.label_Nome_Responsavel.setText(user.getNome());
        } catch (SQLException ex) {
            Logger.getLogger(KeyReturnController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }    

    @FXML
    private void backPage(ActionEvent event) {
    }

    @FXML
    private void confirmDevolucao(ActionEvent event) throws SQLException {
        DevolucaoDao banco = new DevolucaoDao();
        AdminDao banco_admindao = new AdminDao();
        EmprestarDAO banco_emprestar = new EmprestarDAO();
        
        ModelAdmin admin = banco_admindao.buscarAdmin(this.cpf_administrador.getText());
        
        ModelDevolucao devolucao = new ModelDevolucao(admin.getId(), selected.getId());
        banco.inserirDevolucao(devolucao);
        
      
        selected.setStatus("Encerrado");
        banco_emprestar.editar(selected, selected.getId());
        
        KeyDao banco_keydao = new KeyDao();
        ModelKey key = banco_keydao.buscarKey(selected.getId_chave());
        key.setStatus("Disponivel");
        
        KeyDao banco_keydao2 = new KeyDao();
        banco_keydao2.editar(key, key.getId());
        
        JOptionPane.showMessageDialog(null, "Chave Devolvida com Sucesso!");
        
        KeyReturn.getStage().close();
        Loan newFrame = new Loan();
        try {
            newFrame.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(KeyReturnController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ModelEmprestimo getSelected() {
        return selected;
    }

    public static void setSelected(ModelEmprestimo selected) {
       KeyReturnController.selected = selected;
    }
   
}
