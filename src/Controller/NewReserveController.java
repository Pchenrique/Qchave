/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Classe.NewReserve;
import Classe.Reserve;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Samuel Lima
 */
public class NewReserveController implements Initializable {

    @FXML
    private ComboBox<?> select_servidor;
    @FXML
    private ComboBox<?> select_chave;
    @FXML
    private DatePicker data_saida;
    @FXML
    private DatePicker data_entrada;
    @FXML
    private TextField hora_saida;
    @FXML
    private TextField hora_entrada;
    @FXML
    private TextField token_administrador;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void reservarChave(ActionEvent event) {
    }

    @FXML
    private void backPage(ActionEvent event) {
        NewReserve.getStage().close();
        
        Reserve newFrame = new Reserve();
        try {
            newFrame.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(NewReserveController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
