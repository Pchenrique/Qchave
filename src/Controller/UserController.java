/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DAO.UserDAO;
import beans.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
    private Button btn_cadastrar_usuario;
    @FXML
    private TextField tipo_usuario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    void register_user(javafx.event.ActionEvent event) throws SQLException {
    	String nome = this.nome_completo.getText();
    	String email = this.email.getText();
    	long matricula = Long.parseLong(this.matricula.getText());
    	String tipo_user = this.tipo_usuario.getText();
    	
    	User user = new User(nome, email, matricula, tipo_user);
    	
    	UserDAO userdao =  new UserDAO();
    	userdao.inserirUser(user);
    }
    
}
