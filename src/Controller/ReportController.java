/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Classe.Home;
import Classe.LoanReport;
import Classe.Report;
import Classe.ReservesReport;
import Classe.ReturnReport;
import DAO.DevolucaoDao;
import DAO.EmprestarDao;
import Model.ModelDevolucao;
import Model.ModelEmprestimo;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    @FXML
    private Label totalReservas;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EmprestarDao emprestardao = new EmprestarDao();
        DevolucaoDao devolucao = new DevolucaoDao();
        
        List<ModelEmprestimo> listaEmprestimos = new ArrayList();
        
        try {
            listaEmprestimos = emprestardao.listarTudo();
        } catch (SQLException ex) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
        int cont=0;
        for(int i=0; i<listaEmprestimos.size();i++){
            cont++;
        }
        totalEmprestimos.setText(Integer.toString(cont));
        
        List<ModelDevolucao> listaDevolucao = new ArrayList();
        
        try {
            listaDevolucao = devolucao.listar();
        } catch (SQLException ex) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
        int cont2=0;
        for(int i=0; i<listaDevolucao.size();i++){
            cont2++;
        }
        totalDevolucoes.setText(Integer.toString(cont2));
        
        
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
    private void gerarRelatorioReservas(ActionEvent event) {
        ReservesReport reservesreport = new ReservesReport();
        
        try {
            reservesreport.start(new Stage());
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
