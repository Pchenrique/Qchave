/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

//Importação das classes EditAdmin, RegisterAdmin e do Model Admin e do DAO Admin.
import Classe.Admin;
import Classe.EditAdmin;
import Classe.Home;
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
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.C;
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
    @FXML
    private TableColumn<ModelAdmin, Integer> id;
    @FXML
    private TextField buscar_admin;
    @FXML
    private ImageView btn_buscar;

    private ModelAdmin selected;
    
     private ObservableList<ModelAdmin> admins = FXCollections.observableArrayList();
   
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
        
        buscar_admin.setOnKeyReleased((KeyEvent)->{
            table_admins.setItems(buscar());
        });
        
        btn_buscar.setOnMouseClicked((MouseEvent)->{
            table_admins.setItems(buscar());
        });
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
            Admin.getStage().close();
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
                Admin.getStage().close();
               
            } catch (Exception ex) {
                Logger.getLogger(KeyController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setHeaderText("Selecione um administrador clicando sobre o mesmo para edita-lo.");
            alerta.show();
        }
    }

    //Método de Excluir administrador.
    @FXML
    private void excluirAdministrador(ActionEvent event) throws Exception {
        if (selected != null) {
            AdminDao deletar = new AdminDao();
            try {
                deletar.excluir(selected);
                Admin.getStage().close();
                
                Admin newFrame = new Admin();
                newFrame.start(new Stage());
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
        id.setCellValueFactory(new PropertyValueFactory("id"));
        col_nome.setCellValueFactory(new PropertyValueFactory("nome"));
        col_cpf.setCellValueFactory(new PropertyValueFactory("cpf"));
        table_admins.setItems(atualizaTable());
    }

    //Função ObservableList para listar os campos da tabela.
    public ObservableList<Model.ModelAdmin> atualizaTable() throws SQLException {
        AdminDao admindao = new AdminDao();
        admins = FXCollections.observableArrayList(admindao.listar());
        return admins;
    }
    
     public ObservableList<ModelAdmin> buscar(){
        ObservableList<ModelAdmin> adminsFiltrada = FXCollections.observableArrayList();
        
        for(int i=0; i<admins.size();i++){
            if(admins.get(i).getNome().toLowerCase().contains(buscar_admin.getText().toLowerCase()) || admins.get(i).getCpf().contains(buscar_admin.getText())){
                adminsFiltrada.add(admins.get(i));
            }
        }
        return adminsFiltrada;
    }

    @FXML
    private void backPage(ActionEvent event) throws Exception {
        Admin.getStage().close();
        
        Home newFrame = new Home();
        newFrame.start(new Stage());
    }

}
