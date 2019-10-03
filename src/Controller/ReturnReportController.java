/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Classe.LoanReport;
import Classe.Report;
import Classe.ReturnReport;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Samuel Lima
 */
public class ReturnReportController implements Initializable {

    @FXML
    private TableColumn<?, ?> col_chave;
    @FXML
    private TableColumn<?, ?> col_responsavel;
    @FXML
    private TableColumn<?, ?> col_recebida_por;
    @FXML
    private TableColumn<?, ?> col_data;
    @FXML
    private TextField campoBuscar;
    @FXML
    private Button btnBuscar;

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
    private void backPage(ActionEvent event) {
        ReturnReport.getStage().close();
        
        Report newFrame = new Report();
        try {
            newFrame.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(LoanReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
