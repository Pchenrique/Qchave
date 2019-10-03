/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Classe.Home;
import Classe.LoanReport;
import Classe.Report;
import Classe.ReturnReport;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Samuel Lima
 */
public class ReportController implements Initializable {

    @FXML
    private Label totalEmprestimos;
    @FXML
    private Label totalDevolucoes;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void gerarRelatorioEmprestimos(ActionEvent event) {
        LoanReport loanreport = new LoanReport();
        try {
            loanreport.start(new Stage());
            Report.getStage().close();
        } catch (Exception ex) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void gerarRelatorioDevolucoes(ActionEvent event) {
        ReturnReport returnreport = new ReturnReport();
        
        try {
            returnreport.start(new Stage());
            Report.getStage().close();
        } catch (Exception ex) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void backPage(ActionEvent event) {
        Report.getStage().close();
        Home newFrame = new Home();
        
        try {
            newFrame.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
}
