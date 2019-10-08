/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Classe.Reserve;
import Classe.ReservesReport;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Samuel Lima
 */
public class ReservesReportController implements Initializable {

    @FXML
    private TableView<?> table_relatorio_reservas;
    @FXML
    private TableColumn<?, ?> col_chave;
    @FXML
    private TableColumn<?, ?> col_responsavel;
    @FXML
    private TableColumn<?, ?> col_reserva_feita_por;
    @FXML
    private TableColumn<?, ?> col_data_saida;
    @FXML
    private TableColumn<?, ?> col_hora_saida;
    @FXML
    private TableColumn<?, ?> col_data_entrada;
    @FXML
    private TableColumn<?, ?> col_hora_entrada;
    @FXML
    private TextField campoBuscar;
    @FXML
    private Button btnBuscar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void backPage(ActionEvent event) {
        ReservesReport.getStage().close();
        
        Reserve newFrame = new Reserve();
        
        try {
            newFrame.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(ReservesReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
