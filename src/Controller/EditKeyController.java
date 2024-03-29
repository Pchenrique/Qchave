/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

//Importação da classe key, do DAO key e do model key.
import Classe.EditKey;
import Classe.Key;
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
 * FXML Controller class
 *
 * @author Samuel Lima
 */
public class EditKeyController implements Initializable {

    //Ids dos campos de textfield e do botao de alterar.
    @FXML
    private TextField nome_sala;
    @FXML
    private TextField bloco;
    @FXML
    private TextField cod_sala;
    /*@FXML
    private TextField codigoChave;*/
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
        int id = key2.getId();

        if(this.cod_sala.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "O campo cod_sala está vazio!");
        }else if(this.cod_sala.getText().matches(Validacoes.regexCaracteres())){
            JOptionPane.showMessageDialog(null, "O campo cod_sala não pode conter caracteres especiais!");
        }else if(this.cod_sala.getText().matches(Validacoes.regexLetras())){
            JOptionPane.showMessageDialog(null, "O campo cod_sala não pode conter letras!");
        }else if(this.nome_sala.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo nome está vazio!");
        } else if (this.nome_sala.getText().matches(Validacoes.regexCaracteres())){
            JOptionPane.showMessageDialog(null, "O campo nome não pode conter caracteres especiais!");
        } else if (this.nome_sala.getText().matches(Validacoes.regexNumeros())){
            JOptionPane.showMessageDialog(null, "O campo nome não pode conter numéros!");
        } else if (this.bloco.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "O campo bloco está vazio!");
        } else if (this.bloco.getText().matches(Validacoes.regexCaracteres())){
            JOptionPane.showMessageDialog(null, "O campo bloco não pode conter caracteres especiais!");
        } else {
            try {
                String cod_sala = this.cod_sala.getText();
                String nome_sala = this.nome_sala.getText();
                String bloco = this.bloco.getText();
                String status_chave = key2.getStatus();
                ModelKey chave = new ModelKey(cod_sala, nome_sala, bloco, status_chave);

                KeyDao keydao = new KeyDao();

                keydao.editar(chave, id);
                JOptionPane.showMessageDialog(null, "Dados Alterados com Sucesso!");
                EditKey.getStage().close();
                openKey();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Aconteceu algum erro com a base de dados!!!");
                Logger.getLogger(KeyController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    //Função para preencher os campos do form da chave.
    public void initKey() {
        cod_sala.setText(key2.getCod_sala());
        nome_sala.setText(key2.getNome_sala());
        bloco.setText(key2.getBloco());
        //codigoChave.setText(key2.toString());
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

    @FXML
    private void backPage(ActionEvent event) throws Exception {
        EditKey.getStage().close();

        Key newFrame = new Key();
        newFrame.start(new Stage());
    }
}
