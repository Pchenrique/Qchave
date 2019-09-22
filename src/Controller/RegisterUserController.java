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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    private TextField tipo_usuario;
    @FXML
    private Button btn_cadastrar_usuario;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //
    }

    //Método de registrar usuário.
    @FXML
    private void registrarUsuario(ActionEvent event) throws SQLException, Exception {
        String nome = this.nome_completo.getText();
        String email = this.email.getText();
        long matricula = Long.parseLong(this.matricula.getText());
        String tipo_user = this.tipo_usuario.getText();
       
        ModelUser user = new ModelUser(nome, email, matricula, tipo_user);
        UserDAO userdao = new UserDAO();
        userdao.inserirUser(user);

        JOptionPane.showMessageDialog(null, "Dados cadastrados com sucesso!");
        
        RegisterUser.getStage().close();
        
        User newFrame = new User();
        newFrame.start(new Stage());
    }

    @FXML
    private void backPage(ActionEvent event) throws Exception {
        RegisterUser.getStage().close();
        
        User newFrame = new User();
        newFrame.start(new Stage());
    }
}
