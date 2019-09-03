/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Samuel Lima
 */
public class Qchave extends Application {
      
    private static Stage stage;
    private static Scene DashboardScene;
    private static Scene KeyScene;
    
                
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        
        //primaryStage.setTitle("QChave - IFCE");
        
        Parent fxmlDashboard = FXMLLoader.load(getClass().getResource("../gui/FXMLDashboard.fxml"));
        DashboardScene = new Scene(fxmlDashboard);
        
        Parent fxmlKey = FXMLLoader.load(getClass().getResource("FXMLKey.fxml"));
        KeyScene = new Scene(fxmlKey);
        
        stage.setScene(DashboardScene);
        stage.show();
    }
    
    public static void optionsScene(String option){
        switch(option){
            case "dashboard":
                stage.setScene(DashboardScene);
                break;
            case "key":
                stage.setScene(KeyScene);
                break;
                
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
