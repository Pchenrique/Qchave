/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
/**
 *
 * @author Samuel Lima
 */
public class DashboardController implements Initializable {
    
    
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
    
    @FXML
    void register_key(ActionEvent event) {
        Qchave.optionsScene("key");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
