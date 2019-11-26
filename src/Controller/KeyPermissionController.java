/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Classe.KeyPermission;
import Classe.KeysAllowed;
import DAO.KeyDao;
import DAO.KeyPermissionDao;
import Model.ModelKey;
import Model.ModelKeyPermission;
import Model.ModelUser;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Samuel Lima
 */
public class KeyPermissionController implements Initializable {

    @FXML
    private Button btn_permitir_chave;
    @FXML
    private Label nome_usuario;
    @FXML
    private Label matricula_usuario;
    @FXML
    private ComboBox<ModelKey> CbChavePermitida;
    
    private static Model.ModelUser selected;

    public static ModelUser getSelected() {
        return selected;
    }

    public static void setSelected(ModelUser selected) {
        KeyPermissionController.selected = selected;
    }
    @FXML
    private TextField text_chave;
    
    private ObservableList<ModelKey> chaves = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Inicialização do ComboBox.
        this.nome_usuario.setText(selected.getNome());
        
        this.matricula_usuario.setText(Long.toString(selected.getMatricula()));
                
        try {
            BuscarListaDeChaves();   
        } catch (SQLException ex) {
            Logger.getLogger(KeyPermissionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        text_chave.setOnKeyReleased((KeyEvent)->{
            CbChavePermitida.setItems(buscarChave());
        });
    }    

    //Função para pegar a lista de chaves do BD e popular o ComboBox.
    public void BuscarListaDeChaves() throws SQLException{
        CbChavePermitida.setItems(atualizaListChave());
    }
    
    public ObservableList<ModelKey> atualizaListChave() throws SQLException {
        KeyDao keydao = new KeyDao();
        this.chaves = FXCollections.observableArrayList(keydao.listar());
        return chaves;
    }
    
    //Função para pegar o valor selecionado no ComboBox.
     public ModelKey pegarChave(){
        ModelKey chaveParaPermissao = CbChavePermitida.getSelectionModel().getSelectedItem();
        return chaveParaPermissao;
    }
     
     public ObservableList<ModelKey> buscarChave(){
        ObservableList<ModelKey> chaveFiltrada = FXCollections.observableArrayList();
        
        for(int i=0; i<chaves.size();i++){
            if(chaves.get(i).getNome_sala().toLowerCase().contains(text_chave.getText().toLowerCase()) || chaves.get(i).getCod_sala().equals(text_chave.getText())){
                chaveFiltrada.add(chaves.get(i));
            }
        }
        return chaveFiltrada;
    }

    @FXML
    private void concederPermissao(ActionEvent event) throws SQLException, Exception{
        KeyPermissionDao banco_permission_total = new KeyPermissionDao();
        List<ModelKeyPermission> lista = new ArrayList();
        
        if(this.CbChavePermitida.getSelectionModel().isEmpty()){
            JOptionPane.showMessageDialog(null, "Selecione uma chave para emprestar");
        }else{
            lista = banco_permission_total.listarPorUsuario(selected.getId());
            int cont =0;
            for(int i=0; i < lista.size(); i++){
                if(lista.get(i).getNome_chave().equals(pegarChave().getNome_sala())){
                    cont++;
                }
            }
            
            if(cont > 0){
                JOptionPane.showMessageDialog(null, "A chave já foi permitida para esse usuario!");
            }else{
                try{
                    KeyPermissionDao banco_permission = new KeyPermissionDao();
                    ModelKey key = pegarChave();
                    ModelKeyPermission keyP = new ModelKeyPermission(selected.getId(), key.getId(), key.getNome_sala(), selected.getNome());

                    banco_permission.inserirPermission(keyP);

                    JOptionPane.showMessageDialog(null, "Chave "+key.getNome_sala().toUpperCase()+" Permitida para "+selected.getNome().toUpperCase()+"");

                    KeyPermission.getStage().close();

                    KeysAllowed newFrame = new KeysAllowed();
                    newFrame.start(new Stage());
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, "ERRO! Por favor consulte o admistrador");
                    Logger.getLogger(KeyPermissionController.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }
    
    @FXML
    private void backPage(ActionEvent event) {
        KeyPermission.getStage().close();
        
        KeysAllowed newFrame = new KeysAllowed();
        try {
            newFrame.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(KeyPermissionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
}
