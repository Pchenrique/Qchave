/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Classe.KeyPermission;
import Classe.User;
import DAO.KeyDao;
import Model.ModelKey;
import Model.ModelUser;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Samuel Lima
 */
public class KeyPermissionController implements Initializable {

    @FXML
    private Button btn_permitir_chave;
    @FXML
    private Label nome_usuario;
    @FXML
    private Label matricula_usuario;
    @FXML
    private ComboBox<ModelKey> CbChavePermitida;
    
    private static Model.ModelUser selected;

    public static ModelUser getSelected() {
        return selected;
    }

    public static void setSelected(ModelUser selected) {
        KeyPermissionController.selected = selected;
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Inicialização do ComboBox.
        try {
            BuscarListaDeChaves();
        } catch (SQLException ex) {
            Logger.getLogger(KeyPermissionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    //Função para pegar a lista de chaves do BD e popular o ComboBox.
    public void BuscarListaDeChaves() throws SQLException{
        KeyDao keydao = new KeyDao();
        ObservableList<ModelKey> chaves = FXCollections.observableArrayList(keydao.listar());
        CbChavePermitida.setItems(chaves);
    }
    
    //Função para pegar o valor selecionado no ComboBox.
     public ModelKey pegarChave(){
        ModelKey chaveParaPermissao = CbChavePermitida.getSelectionModel().getSelectedItem();
        return chaveParaPermissao;
    }

    @FXML
    private void concederPermissao(ActionEvent event) {
    
    }
    
    @FXML
    private void backPage(ActionEvent event) {
        KeyPermission.getStage().close();
        
        User newFrame = new User();
        try {
            newFrame.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(KeyPermissionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
