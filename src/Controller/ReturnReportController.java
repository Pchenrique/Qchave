/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Classe.Report;
import Classe.ReturnReport;
import DAO.DevolucaoDao;
import Model.ModelDevolucao;
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
public class ReturnReportController implements Initializable {
    
    
    @FXML
    private TableView<ModelDevolucao> table_lista_devolucoes;
    @FXML
    private TableColumn<ModelDevolucao, String> col_chave;
    @FXML
    private TableColumn<ModelDevolucao, String> col_responsavel;
    @FXML
    private TableColumn<ModelDevolucao, String> col_recebida_por;
    @FXML
    private TableColumn<ModelDevolucao, String> col_data;
    @FXML
    private TextField campoBuscar;
    @FXML
    private Button btnBuscar;
    
    private ObservableList<ModelDevolucao> devolucao = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            initTable();
        } catch (SQLException ex) {
            Logger.getLogger(LoanReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        campoBuscar.setOnKeyReleased((KeyEvent)->{
            table_lista_devolucoes.setItems(buscar());
        });
        
        btnBuscar.setOnMouseClicked((MouseEvent)->{
            table_lista_devolucoes.setItems(buscar());
        });
    }    
    
     public void initTable() throws SQLException {
        col_chave.setCellValueFactory(new PropertyValueFactory("nome_chave"));
        col_responsavel.setCellValueFactory(new PropertyValueFactory("nome_usuario"));
        col_recebida_por.setCellValueFactory(new PropertyValueFactory("nome_admin"));
        col_data.setCellValueFactory(new PropertyValueFactory("data_devolucao"));

        table_lista_devolucoes.setItems(atualizaTable());
    }

    //Função ObservableList para listar os campos da tabela.
    public ObservableList<ModelDevolucao> atualizaTable() throws SQLException {
        DevolucaoDao devolucaodao = new DevolucaoDao();
        this.devolucao = FXCollections.observableArrayList(devolucaodao.listar());
        return devolucao;
    }
    
    public ObservableList<ModelDevolucao> buscar(){
        ObservableList<ModelDevolucao> devolucaoFiltrada = FXCollections.observableArrayList();
        
        for(int i=0; i<devolucao.size();i++){
            if(devolucao.get(i).getNome_chave().toLowerCase().contains(campoBuscar.getText().toLowerCase()) || devolucao.get(i).getNome_usuario().toLowerCase().contains(campoBuscar.getText().toLowerCase()) || devolucao.get(i).getData_devolucao().contains(campoBuscar.getText())){
                 devolucaoFiltrada.add(devolucao.get(i));
            }
        }
        return devolucaoFiltrada;
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
