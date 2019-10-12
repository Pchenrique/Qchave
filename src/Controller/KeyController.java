/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

//Importação das classes EditKey, RegisterKey, do Key DAO e do Model Key.
import Classe.EditKey;
import Classe.Home;
import Classe.Key;
import Classe.KeyLoan;
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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Samuel Lima
 */
public class KeyController implements Initializable {

    //Ids das colunas da tabela.
    @FXML
    private TableColumn<ModelKey, Integer> id;
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
    @FXML
    private TextField buscar_chave;
    @FXML
    private ImageView btn_buscar;

    private ModelKey selected;
    
    private ObservableList<ModelKey> chaves = FXCollections.observableArrayList();
   
   

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            //Inicializa a tela de chaves.
            initTable();
        } catch (SQLException ex) {
            Logger.getLogger(KeyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        buscar_chave.setOnKeyReleased((KeyEvent)->{
            table_chaves.setItems(buscar());
        });
        
        btn_buscar.setOnMouseClicked((MouseEvent)->{
            table_chaves.setItems(buscar());
        });
        
        //Função para verificar a linha selecionada na tabela.
        table_chaves.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                selected = (ModelKey) newValue;
            }
        });
    }

    //Método para chamar a view de cadastrar chave.
    @FXML
    private void cadastrarChave(javafx.event.ActionEvent event) {
        RegisterKey key = new RegisterKey();

        try {
            key.start(new Stage());
            Key.getStage().close();
        } catch (Exception ex) {
            Logger.getLogger(KeyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Método para chamar a view de emprestar chave.
    @FXML
    private void emprestarChave(javafx.event.ActionEvent event) {
        if (selected != null) {
            if(selected.getStatus().equals("Emprestada")){
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Aviso Chave Emprestada");
                alerta.setHeaderText("A chave "+selected.getNome_sala().toUpperCase()+" já está emprestada!");
                alerta.show();
            }else{
                Key.getStage().close();
                KeyLoan keyloan = new KeyLoan(selected);
            
                try {
                    keyloan.start(new Stage());
                } catch (Exception ex) {
                    Logger.getLogger(KeyController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setHeaderText("Selecione uma chave clicando para emprestar.");
            alerta.show();
        }
    }

     //Método para chamar a view de devolver chave.

    //Método de editar chave.
    @FXML
    private void editarChave(javafx.event.ActionEvent event) throws Exception {
        if (selected != null) {
            EditKey edit = new EditKey(selected);
            try {
                edit.start(new Stage());
                Key.getStage().close();
            } catch (Exception ex) {
                Logger.getLogger(KeyController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setHeaderText("Selecione uma chave clicando sobre a mesma para edita-lá.");
            alerta.show();
        }
    }

    //Método de excluir chave.
    @FXML
    private void excluirChave(ActionEvent event) throws Exception {
        if (selected != null) {
            KeyDao deletar = new KeyDao();
            int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir " + selected.getNome_sala() + "?","Excluir usuario?", JOptionPane.YES_NO_OPTION);
          
            if(resposta == JOptionPane.YES_OPTION){
                try {
                    deletar.excluir(selected);
                    Key.getStage().close();

                    Key newFrame = new Key();
                    newFrame.start(new Stage());
                } catch (SQLException ex) {
                    Alert alerta = new Alert(Alert.AlertType.WARNING);
                    alerta.setTitle("Cuidado!");
                    alerta.setHeaderText("A chave que você tenta excluir pode esta em uma transação.");
                    alerta.setContentText("Por esse motivo elá não pode mais ser apagada!");
                    alerta.show();
                    Logger.getLogger(KeyController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setHeaderText("Selecione uma chave clicando sobre a mesma para exclui-lá.");
            alerta.show();
        }
    }

    //Função para set os valores das colunas da tabela.
    public void initTable() throws SQLException {
        id.setCellValueFactory(new PropertyValueFactory("id"));
        nome.setCellValueFactory(new PropertyValueFactory("nome_sala"));
        bloco.setCellValueFactory(new PropertyValueFactory("bloco"));
        status.setCellValueFactory(new PropertyValueFactory("status"));
        table_chaves.setItems(atualizaTable());
    }

    //Função ObservableList para listar os campos da tabela.
    public ObservableList<ModelKey> atualizaTable() throws SQLException {
        KeyDao keydao = new KeyDao();
        chaves = FXCollections.observableArrayList(keydao.listar());
        return chaves;
    }
    
    public ObservableList<ModelKey> buscar(){
        ObservableList<ModelKey> chaveFiltrada = FXCollections.observableArrayList();
        
        for(int i=0; i<chaves.size();i++){
            String id_sala = Integer.toString(chaves.get(i).getId());
            if(chaves.get(i).getNome_sala().toLowerCase().contains(buscar_chave.getText().toLowerCase()) || id_sala.contains(buscar_chave.getText())){
                 chaveFiltrada.add(chaves.get(i));
            }
        }
        return chaveFiltrada;
    }

    @FXML
    private void backPage(ActionEvent event) throws Exception {
        Key.getStage().close();
        
        Home newFrame = new Home();
        newFrame.start(new Stage());
    }
}
