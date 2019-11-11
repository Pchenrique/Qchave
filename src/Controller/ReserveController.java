/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Classe.Home;
import Classe.NewReserve;
import Classe.Reserve;
import DAO.KeyDao;
import DAO.ReservaDao;
import Model.ModelKey;
import Model.ModelReservas;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

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
    private TableView<ModelReservas> table_reservas;
    @FXML
    private TableColumn<ModelReservas, Integer> col_id;
    @FXML
    private TableColumn<ModelReservas, String> col_chave;
    @FXML
    private TableColumn<ModelReservas, String> col_responsavel;
    @FXML
    private TableColumn<ModelReservas, String> col_reserva_feita_por;
    @FXML
    private TableColumn<ModelReservas, String> data_saida;
    @FXML
    private TableColumn<ModelReservas, String> hora_saida;
    @FXML
    private TableColumn<ModelReservas, String> data_entrada;
    @FXML
    private TableColumn<ModelReservas, String> hora_entrada;
    
    private ObservableList<ModelReservas> reservas = FXCollections.observableArrayList();
    
    private ModelReservas selected;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            initTable();
        } catch (SQLException ex) {
            Logger.getLogger(KeyLoanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        buscar_reserva.setOnKeyReleased((KeyEvent)->{
            table_reservas.setItems(buscar());
        });
        
        btn_buscar.setOnMouseClicked((MouseEvent)->{
            table_reservas.setItems(buscar());
        });
        
        //Função para verificar a linha selecionada na tabela.
        table_reservas.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                selected = (ModelReservas) newValue;
            }
        });
    }    
    
    public void initTable() throws SQLException {
        col_id.setCellValueFactory(new PropertyValueFactory("id"));
        col_chave.setCellValueFactory(new PropertyValueFactory("nome_chave"));
        col_responsavel.setCellValueFactory(new PropertyValueFactory("nome_usuario"));
        col_reserva_feita_por.setCellValueFactory(new PropertyValueFactory("nome_admin"));
        data_saida.setCellValueFactory(new PropertyValueFactory("data_saida"));
        hora_saida.setCellValueFactory(new PropertyValueFactory("hora_saida"));
        data_entrada.setCellValueFactory(new PropertyValueFactory("data_devolucao"));
        hora_entrada.setCellValueFactory(new PropertyValueFactory("hora_devolucao"));

        table_reservas.setItems(atualizaTable());
    }

    //Função ObservableList para listar os campos da tabela.
    public ObservableList<ModelReservas> atualizaTable() throws SQLException {
        ReservaDao reservadao = new ReservaDao();
        this.reservas = FXCollections.observableArrayList(reservadao.listaDisponivel());
        return reservas;
    }
    
    public ObservableList<ModelReservas> buscar(){
        ObservableList<ModelReservas> reservasFiltrada = FXCollections.observableArrayList();
        
        for(int i=0; i<reservas.size();i++){
            if(reservas.get(i).getNome_chave().toLowerCase().contains(buscar_reserva.getText().toLowerCase()) || reservas.get(i).getNome_usuario().toLowerCase().contains(buscar_reserva.getText().toLowerCase()) || reservas.get(i).getData_saida().contains(buscar_reserva.getText())){
                reservasFiltrada.add(reservas.get(i));
            }
        }
        return reservasFiltrada;
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
    private void excluirReserva(ActionEvent event) throws SQLException {
        if(selected != null){
            int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente finalizar a reserva da sala " + selected.getNome_chave().toUpperCase() + "?","Finalizar reservas?", JOptionPane.YES_NO_OPTION);

             if(resposta == JOptionPane.YES_OPTION){
                 KeyDao banco_key = new KeyDao();
                 ModelKey key = new ModelKey();
                 
                 key = banco_key.buscarKey(selected.getId_chave());
                 
                 key.setStatus("Disponivel");
                 KeyDao banco_key2 = new KeyDao();
                 
                 banco_key2.editar(key, key.getId());
                 
                 ReservaDao daoreserva = new ReservaDao();
                 selected.setStatus("Finalizada");
                 daoreserva.editar(selected, selected.getId());
                 JOptionPane.showMessageDialog(null, "Reserva Finalizada com Sucesso!");
                 Reserve.getStage().close();
                 
                 Reserve newFrame = new Reserve();
                 
                try {
                    newFrame.start(new Stage());
                } catch (Exception ex) {
                    Logger.getLogger(ReserveController.class.getName()).log(Level.SEVERE, null, ex);
                }
             }
        }else{
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setHeaderText("Selecione uma reserva clicando para Devolve-la.");
            alerta.show();
        }
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
