/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

//Importação da classe user, do DAO user e do model user.
import Classe.User;
import DAO.UserDAO;
import Model.ModelUser;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Samuel Lima
 */
public class EditUserController implements Initializable {

    //Ids dos campos de textfield.
    @FXML
    private TextField nomeCompleto;
    @FXML
    private TextField email;
    @FXML
    private TextField matricula;
    @FXML
    private TextField tipoUsuario;

    private static Model.ModelUser user2;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //inicialização dos campos do usuário preenchidos com seus respectivos valores.
        initKey();
    }

    //Método de alterar administrador.
    @FXML
    private void alterarUsuario(ActionEvent event) throws Exception {
        String nome = this.nomeCompleto.getText();
        String email = this.email.getText();
        Long matricula = Long.parseLong(this.matricula.getText());
        String tipo_user = this.tipoUsuario.getText();

        Model.ModelUser usuario = new ModelUser(nome, email, matricula, tipo_user);

        UserDAO userdao = new UserDAO();

        try {
            userdao.editar(usuario);
            JOptionPane.showMessageDialog(null, "Dados Alterados com Sucesso!");
            openKey();
        } catch (SQLException ex) {
            Logger.getLogger(EditUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Função para preencher os campos do form do usuário.
    public void initKey() {
        nomeCompleto.setText(user2.getNome());
        email.setText(user2.getEmail());
        matricula.setText(user2.toString());
        tipoUsuario.setText(user2.getTipo_user());
    }

    public static ModelUser getUser2() {
        return user2;
    }

    public static void setUser2(ModelUser user2) {
        EditUserController.user2 = user2;
    }

    //função para abrir a userPage após alterar os dados.
    public void openKey() throws Exception {
        User user = new User();
        user.start(new Stage());
    }

}
