/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Classe.Home;
import Classe.NewReserve;
import Classe.Reserve;
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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Samuel Lima
 */
public class ReserveController implements Initializable {

    @FXML
    private TextField buscar_reserva;
    @FXML
    private Button btn_reserva;
    @FXML
    private ImageView btn_buscar;
    @FXML
    private TableView<?> table_reservas;
    @FXML
    private TableColumn<?, ?> col_id;
    @FXML
    private TableColumn<?, ?> col_chave;
    @FXML
    private TableColumn<?, ?> col_responsavel;
    @FXML
    private TableColumn<?, ?> col_reserva_feita_por;
    @FXML
    private TableColumn<?, ?> col_saida;
    @FXML
    private TableColumn<?, ?> col_entrada;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void novaReserva(ActionEvent event) {
        NewReserve newreserve = new NewReserve();
        try {
            newreserve.start(new Stage());
            Reserve.getStage().close();
        } catch (Exception ex) {
            Logger.getLogger(ReserveController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void excluirReserva(ActionEvent event) {
    }
    
    @FXML
    private void backPage(ActionEvent event) {
        Reserve.getStage().close();
        
        Home newFrame = new Home();
        
        try {
            newFrame.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(ReserveController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    
}
