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
import Classe.Report;
import Classe.Reserve;
import Classe.User;
import DAO.DevolucaoDao;
import DAO.EmprestarDao;
import DAO.KeyDao;
import DAO.ReservaDao;
import DAO.UserDao;
import Model.ModelDevolucao;
import Model.ModelEmprestimo;
import Model.ModelKey;
import Model.ModelReservas;
import Model.ModelUser;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Samuel Lima
 */
public class HomeController implements Initializable {

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
    @FXML
    private Label info_chaves_total;
    @FXML
    private ImageView img_sol;
    @FXML
    private Label mensagem;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try{
            Image noite = new Image("Images/moon.png");
            Image dia_tarde = new Image("Images/sunny.png");
            Image dia_manha = new Image("Images/sunrise.png");
            LocalDateTime agora = LocalDateTime.now();

            DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH");
            String horaFormatada = formatterHora.format(agora);
            int hora = Integer.parseInt(horaFormatada);

            if(hora > 00 && hora <= 12){
                mensagem.setText("Bom Dia!");
                img_sol.setImage(dia_manha);
            }else if(hora > 12 && hora <= 18){
                mensagem.setText("Boa Tarde!");
                img_sol.setImage(dia_tarde);
            }else if(hora > 18 && hora <= 00){
                mensagem.setText("Boa Noite!");
                img_sol.setImage(noite);
            }


            List<ModelEmprestimo> emprestimos = new ArrayList<>();
            List<ModelKey> chaves = new ArrayList<>();
            List<ModelKey> chaves2 = new ArrayList<>();
            List<ModelUser> users = new ArrayList<>();
            List<ModelDevolucao> devolucoes = new ArrayList<>();
            List<ModelReservas> reservas = new ArrayList<>();

            try {
                //lista de emprestimos
                EmprestarDao banco_emprestimos = new EmprestarDao();
                emprestimos = banco_emprestimos.listar();
                int cont_emprestimos = 0;
                for (int i = 0; i < emprestimos.size(); i++) {
                    cont_emprestimos++;
                }
                String valor_emprestimos = Integer.toString(cont_emprestimos);
                this.info_emprestimos.setText(valor_emprestimos);

                //lista de chaves disponiveis
                KeyDao banco_chaves = new KeyDao();
                chaves = banco_chaves.chavesDisponivel();
                int cont_chaves = 0;
                for (int i = 0; i < chaves.size(); i++) {
                    cont_chaves++;
                }
                String valor_chaves = Integer.toString(cont_chaves);
                this.info_chaves.setText(valor_chaves);

                //Lista de chaves totais
                KeyDao banco_chaves2 = new KeyDao();
                chaves2 = banco_chaves2.listar();
                int cont_chaves_total = 0;
                for (int i = 0; i < chaves2.size(); i++) {
                    cont_chaves_total++;
                }
                String valor_chaves2 = Integer.toString(cont_chaves_total);
                this.info_chaves_total.setText(valor_chaves2);

                //Lista de Reservas
                ReservaDao banco_reservas = new ReservaDao();
                reservas = banco_reservas.listaDisponivel();
                int cont_reservas_total = 0;
                for (int i = 0; i < reservas.size(); i++) {
                    cont_reservas_total++;
                }
                String valor_reservas = Integer.toString(cont_reservas_total);
                this.info_reservas.setText(valor_reservas);

                //lista de usuarios
                UserDao banco_user = new UserDao();
                users = banco_user.listar();
                int cont_users = 0;
                for (int i = 0; i < users.size(); i++) {
                    cont_users++;
                }
                String valor_user = Integer.toString(cont_users);
                this.info_usuarios.setText(valor_user);

                //lista de devolucoes
                DevolucaoDao banco_devolucao = new DevolucaoDao();
                devolucoes = banco_devolucao.listar();
                int cont_devolucoes = 0;
                for (int i = 0; i < devolucoes.size(); i++) {
                    cont_devolucoes++;
                }
                String valor_devolucoes = Integer.toString(cont_devolucoes);
                this.info_devolucoes.setText(valor_devolucoes);

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro no banco!");
                Home.getStage().close();
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro no banco!");
            Home.getStage().close();
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, e);
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

    @FXML
    private void gerarRelatorio(ActionEvent event) {
        Report report = new Report();
        
        try {
            report.start(new Stage());
            Home.getStage().close();
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void reservarChave(ActionEvent event) {
        Reserve reserve = new Reserve();
        
        try {
            reserve.start(new Stage());
            Home.getStage().close();
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
