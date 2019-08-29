/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Classe.Admin;

import Classe.Key;
import Classe.User;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //
    }    

    
    //Métodos dos botões da home
    
    @FXML
    private void registerKey(javafx.event.ActionEvent event) throws Exception {
       Key chave = new Key();
       
       try {
    	   chave.start(new Stage());
       } catch (Exception ex) {
    	   Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
       }
       
    }

    @FXML
    private void registerAdmin(javafx.event.ActionEvent event) {
        Admin admin = new Admin();
        try {
            admin.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void registerUser(javafx.event.ActionEvent event) {
        User user = new User();
        try {
            user.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
    
    
}
