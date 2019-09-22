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
        String nome_sala = this.nome_sala.getText();
        //int number = Integer.parseInt(codigo_chave.getText());
        String bloco = this.bloco.getText();
        Boolean status_chave = true;
        

        ModelKey chave = new ModelKey(nome_sala, bloco, status_chave);
        KeyDao keydao = new KeyDao();
        keydao.inserirKey(chave);

        JOptionPane.showMessageDialog(null, "Dados cadastrados com sucesso!");
        
        RegisterKey.getStage().close();
        
        Key newFrame = new Key();
        newFrame.start(new Stage());
    }

    @FXML
    private void backPage(ActionEvent event) throws Exception {
        RegisterKey.getStage().close();
        
        Key newFrame = new Key();
        newFrame.start(new Stage());
    }
}
