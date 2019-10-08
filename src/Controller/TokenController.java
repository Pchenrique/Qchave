/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Classe.Admin;
import Model.ModelAdmin;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Samuel Lima
 */
public class TokenController implements Initializable {

    @FXML
    private Label nome_administrador;
    @FXML
    private TextField cpf_administrador;
    @FXML
    private Label token;
    
    private static ModelAdmin selected;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nome_administrador.setText(selected.getNome());
    }    

    @FXML
    private void recuperarToken(ActionEvent event) {
       try{
            String vcpf = cpf_administrador.getText();
            if(vcpf.equals(selected.getCpf())){
                String vtoken = Integer.toString(selected.getToken());
                token.setText(vtoken);
            }else{
                 JOptionPane.showMessageDialog(null, "O CPF desse admin está incorreto!");
            }
       }catch(NumberFormatException e){
           JOptionPane.showMessageDialog(null, "O CPF tem que ser numeros e ate 11 digitos!");
           Logger.getLogger(KeyController.class.getName()).log(Level.SEVERE, null, e);
       }
    }
    
    public static ModelAdmin getSelected() {
        return selected;
    }

    public static void setSelected(ModelAdmin selected) {
        TokenController.selected = selected;
    }
    
}
