/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

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

/**
 * FXML Controller class
 *
 * @author Samuel Lima
 */
public class RegisterAdminController implements Initializable {

    @FXML
    private TextField nome_completo;
    @FXML
    private TextField cpf;
    @FXML
    private Button btn_cadastrar_admin;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void registrarAdmin(ActionEvent event) throws SQLException {
        String name = this.nome_completo.getText();
    	String cpf = this.cpf.getText();
    
    	ModelAdmin admin = new ModelAdmin(name, cpf);
    	AdminDao admindao = new AdminDao();
    	
    	admindao.inserirAdmin(admin);
    }
    
}
