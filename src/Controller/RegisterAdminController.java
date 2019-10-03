/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

//Importação do Admin DAO e do Model Admin.
import Classe.Admin;
import Classe.RegisterAdmin;
import DAO.AdminDao;
import Model.ModelAdmin;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Samuel Lima
 */
public class RegisterAdminController implements Initializable {

    //Ids dos campos de textfield e botão de cadastrar administrador.
    @FXML
    private TextField nome_completo;
    @FXML
    private TextField cpf;
    @FXML
    private Button btn_cadastrar_admin;
    @FXML
    private TextField token;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    //Método de registrar administrador.
    @FXML
    private void registrarAdmin(ActionEvent event) throws SQLException, Exception {

        if (this.nome_completo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo nome está vazio!");
        } else if (this.cpf.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo cpf está vazio!");
        } else if(this.token.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "O campo token está vazio!");
        }else {
            try{
                String name = this.nome_completo.getText();
                long cpf = Long.parseLong(this.cpf.getText());
                int token = Integer.parseInt(this.token.getText());

                try{
                    ModelAdmin admin = new ModelAdmin(name, cpf, token);
                    AdminDao admindao = new AdminDao();
                    admindao.inserirAdmin(admin);

                    JOptionPane.showMessageDialog(null, "Dados cadastrados com sucesso!");

                    RegisterAdmin.getStage().close();

                    Admin newFrame = new Admin();
                    newFrame.start(new Stage());
                }catch(SQLException e){
                    AdminDao admindao = new AdminDao();
                    List<ModelAdmin> lista = new ArrayList();
                    lista = admindao.listar();
                    for(int i=0;i<lista.size();i++){
                        if(cpf == lista.get(i).getCpf()){
                            JOptionPane.showMessageDialog(null, "CPF já está cadastrados!");
                        }else if(token == lista.get(i).getToken()){
                            JOptionPane.showMessageDialog(null, "TOKEN já está cadastrados!");
                        }
                    }
                    Logger.getLogger(KeyController.class.getName()).log(Level.SEVERE, null, e);
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Os campos CPF e TOKEN tem que ser números!");
                Logger.getLogger(KeyController.class.getName()).log(Level.SEVERE, null, e);
            }
                  
        }

    }

    @FXML
    private void backPage(ActionEvent event) throws Exception {
        RegisterAdmin.getStage().close();

        Admin newFrame = new Admin();
        newFrame.start(new Stage());
    }
}
