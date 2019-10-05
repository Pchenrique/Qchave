/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classe;

import Controller.TokenController;
import Model.ModelAdmin;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Samuel Lima
 */
public class Token {
    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Token.stage = stage;
    }
    
    public Token(ModelAdmin selected){
        TokenController.setSelected(selected);
    }

    public void start(Stage stage) throws Exception {

        stage.setTitle("Qchave - Recuperação de Token");
        stage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("/View/Token.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        setStage(stage);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
