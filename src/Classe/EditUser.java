/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classe;

import Controller.EditUserController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Samuel Lima
 */
public class EditUser extends Application {
    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        EditUser.stage = stage;
    }
   
    public EditUser(Model.ModelUser user1) {
        EditUserController.setUser2(user1);
    }

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Qchave - Editar Usuário");
        stage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("/View/EditUser.fxml"));
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
