/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classe;

import static Classe.Home.setStage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Samuel Lima
 */
public class RegisterKey extends Application {
    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        RegisterKey.stage = stage;
    }

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Qchave - Cadastro de Chaves");
        stage.setResizable(false);
        stage.getIcons().add(new Image(getClass().getResourceAsStream(".././Images/logoKey.png")));
        Parent root = FXMLLoader.load(getClass().getResource("/View/RegisterKey.fxml"));
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
