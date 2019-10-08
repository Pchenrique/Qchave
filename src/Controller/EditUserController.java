/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

//Importação da classe user, do DAO user e do model user.
import Classe.EditUser;
import Classe.User;
import DAO.UserDao;
import Model.ModelUser;
import Validacoes.Validacoes;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
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
    private ComboBox<String> tipo_user;
    
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
        initUser();
    }

    //Método de alterar administrador.
    @FXML
    private void alterarUsuario(ActionEvent event) throws Exception,  NumberFormatException{
        int id = user2.getId();

        if (this.nomeCompleto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo nome está vázio!");
        } else if (this.nomeCompleto.getText().matches(Validacoes.regexNumeros())) {
            JOptionPane.showMessageDialog(null, "O campo nome não pode conter numéros!");
        } else if (this.nomeCompleto.getText().matches(Validacoes.regexCaracteres())) {
            JOptionPane.showMessageDialog(null, "O campo nome não pode conter caracteres especiais!");
        } else if (this.matricula.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo matricula está vázio!");
        } else if (this.matricula.getText().matches(Validacoes.regexCaracteres())) {
            JOptionPane.showMessageDialog(null, "O campo matrícula não pode conter caracteres especiais!");
        } else if (this.matricula.getText().matches(Validacoes.regexLetras())) {
            JOptionPane.showMessageDialog(null, "O campo matrícula não pode conter letras!");
        } else if (this.matricula.getText().length() > 7) {
            JOptionPane.showMessageDialog(null, "O matrícula não pode conter mais do que 7 numéros!");
        } else if (this.email.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo email está vázio!");
        } else {

            try{
                Long matricula = Long.parseLong(this.matricula.getText());
                Model.ModelUser usuario = new ModelUser(this.nomeCompleto.getText(), this.email.getText(), matricula, tipo_user.getSelectionModel().getSelectedItem());
                UserDao userdao = new UserDao();
                try {
                    userdao.editar(usuario, id);
                    JOptionPane.showMessageDialog(null, "Dados Alterados com Sucesso!");
                    EditUser.getStage().close();
                    openUser();
                } catch (SQLException ex) {
                    Logger.getLogger(EditUserController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "A Matricula informada ja existe!");
                }
            }catch(NumberFormatException e){
                    Logger.getLogger(KeyController.class.getName()).log(Level.SEVERE, null, e);
                    JOptionPane.showMessageDialog(null, "A Matricula precisa ser so números!");
            }   
        }
    }

    //Função para preencher os campos do form do usuário.
    public void initUser() {
        nomeCompleto.setText(user2.getNome());
        email.setText(user2.getEmail());
        matricula.setText(String.valueOf(user2.getMatricula()));
        
        ObservableList<String> options = FXCollections.observableArrayList("Servidor", "Bolsista", "Estagiário");
        tipo_user.setItems(options);
        
        tipo_user.getSelectionModel().select(user2.getTipo_user());
    }

    public static ModelUser getUser2() {
        return user2;
    }

    public static void setUser2(ModelUser user2) {
        EditUserController.user2 = user2;
    }

    //função para abrir a userPage após alterar os dados.
    public void openUser() throws Exception {
        User user = new User();
        user.start(new Stage());
    }

    @FXML
    private void backPage(ActionEvent event) throws Exception {
        EditUser.getStage().close();

        User newFrame = new User();
        newFrame.start(new Stage());
    }
}
