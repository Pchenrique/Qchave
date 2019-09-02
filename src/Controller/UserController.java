/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import Classe.RegisterUser;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DAO.UserDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class UserController implements Initializable {

    @FXML
    private TextField nome_completo;
    @FXML
    private TextField email;
    @FXML
    private TextField matricula;
    @FXML
    private TextField tipo_usuario;

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
    }     

    @FXML
    void cadastrarUsuario(javafx.event.ActionEvent event) throws Exception {
        RegisterUser user = new RegisterUser();
        
        user.start(new Stage());
        
       
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
