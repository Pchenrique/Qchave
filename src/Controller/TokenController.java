/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ModelAdmin;
import Validacoes.Validacoes;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    private void recuperarToken(ActionEvent event) throws ParseException {
       if(this.cpf_administrador.getText().matches(Validacoes.regexLetras())){
            JOptionPane.showMessageDialog(null, "O CPF não pode ter letras");
       }else if(this.cpf_administrador.getText().matches(Validacoes.regexCaracteres())){
           JOptionPane.showMessageDialog(null, "O CPF não pode ter caracteres especiais");
       }else{
            String vcpf = FormataCPF(cpf_administrador.getText());
            if(vcpf.equals(selected.getCpf())){
                String vtoken = Integer.toString(selected.getToken());
                token.setText(vtoken);
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
    
}
