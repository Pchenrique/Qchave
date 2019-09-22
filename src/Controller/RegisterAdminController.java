/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

//Importação do Admin DAO e do Model Admin.
import Classe.Admin;
import Classe.RegisterAdmin;
import DAO.AdminDao;
import Model.ModelAdmin;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Samuel Lima
 */
public class RegisterAdminController implements Initializable {

    //Ids dos campos de textfield e botão de cadastrar administrador.
    @FXML
    private TextField nome_completo;
    @FXML
    private TextField cpf;
    @FXML
    private Button btn_cadastrar_admin;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    //Método de registrar administrador.
    @FXML
    private void registrarAdmin(ActionEvent event) throws SQLException, Exception {
        String name = this.nome_completo.getText();
        String cpf = this.cpf.getText();
        int id = 0;

        ModelAdmin admin = new ModelAdmin(name, cpf);
        AdminDao admindao = new AdminDao();
        admindao.inserirAdmin(admin);

        JOptionPane.showMessageDialog(null, "Dados cadastrados com sucesso!");
        
        RegisterAdmin.getStage().close();
        
        Admin newFrame = new Admin();
        newFrame.start(new Stage());
    }

    @FXML
    private void backPage(ActionEvent event) throws Exception {
        RegisterAdmin.getStage().close();
        
        Admin newFrame = new Admin();
        newFrame.start(new Stage());
    }
}
