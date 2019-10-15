package Controller;

import Classe.Report;
import Classe.Reserve;
import Classe.ReservesReport;
import DAO.ReservaDao;
import Model.ModelReservas;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Samuel Lima
 */
public class ReservesReportController implements Initializable {

    @FXML
    private TableView<ModelReservas> table_relatorio_reservas;
    @FXML
    private TableColumn<ModelReservas, String> col_chave;
    @FXML
    private TableColumn<ModelReservas, String> col_responsavel;
    @FXML
    private TableColumn<ModelReservas, String> col_reserva_feita_por;
    @FXML
    private TableColumn<ModelReservas, String> col_data_saida;
    @FXML
    private TableColumn<ModelReservas, String> col_hora_saida;
    @FXML
    private TableColumn<ModelReservas, String> col_data_entrada;
    @FXML
    private TableColumn<ModelReservas, String> col_hora_entrada;
    @FXML
    private TextField campoBuscar;
    @FXML
    private Button btnBuscar;
    
    private ObservableList<ModelReservas> reservas = FXCollections.observableArrayList();
    @FXML
    private TableColumn<ModelReservas, String> col_status;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            initTable();
        } catch (SQLException ex) {
            Logger.getLogger(KeyLoanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        campoBuscar.setOnKeyReleased((KeyEvent)->{
            table_relatorio_reservas.setItems(buscar());
        });
        
        btnBuscar.setOnMouseClicked((MouseEvent)->{
            table_relatorio_reservas.setItems(buscar());
        });
    } 
    
    public void initTable() throws SQLException {
        col_chave.setCellValueFactory(new PropertyValueFactory("nome_chave"));
        col_responsavel.setCellValueFactory(new PropertyValueFactory("nome_usuario"));
        col_reserva_feita_por.setCellValueFactory(new PropertyValueFactory("nome_admin"));
        col_data_saida.setCellValueFactory(new PropertyValueFactory("data_saida"));
        col_hora_saida.setCellValueFactory(new PropertyValueFactory("hora_saida"));
        col_data_entrada.setCellValueFactory(new PropertyValueFactory("data_devolucao"));
        col_hora_entrada.setCellValueFactory(new PropertyValueFactory("hora_devolucao"));
        col_status.setCellValueFactory(new PropertyValueFactory("status"));

        table_relatorio_reservas.setItems(atualizaTable());
    }
    
    public ObservableList<ModelReservas> buscar(){
        ObservableList<ModelReservas> reservasFiltrada = FXCollections.observableArrayList();
        
        for(int i=0; i<reservas.size();i++){
            if(reservas.get(i).getNome_chave().toLowerCase().contains(campoBuscar.getText().toLowerCase()) || reservas.get(i).getNome_usuario().toLowerCase().contains(campoBuscar.getText().toLowerCase())){
                 reservasFiltrada.add(reservas.get(i));
            }
        }
        return reservasFiltrada;
    }

    //Função ObservableList para listar os campos da tabela.
    public ObservableList<ModelReservas> atualizaTable() throws SQLException {
        ReservaDao reservadao = new ReservaDao();
        this.reservas = FXCollections.observableArrayList(reservadao.lista());
        return reservas;
    }

    @FXML
    private void backPage(ActionEvent event) {
        ReservesReport.getStage().close();
        
        Report newFrame = new Report();
        
        try {
            newFrame.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(ReservesReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
