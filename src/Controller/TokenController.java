/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Classe.Admin;
import Classe.Token;
import Model.ModelAdmin;
import Validacoes.Validacoes;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

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
    
    @FXML
    private Button button_voltar;
            
    private static ModelAdmin selected;
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nome_administrador.setText(selected.getNome());
        button_voltar.setVisible(false);
    }    

    @FXML
    private void recuperarToken(ActionEvent event) throws ParseException {
       if(this.cpf_administrador.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "O CPF não pode ser vazio");
       }else if(this.cpf_administrador.getText().matches(Validacoes.regexLetras())){
            JOptionPane.showMessageDialog(null, "O CPF não pode ter letras");
       }else if(this.cpf_administrador.getText().matches(Validacoes.regexCaracteres())){
           JOptionPane.showMessageDialog(null, "O CPF não pode ter caracteres especiais");
       }else if(this.cpf_administrador.getText().length() < 11 || this.cpf_administrador.getText().length() > 11){
           JOptionPane.showMessageDialog(null, "O CPF não pode ser maior ou menor que 11 caracteres");
       }else{
            String vcpf = FormataCPF(cpf_administrador.getText());
            if(vcpf.equals(selected.getCpf())){
                String vtoken = Integer.toString(selected.getToken());
                token.setText(vtoken);
                button_voltar.setVisible(true);
            }else{
                 JOptionPane.showMessageDialog(null, "O CPF desse admin está incorreto!");
            }
       }
    }
    
    public String FormataCPF(String cpf) throws ParseException {
        MaskFormatter mask = new MaskFormatter("###.###.###-##");
        mask.setValueContainsLiteralCharacters(false);

        return mask.valueToString(cpf);
    }
    
    public static ModelAdmin getSelected() {
        return selected;
    }

    public static void setSelected(ModelAdmin selected) {
        TokenController.selected = selected;
    }

    @FXML
    private void cancelar(ActionEvent event) {
        Token.getStage().close();
        
        Admin newFrame = new Admin();
        try {
            newFrame.start(new Stage());
        }catch (Exception ex) {
            Logger.getLogger(TokenController.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    @FXML
    private void button_voltar(ActionEvent event){
        Token.getStage().close();
        
        Admin newFrame = new Admin();
        try {
            newFrame.start(new Stage());
        }catch (Exception ex) {
            Logger.getLogger(TokenController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
