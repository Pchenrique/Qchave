/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


import DAO.AdminDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import beans.Admin;
/**
 * FXML Controller class
 *
 * @author Samuel Lima
 */
public class AdminController implements Initializable {

    @FXML
    private TextField nome_completo;
    @FXML
    private TextField cpf;
    @FXML
    private Button btn_cadastrar_admin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void register_admin(javafx.event.ActionEvent event) throws SQLException {
    	String name = this.nome_completo.getText();
    	String cpf = this.cpf.getText();
    
    	Admin admin = new Admin(name, cpf);
    	AdminDao admindao = new AdminDao();
    	
    	admindao.inserirAdmin(admin);
    	
    }
    
}
