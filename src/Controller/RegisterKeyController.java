/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

//Importação do Key DAO e do Model Key.
import Classe.Key;
import Classe.RegisterKey;
import DAO.KeyDao;
import Model.ModelKey;
import Validacoes.Validacoes;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class RegisterKeyController implements Initializable {

    //Ids dos campos de textfield e botão de cadastrar chave.
    @FXML
    private TextField nome_sala;
    /*@FXML
    private TextField codigo_chave;*/
    @FXML
    private TextField bloco;
    @FXML
    private Button btn_cadastrar_chave;
    @FXML
    private TextField codigo_chave;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //.
    }

    //Método de registrar administrador.
    @FXML
    private void registrarChave(ActionEvent event) throws SQLException, Exception {

        if (this.nome_sala.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo nome está vazio!");
        } else if (this.nome_sala.getText().matches(Validacoes.regexCaracteres())) {
            JOptionPane.showMessageDialog(null, "O campo nome não pode conter caracteres especiais!");
        } else if (this.nome_sala.getText().matches(Validacoes.regexNumeros())) {
            JOptionPane.showMessageDialog(null, "O campo nome não pode conter numéros!");
        } else if (this.bloco.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo bloco está vazio!");
        } else if (this.bloco.getText().matches(Validacoes.regexCaracteres())) {
            JOptionPane.showMessageDialog(null, "O campo bloco não pode conter caracteres especiais!");
        } else {
            String nome_sala = this.nome_sala.getText();
            String bloco = this.bloco.getText();
            String status_chave = "Disponivel";
            
            ModelKey chave = new ModelKey(nome_sala, bloco, status_chave);
            KeyDao keydao = new KeyDao();
            try {
                keydao.inserirKey(chave);
                JOptionPane.showMessageDialog(null, "Dados cadastrados com sucesso!");
                RegisterKey.getStage().close();

                Key newFrame = new Key();
                newFrame.start(new Stage());
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Aconteceu algum erro com a base de dados!!!");
                Logger.getLogger(KeyController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    @FXML
    private void backPage(ActionEvent event) throws Exception {
        RegisterKey.getStage().close();

        Key newFrame = new Key();
        newFrame.start(new Stage());
    }
}
