/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Classe.RegisterKey;
import DAO.KeyDao;
import Model.ModelKey;

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
    }    

    @FXML
    private void cadastrarChave(javafx.event.ActionEvent event) throws Exception {
        RegisterKey key = new RegisterKey();
        
        key.start(new Stage());
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
