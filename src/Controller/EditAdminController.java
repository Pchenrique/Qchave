/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

//Importação da classe admin, do DAO admin e do model admin.
import Classe.Admin;
import Classe.EditAdmin;
import DAO.AdminDao;
import Model.ModelAdmin;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Samuel Lima
 */
public class EditAdminController implements Initializable {

    //Ids dos campos de textfield e do botao de alterar.
    @FXML
    private TextField nomeCompleto;
    @FXML
    private TextField cpf;
    @FXML
    private Button btnAlterar;

    private static Model.ModelAdmin admin2;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //inicialização dos campos do administrador preenchidos com seus respectivos valores.
        initAdmin();
    }

    //Método de alterar administrador.
    @FXML
    private void alterarAdministrador(ActionEvent event) throws SQLException, Exception {

        int id = admin2.getId();

        if (this.nomeCompleto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo nome está vazio!");
        } else if (this.cpf.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo cpf está vazio!");
        } else {
            try{
                String nome_completo = this.nomeCompleto.getText();
                String cpf = this.cpf.getText();
                try{
                    ModelAdmin admin = new ModelAdmin(nome_completo, cpf);

                    AdminDao admindao = new AdminDao();

                    admindao.editar(admin, id);
                    JOptionPane.showMessageDialog(null, "Dados Alterados com Sucesso!");
                    EditAdmin.getStage().close();
                    openAdmin();
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, "O CPF já existe!");
                    Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, e);
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "O CPF tem que ser número!");
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    //Função para preencher os campos do form do admin.
    public void initAdmin() {
        nomeCompleto.setText(admin2.getNome());
        cpf.setText(admin2.toString());
    }

    public static ModelAdmin getAdmin2() {
        return admin2;
    }

    public static void setAdmin2(ModelAdmin admin2) {
        EditAdminController.admin2 = admin2;
    }

    //função para abrir a adminPage após alterar os dados.
    public void openAdmin() throws Exception {
        Admin admin = new Admin();
        admin.start(new Stage());
    }

    @FXML
    private void backPage(ActionEvent event) throws Exception {
        EditAdmin.getStage().close();

        Admin newFrame = new Admin();
        newFrame.start(new Stage());
    }
}
