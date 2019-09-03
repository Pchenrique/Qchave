/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import Classe.EditUser;
import Classe.RegisterUser;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DAO.UserDAO;
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
public class UserController implements Initializable {


    @FXML
    private TableView<Model.ModelUser> table_users;
    @FXML
    private TableColumn<Model.ModelUser, String> col_nome;
    @FXML
    private TableColumn<Model.ModelUser, Long> col_matricula;
    @FXML
    private TableColumn<Model.ModelUser, String> col_tipo_usuario;
    @FXML
    private TableColumn<Model.ModelUser, String> col_email;
    
    private Model.ModelUser selected;
    
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
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        table_users.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                selected = (Model.ModelUser) newValue;
            }
        });
    }     

    @FXML
    void cadastrarUsuario(javafx.event.ActionEvent event) throws Exception {
        RegisterUser user = new RegisterUser();
        
        user.start(new Stage());
        
       
    }
     
    @FXML
    private void editarUsuario(ActionEvent event) {
        if (selected != null) {
            EditUser edit =  new EditUser(selected);
            try {
                edit.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(KeyController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setHeaderText("Selecione um usuário clicando sobre o mesmo!");
            alerta.show();
        }
    }
    
    @FXML
    private void excluirUsuario(ActionEvent event) throws SQLException {
        if (selected != null) {
            UserDAO deletar =  new UserDAO();
            try {
                deletar.excluir(selected);
            } catch (SQLException ex) {
                Logger.getLogger(KeyController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setHeaderText("Selecione uma chave.");
            alerta.show();
        } 
    }

    
    public void initTable() throws SQLException{
        col_nome.setCellValueFactory(new PropertyValueFactory("nome"));
        col_email.setCellValueFactory(new PropertyValueFactory("email"));
        col_matricula.setCellValueFactory(new PropertyValueFactory("matricula"));
        col_tipo_usuario.setCellValueFactory(new PropertyValueFactory("tipo_user"));
        table_users.setItems(atualizaTable());
    }

     public  ObservableList<Model.ModelUser> atualizaTable() throws SQLException{
        UserDAO userdao = new UserDAO();
        return FXCollections.observableArrayList(userdao.listar());
    }

    
}
