/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.KeyDao;
import beans.Key;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Samuel Lima
 */
public class KeyController implements Initializable {

    @FXML
    private TextField codigo_chave;
    @FXML
    private TextField nome_sala;
    @FXML
    private TextField bloco;
    @FXML
    private Button btn_cadastrar_chave;
    @FXML
    private Button btn_come_back;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    void come_back(ActionEvent event) {
        //
    }
    @FXML
    void register_key(javafx.event.ActionEvent event) throws SQLException {
    	String nome_sala = this.nome_sala.getText();
    	int number = Integer.parseInt(codigo_chave.getText());
    	String bloco = this.bloco.getText();
    	Boolean status_chave = true;
    	
		Key chave = new Key(nome_sala, number, bloco, status_chave);
    	
    	KeyDao keydao = new KeyDao();
    	
    	keydao.inserirKey(chave);
    }

}
