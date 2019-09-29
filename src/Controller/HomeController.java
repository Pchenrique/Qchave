/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

//Importação das classes admin, user e key.
import Classe.Admin;
import Classe.Home;
import Classe.Key;
import Classe.Loan;
import Classe.User;
import DAO.DevolucaoDao;
import DAO.EmprestarDAO;
import DAO.KeyDao;
import DAO.UserDAO;
import Model.ModelDevolucao;
import Model.ModelEmprestimo;
import Model.ModelKey;
import Model.ModelUser;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Samuel Lima
 */
public class HomeController implements Initializable {

    @FXML
    private Button devolver_chave;
    @FXML
    private Button emprestar_chave;
    @FXML
    private Label info_emprestimos;
    @FXML
    private Label info_chaves;
    @FXML
    private Label info_usuarios;
    @FXML
    private Label info_devolucoes;
    @FXML
    private Label info_reservas;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<ModelEmprestimo> emprestimos = new ArrayList<>();
        List<ModelKey> chaves = new ArrayList<>();
        List<ModelUser> users = new ArrayList<>();
        List<ModelDevolucao> devolucoes = new ArrayList<>();
       
        try {
            //lista de emprestimos
            EmprestarDAO banco_emprestimos = new EmprestarDAO();
            emprestimos = banco_emprestimos.listar();
            int cont_emprestimos = 0;
            for(int i=0;i<emprestimos.size();i++){
                cont_emprestimos++;
            }
            String valor_emprestimos = Integer.toString(cont_emprestimos);
            this.info_emprestimos.setText(valor_emprestimos);
            
            //lista de chaves
            KeyDao banco_chaves = new KeyDao();
            chaves = banco_chaves.chavesDisponivel();
            int cont_chaves = 0;
            for(int i=0;i<chaves.size();i++){
                cont_chaves++;
            }
            String valor_chaves = Integer.toString(cont_chaves);
            this.info_chaves.setText(valor_chaves);
            
            //lista de usuarios
            UserDAO banco_user = new UserDAO();
            users = banco_user.listar();
            int cont_users = 0;
            for(int i=0;i<users.size();i++){
                cont_users++;
            }
            String valor_user = Integer.toString(cont_users);
            this.info_usuarios.setText(valor_user);
            
            //lista de devolucoes
            DevolucaoDao banco_devolucao = new DevolucaoDao();
            devolucoes = banco_devolucao.listar();
            int cont_devolucoes = 0;
            for(int i=0;i<devolucoes.size();i++){
                cont_devolucoes++;
            }
            String valor_devolucoes = Integer.toString(cont_devolucoes);
            this.info_devolucoes.setText(valor_devolucoes);
            
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Métodos dos botões da home.
    //Chama a tela do keyPage.
    @FXML
    private void keyPage(javafx.event.ActionEvent event) throws Exception {
        Key chave = new Key();
        
        try {
            chave.start(new Stage());
            Home.getStage().close();
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Chama a tela do adminPage.
    @FXML
    private void AdminPage(javafx.event.ActionEvent event) {
        Admin admin = new Admin();
        try {
            admin.start(new Stage());
            Home.getStage().close();
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Chama a tela do userPage.
    @FXML
    private void UserPage(javafx.event.ActionEvent event) {
        User user = new User();
        try {
            user.start(new Stage());
            Home.getStage().close();
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void EmprestimosPage(ActionEvent event) {
        Loan loan = new Loan();
        
        try {
            loan.start(new Stage());
            Home.getStage().close();
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
