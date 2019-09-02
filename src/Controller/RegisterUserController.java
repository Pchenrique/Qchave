/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

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

/**
 *
 * @author Samuel Lima
 */
public class RegisterUserController implements Initializable{

    @FXML
    private TextField nome_completo;
    @FXML
    private TextField email;
    @FXML
    private TextField matricula;
    @FXML
    private Button btn_cadastrar_usuario;
    @FXML
    private TextField tipo_usuario;

    @FXML
   

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //
    }
    
    @FXML
    private void registrarUsuario(ActionEvent event) throws SQLException {
        String nome = this.nome_completo.getText();
    	String email = this.email.getText();
    	long matricula = Long.parseLong(this.matricula.getText());
    	String tipo_user = this.tipo_usuario.getText();
    	
    	ModelUser user = new ModelUser(nome, email, matricula, tipo_user);
    	
    	UserDAO userdao =  new UserDAO();
    	userdao.inserirUser(user);
    }
}
