/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Classe.Admin;
import Classe.EditAdmin;
import Classe.TokenEdit;
import Model.ModelAdmin;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Samuel Lima
 */
public class TokinEditController implements Initializable {

    @FXML
    private Label nomeAdministrador;
    @FXML
    private TextField token;

    static ModelAdmin selected;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomeAdministrador.setText(selected.getNome());
    }

    @FXML
    private void verificaToken(ActionEvent event) throws SQLException, Exception {
        try{
            int vtoken = Integer.parseInt(token.getText());
            if(vtoken == selected.getToken()){
                EditAdmin edit = new EditAdmin(selected);
                try {
                    edit.start(new Stage());
                    Admin.getStage().close();

                } catch (Exception ex) {
                    Logger.getLogger(KeyController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                JOptionPane.showMessageDialog(null, "O token desse admin é diferente!");
            }
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "O token é somente numeros e com ate 8 digitos!");
            Logger.getLogger(KeyController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public static ModelAdmin getSelected() {
        return selected;
    }

    public static void setSelected(ModelAdmin selected) {
        TokinEditController.selected = selected;
    }

    @FXML
    private void cancelar(ActionEvent event) {
        TokenEdit.getStage().close();
        Admin newFrame = new Admin();

        try {
            newFrame.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(TokinEditController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
