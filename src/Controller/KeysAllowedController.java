/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Classe.KeyPermission;
import Classe.User;
import DAO.KeyPermissionDao;
import Model.ModelKeyPermission;
import Model.ModelUser;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Samuel Lima
 */
public class KeysAllowedController implements Initializable {

    @FXML
    private Label nome_usuario;

    private static Model.ModelUser usuario;
    
    @FXML
    private TableView<ModelKeyPermission> table_chaves_permitidas;
    @FXML
    private TableColumn<ModelKeyPermission, String> chaves_permitidas;

    private ObservableList<ModelKeyPermission> permitidas = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nome_usuario.setText(usuario.getNome());
        
        initTable(); 
    }

    @FXML
    private void novaPermissao(ActionEvent event) {

        KeyPermission permission = new KeyPermission(usuario);
        try {
            User.getStage().close();
            permission.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(KeyPermissionController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void excluirPermissao(ActionEvent event) {
    }

    public ObservableList<ModelKeyPermission> lista() throws SQLException {
        KeyPermissionDao buscarPorId = new KeyPermissionDao();
        this.permitidas = FXCollections.observableArrayList(buscarPorId.listarPorUsuario(usuario.getId()));
        return permitidas;
    }

    public void initTable(){
        chaves_permitidas.setCellValueFactory(new PropertyValueFactory("nome_chave"));
        try {
            table_chaves_permitidas.setItems(lista());
        } catch (SQLException ex) {
            Logger.getLogger(KeysAllowedController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void cancelar(ActionEvent event) {
    }

    public static ModelUser getPermitida() {
        return usuario;
    }

    public static void setPermitida(ModelUser permitida) {
        KeysAllowedController.usuario = permitida;
    }

}
