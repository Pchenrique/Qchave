/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

//Importação da classe key, do DAO key e do model key.
import Classe.Key;
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
 * FXML Controller class
 *
 * @author Samuel Lima
 */
public class EditKeyController implements Initializable {

    //Ids dos campos de textfield e do botao de alterar.
    @FXML
    private TextField nomeSala;
    @FXML
    private TextField blocoSala;
    @FXML
    private TextField codigoChave;
    @FXML
    private Button btnAlterar;

    private static Model.ModelKey key2;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //inicialização dos campos da chave preenchidos com seus respectivos valores.
        initKey();
    }

    //Método de alterar administrador.
    @FXML
    private void alterarChave(ActionEvent event) throws SQLException, Exception {
        String nome_sala = this.nomeSala.getText();
        int number = Integer.parseInt(this.codigoChave.getText());
        String bloco = this.blocoSala.getText();
        Boolean status_chave = true;

        ModelKey chave = new ModelKey(nome_sala, number, bloco, status_chave);

        KeyDao keydao = new KeyDao();

        keydao.editar(chave);
        JOptionPane.showMessageDialog(null, "Dados Alterados com Sucesso!");
        openKey();
    }

    //Função para preencher os campos do form da chave.
    public void initKey() {
        nomeSala.setText(key2.getNome_sala());
        blocoSala.setText(key2.getBloco());
        codigoChave.setText(key2.toString());
    }

    public static ModelKey getKey2() {
        return key2;
    }

    public static void setKey2(ModelKey key2) {
        EditKeyController.key2 = key2;
    }

    //função para abrir a keyPage após alterar os dados.
    public void openKey() throws Exception {
        Key key = new Key();
        key.start(new Stage());
    }

}
