/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void backPage(ActionEvent event) {
    }

    @FXML
    private void confirmDevolucao(ActionEvent event) {
        
    }

   
}
