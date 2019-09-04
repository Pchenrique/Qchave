/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classe;

import Controller.EditAdminController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Samuel Lima
 */
public class EditAdmin extends Application {

    public EditAdmin(Model.ModelAdmin admin1) {
        EditAdminController.setAdmin2(admin1);
    }

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Qchave - Editar Administrador");
        Parent root = FXMLLoader.load(getClass().getResource("/View/EditAdmin.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

}
