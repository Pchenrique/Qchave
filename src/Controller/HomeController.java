/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

//Importação das classes admin, user e key.
import Classe.Admin;
import Classe.Home;
import Classe.Key;
import Classe.Loan;
import Classe.User;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Samuel Lima
 */
public class HomeController implements Initializable {

    @FXML
    private Button devolver_chave;
    @FXML
    private Button emprestar_chave;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //
    }

    //Métodos dos botões da home.
    //Chama a tela do keyPage.
    @FXML
    private void keyPage(javafx.event.ActionEvent event) throws Exception {
        Key chave = new Key();
        
        try {
            chave.start(new Stage());
            Home.getStage().close();
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Chama a tela do adminPage.
    @FXML
    private void AdminPage(javafx.event.ActionEvent event) {
        Admin admin = new Admin();
        try {
            admin.start(new Stage());
            Home.getStage().close();
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Chama a tela do userPage.
    @FXML
    private void UserPage(javafx.event.ActionEvent event) {
        User user = new User();
        try {
            user.start(new Stage());
            Home.getStage().close();
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void EmprestimosPage(ActionEvent event) {
        Loan loan = new Loan();
        
        try {
            loan.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
