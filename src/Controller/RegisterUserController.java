/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

//Importação do User DAO e do Model User.
import Classe.RegisterUser;
import Classe.User;
import DAO.UserDAO;
import Model.ModelUser;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Samuel Lima
 */
public class RegisterUserController implements Initializable {

    //Ids dos campos de textfield e botão de cadastrar usuário.
    @FXML
    private TextField nome_completo;
    @FXML
    private TextField email;
    @FXML
    private TextField matricula;
    @FXML
    private ComboBox<String> tipo_usuario;
    @FXML
    private Button btn_cadastrar_usuario;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            tiposDeUsuario();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void tiposDeUsuario() {
        ObservableList<String> options = FXCollections.observableArrayList("Servidor", "Bolsista", "Estagiário");
        tipo_usuario.setItems(options);
    }
    
    //Método de registrar usuário.
    @FXML
    private void registrarUsuario(ActionEvent event) throws SQLException, Exception, NumberFormatException{

        if (this.nome_completo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo nome está vázio!");
        } else if (this.matricula.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo matricula está vázio!");
        } else if (this.email.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo email está vázio!");
        } else {
            try{
                try {
                    long matricula = Long.parseLong(this.matricula.getText());
                    ModelUser user = new ModelUser(this.nome_completo.getText(), this.email.getText(), matricula, tipo_usuario.getSelectionModel().getSelectedItem());
                    UserDAO userdao = new UserDAO();
                    try{
                        userdao.inserirUser(user);
                        JOptionPane.showMessageDialog(null, "Dados cadastrados com sucesso!");

                        RegisterUser.getStage().close();

                        User newFrame = new User();
                        newFrame.start(new Stage());
                    }catch(SQLException e){
                        Logger.getLogger(KeyController.class.getName()).log(Level.SEVERE, null, e);
                        JOptionPane.showMessageDialog(null, "A Matricula informada ja existe!");
                    }
                }catch(NumberFormatException e){
                    Logger.getLogger(KeyController.class.getName()).log(Level.SEVERE, null, e);
                    JOptionPane.showMessageDialog(null, "A Matricula precisa ser so números!");
                }
            } catch (Exception e) {
                Logger.getLogger(KeyController.class.getName()).log(Level.SEVERE, null, e);
                JOptionPane.showMessageDialog(null, "O tipo de usuário precisa ser selecionado!");
            }

        }
    }

    @FXML
    private void backPage(ActionEvent event) throws Exception {
        RegisterUser.getStage().close();

        User newFrame = new User();
        newFrame.start(new Stage());
    }
}
