/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Classe.Home;
import Classe.KeyReturn;
import Classe.Loan;
import DAO.EmprestarDAO;
import Model.ModelEmprestimo;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author Samuel Lima
 */
public class LoanController implements Initializable{
    
    @FXML
    private TableView<ModelEmprestimo> table_chaves_emprestadas;
    @FXML
    private TableColumn<ModelEmprestimo, Integer> id;
    @FXML
    private TableColumn<ModelEmprestimo, String> nome_responsavel;
    @FXML
    private TableColumn<ModelEmprestimo, String> nome_admin;
    @FXML
    private TableColumn<ModelEmprestimo, String> nome_chave;
    @FXML
    private TableColumn<ModelEmprestimo, String> data;
    @FXML
    private TextField buscar_emprestimo;
    @FXML
    private ImageView btn_emprestimo;
     
    private ModelEmprestimo selected;
    
    private ObservableList<ModelEmprestimo> emprestimos = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            initTable();
        } catch (SQLException ex) {
            Logger.getLogger(KeyLoanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        buscar_emprestimo.setOnKeyReleased((KeyEvent)->{
            table_chaves_emprestadas.setItems(buscar());
        });
        
        btn_emprestimo.setOnMouseClicked((MouseEvent)->{
            table_chaves_emprestadas.setItems(buscar());
        });
        
        //Função para verificar a linha selecionada na tabela.
        table_chaves_emprestadas.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                selected = (ModelEmprestimo) newValue;
            }
        });
    }
    
     public void initTable() throws SQLException {
        id.setCellValueFactory(new PropertyValueFactory("id"));
        nome_chave.setCellValueFactory(new PropertyValueFactory("nome_chave"));
        nome_responsavel.setCellValueFactory(new PropertyValueFactory("nome_usuario"));
        nome_admin.setCellValueFactory(new PropertyValueFactory("nome_admin"));
        data.setCellValueFactory(new PropertyValueFactory("data_emprestimo"));

        table_chaves_emprestadas.setItems(atualizaTable());
    }

    //Função ObservableList para listar os campos da tabela.
    public ObservableList<ModelEmprestimo> atualizaTable() throws SQLException {
        EmprestarDAO emprestardao = new EmprestarDAO();
        this.emprestimos = FXCollections.observableArrayList(emprestardao.listar());
        return emprestimos;
    }
    
    public ObservableList<ModelEmprestimo> buscar(){
        ObservableList<ModelEmprestimo> emprestimoFiltrada = FXCollections.observableArrayList();
        
        for(int i=0; i<emprestimos.size();i++){
            if(emprestimos.get(i).getNome_chave().toLowerCase().contains(buscar_emprestimo.getText().toLowerCase()) || emprestimos.get(i).getNome_usuario().contains(buscar_emprestimo.getText())){
                 emprestimoFiltrada.add(emprestimos.get(i));
            }
        }
        return emprestimoFiltrada;
    }
    
    @FXML
    private void devolverChave(ActionEvent event) {
        if(selected != null){
            if(selected.getStatus().equals("Encerrado")){
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setHeaderText("Devolução Já foi efetuada!.");
                alerta.show(); 
            }else{
                KeyReturn keyreturn = new KeyReturn(selected);

                try {
                    Loan.getStage().close();
                    keyreturn.start(new Stage());
                } catch (Exception ex) {
                    Logger.getLogger(KeyLoanController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setHeaderText("Selecione uma chave clicando para Devolvela.");
            alerta.show();
        }
    }

    @FXML
    private void backPage(ActionEvent event) {
        Loan.getStage().close();
        
        Home newFrame = new Home();
        try {
            newFrame.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(LoanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}
