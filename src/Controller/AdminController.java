/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

//Importação das classes EditAdmin, RegisterAdmin e do Model Admin e do DAO Admin.
import Classe.EditAdmin;
import Classe.RegisterAdmin;
import Model.ModelAdmin;
import DAO.AdminDao;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
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

    //Ids das colunas da tabela.
    @FXML
    private TableView<Model.ModelAdmin> table_admins;
    @FXML
    private TableColumn<Model.ModelAdmin, String> col_nome;
    @FXML
    private TableColumn<Model.ModelAdmin, String> col_cpf;
    @FXML
    private Button btn_cadastrar_admin;

    private ModelAdmin selected;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Inicializa a tela de administradores.
        try {
            initTable();
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Função para verificar a linha selecionada na tabela.
        table_admins.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                selected = (ModelAdmin) newValue;
            }
        });
    }

    //Método de cadastrar administrador.
    @FXML
    private void cadastrarAdmin(javafx.event.ActionEvent event) {
        RegisterAdmin admin = new RegisterAdmin();
        try {
            admin.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Método de editar administrador.
    @FXML
    private void editarAdministrador(ActionEvent event) {
        if (selected != null) {
            EditAdmin edit = new EditAdmin(selected);
            try {
                edit.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(KeyController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setHeaderText("Selecione um administrador clicando sobre o mesmo.");
            alerta.show();
        }
    }

    //Método de Excluir administrador.
    @FXML
    private void excluirAdministrador(ActionEvent event) {
        if (selected != null) {
            AdminDao deletar = new AdminDao();
            try {
                deletar.excluir(selected);
            } catch (SQLException ex) {
                Logger.getLogger(KeyController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setHeaderText("Selecione um administrador clicando sobre o mesmo para exclui-lo!");
            alerta.show();
        }
    }

    //Função para set os valores das colunas da tabela.
    public void initTable() throws SQLException {
        col_nome.setCellValueFactory(new PropertyValueFactory("nome"));
        col_cpf.setCellValueFactory(new PropertyValueFactory("cpf"));
        table_admins.setItems(atualizaTable());
    }

    //Função ObservableList para listar os campos da tabela.
    public ObservableList<Model.ModelAdmin> atualizaTable() throws SQLException {
        AdminDao admindao = new AdminDao();
        return FXCollections.observableArrayList(admindao.listar());
    }

}
