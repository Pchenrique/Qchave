/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Classe.EditKey;
import Classe.Key;
import Classe.RegisterKey;
import DAO.KeyDao;
import Model.ModelKey;

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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Samuel Lima
 */
public class KeyController implements Initializable {

    @FXML
    private TableColumn<ModelKey, Integer> codigo_chave;
    @FXML
    private TableColumn<ModelKey, String> bloco;
    @FXML
    private TableColumn<ModelKey, String> nome;
    @FXML
    private TableColumn<ModelKey, Boolean> status;
    @FXML
    private TableView<ModelKey> table_chaves;
    @FXML
    private Button keyEdit;
    
    private ModelKey selected;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            initTable();
        } catch (SQLException ex) {
            Logger.getLogger(KeyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        table_chaves.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                selected = (ModelKey) newValue;
            }
        });
    }    

    @FXML
    private void cadastrarChave(javafx.event.ActionEvent event) throws Exception {
        RegisterKey key = new RegisterKey();
        
        key.start(new Stage());
    }
    
    @FXML
    private void editarChave(javafx.event.ActionEvent event) throws Exception {
        if (selected != null) {
            EditKey edit =  new EditKey(selected);
            try {
                edit.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(KeyController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setHeaderText("Selecione uma chave.");
            alerta.show();
        }
    }
    
    @FXML
    private void excluirChave(ActionEvent event) throws SQLException {
        if (selected != null) {
            KeyDao deletar =  new KeyDao();
            try {
                deletar.excluir(selected);
            } catch (Exception ex) {
                Logger.getLogger(KeyController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setHeaderText("Selecione uma chave.");
            alerta.show();
        }
        
        
        
       
    }

     public void initTable() throws SQLException{
        nome.setCellValueFactory(new PropertyValueFactory("nome_sala"));
        codigo_chave.setCellValueFactory(new PropertyValueFactory("codigo_chave"));
        bloco.setCellValueFactory(new PropertyValueFactory("bloco"));
        status.setCellValueFactory(new PropertyValueFactory("status"));
        table_chaves.setItems(atualizaTable());
    }
    public  ObservableList<Model.ModelKey> atualizaTable() throws SQLException{
        KeyDao keydao =  new KeyDao();
        return FXCollections.observableArrayList(keydao.listar());
    }   

   
   

}
