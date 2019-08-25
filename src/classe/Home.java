/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Samuel Lima
 */
public class Home extends Application {
    
    private static Stage stage;
    private static Scene dashboardScene;
    private static Scene keyScene;
    private static Scene userScene;
    private static Scene adminScene;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        stage = primaryStage;
        
        primaryStage.setTitle("Dashboard - Qchave");
        
        Parent fxmlDashboard = FXMLLoader.load(getClass().getResource("/view/Home.fxml"));
        dashboardScene = new Scene(fxmlDashboard);
        
        Parent fxmlKey = FXMLLoader.load(getClass().getResource("/view/Key.fxml"));
        keyScene = new Scene(fxmlKey);
            
        Parent fxmlUser = FXMLLoader.load(getClass().getResource("/view/User.fxml"));
        userScene = new Scene(fxmlUser);
        
        Parent fxmlAdmin = FXMLLoader.load(getClass().getResource("/view/Admin.fxml"));
        adminScene = new Scene(fxmlAdmin);
        
        primaryStage.setScene(dashboardScene);
        primaryStage.show();
    }
    
    public static void optionScreen(String screen){
        switch(screen){
            case "dashboard":
                stage.setScene(dashboardScene);
                break;
            case "key":
                stage.setTitle("Cadastro de Chaves - Qchave");
                stage.setScene(keyScene);
                break;
            case "user":
                stage.setTitle(("Cadastro de Usuários - Qchave"));
                stage.setScene(userScene);
                break;
            case "admin":
                stage.setTitle("Cadastro de Administradores - Qchave");
                stage.setScene(adminScene);
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
