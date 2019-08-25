/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classe.Home;
import com.sun.org.apache.bcel.internal.generic.SWITCH;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Samuel Lima
 */
public class HomeController implements Initializable {

    @FXML
    private Button cadastrar_admin;
    @FXML
    private Button cadastrar_usuario;
    @FXML
    private Button cadastrar_chave;
    @FXML
    private Button devolver_chave;
    @FXML
    private Button emprestar_chave;
    
    private String button;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //
    }    

    
    
    
    
    @FXML
    private void registerKey(javafx.event.ActionEvent event) {
        Home.optionScreen("key");
    }

    @FXML
    private void registerAdmin(javafx.event.ActionEvent event) {
        Home.optionScreen("admin");
    }

    @FXML
    private void registerUser(javafx.event.ActionEvent event) {
        Home.optionScreen("user");
    }
    
    
    
    
    
}
