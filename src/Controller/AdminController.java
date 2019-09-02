/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Classe.Admin;
import Classe.RegisterAdmin;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


import DAO.AdminDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import Model.ModelAdmin;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Samuel Lima
 */
public class AdminController implements Initializable {

    @FXML
    private TextField nome_completo;
    @FXML
    private TextField cpf;
    @FXML
    private TableView<Model.ModelAdmin> table_admins;
    @FXML
    private TableColumn<Model.ModelAdmin, String> col_nome;
    @FXML
    private TableColumn<Model.ModelAdmin, String> col_cpf;
    @FXML
    private Button btn_cadastrar_admin;

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
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }    

    @FXML
    private void cadastrarAdmin(javafx.event.ActionEvent event) {
        RegisterAdmin admin = new RegisterAdmin();
        try {
            admin.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void initTable() throws SQLException{
        col_nome.setCellValueFactory(new PropertyValueFactory("nome"));
        col_cpf.setCellValueFactory(new PropertyValueFactory("cpf"));
        table_admins.setItems(atualizaTable());
    }
    public  ObservableList<Model.ModelAdmin> atualizaTable() throws SQLException{
        AdminDao admindao =  new AdminDao();
        return FXCollections.observableArrayList(admindao.listar());
    }   
}
