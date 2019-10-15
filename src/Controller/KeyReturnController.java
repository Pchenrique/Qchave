/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Classe.KeyReturn;
import Classe.Loan;
import DAO.AdminDao;
import DAO.DevolucaoDao;
import DAO.EmprestarDao;
import DAO.KeyDao;
import DAO.UserDao;
import Model.ModelAdmin;
import Model.ModelDevolucao;
import Model.ModelEmprestimo;
import Model.ModelKey;
import Model.ModelUser;
import Validacoes.Validacoes;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Samuel Lima
 */
public class KeyReturnController implements Initializable {
    
    @FXML
    private TextField token_administrador;
    @FXML
    private Button btn_emprestar_chave;
    @FXML
    private Label label_Nome_Responsavel;
    @FXML
    private Label label_Nome_Chave;
    
    private static Model.ModelEmprestimo selected;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            KeyDao banco_key = new KeyDao();
            UserDao banco_user = new UserDao();
            ModelKey key = banco_key.buscarKey(selected.getId_chave());
            ModelUser user = banco_user.buscarUser(selected.getId_user());
            
            this.label_Nome_Chave.setText(key.getNome_sala());
            this.label_Nome_Responsavel.setText(user.getNome());
        } catch (SQLException ex) {
            Logger.getLogger(KeyReturnController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }    

    @FXML
    private void confirmDevolucao(ActionEvent event) throws SQLException {
        if(this.token_administrador.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Preencha o campo Token!");
        }else if(this.token_administrador.getText().matches(Validacoes.regexCaracteres())){
            JOptionPane.showMessageDialog(null, "O campo token não pode ter caracteres especiais!");   
        }else if(this.token_administrador.getText().matches(Validacoes.regexLetras())){
            JOptionPane.showMessageDialog(null, "O campo token não pode ter letras!");   
        }else{
            AdminDao daoAdmin = new AdminDao();
            List<ModelAdmin> listaAmin = new ArrayList();
            listaAmin = daoAdmin.listar();
            
            int identificador = 0;
            for(int i=0; i < listaAmin.size(); i++){
                if(listaAmin.get(i).getToken() == Integer.parseInt(this.token_administrador.getText())){
                    identificador++;
                }
            }
            if(identificador > 0){
                KeyDao deletar = new KeyDao();

                int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente finalizar o emprestimo da sala " + selected.getNome_chave() + "?","Finalizar emprestimo?", JOptionPane.YES_NO_OPTION);

                if(resposta == JOptionPane.YES_OPTION){
                    DevolucaoDao banco = new DevolucaoDao();
                    AdminDao banco_admindao = new AdminDao();
                    EmprestarDao banco_emprestar = new EmprestarDao();

                    ModelAdmin admin = banco_admindao.buscarAdmin(Integer.parseInt(this.token_administrador.getText()));

                    ModelDevolucao devolucao = new ModelDevolucao(admin.getId(), selected.getId(), selected.getNome_usuario(), selected.getNome_chave(), selected.getNome_admin());
                    banco.inserirDevolucao(devolucao);


                    selected.setStatus("Encerrado");
                    banco_emprestar.editar(selected, selected.getId());

                    KeyDao banco_keydao = new KeyDao();
                    ModelKey key = banco_keydao.buscarKey(selected.getId_chave());
                    key.setStatus("Disponivel");

                    KeyDao banco_keydao2 = new KeyDao();
                    banco_keydao2.editar(key, key.getId());

                    JOptionPane.showMessageDialog(null, "Chave Devolvida com Sucesso!");

                    KeyReturn.getStage().close();
                    Loan newFrame = new Loan();
                    try {
                        newFrame.start(new Stage());
                    } catch (Exception ex) {
                        Logger.getLogger(KeyReturnController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }else{
                JOptionPane.showMessageDialog(null, "Token Invalido!");
            }
        }
    }
    
    public static ModelEmprestimo getSelected() {
        return selected;
    }

    public static void setSelected(ModelEmprestimo selected) {
       KeyReturnController.selected = selected;
    }
    @FXML
    private void backPage(ActionEvent event) {
        KeyReturn.getStage().close();
        
        Loan newFrame = new Loan();
        
        try {
            newFrame.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(KeyReturnController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
