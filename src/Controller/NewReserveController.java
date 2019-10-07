/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Classe.NewReserve;
import Classe.Reserve;
import DAO.AdminDao;
import DAO.KeyDao;
import DAO.ReservaDao;
import DAO.UserDAO;
import Model.ModelAdmin;
import Model.ModelKey;
import Model.ModelReservas;
import Model.ModelUser;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Samuel Lima
 */
public class NewReserveController implements Initializable {

    @FXML
    private ComboBox<ModelUser> select_servidor;
    @FXML
    private ComboBox<ModelKey> select_chave;
    @FXML
    private DatePicker data_saida;
    @FXML
    private DatePicker data_entrada;
    @FXML
    private TextField hora_saida;
    @FXML
    private TextField hora_entrada;
    @FXML
    private TextField token_administrador;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        try {
            BuscarListaDeUsuarios();
            BuscarListaDeChaves();
        } catch (SQLException ex) {
            Logger.getLogger(KeyPermissionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void BuscarListaDeUsuarios() throws SQLException{
        UserDAO userdao = new UserDAO();
        ObservableList<ModelUser> users = FXCollections.observableArrayList(userdao.listarServidor());
        select_servidor.setItems(users);
    }
    public void BuscarListaDeChaves() throws SQLException{
        KeyDao keydao = new KeyDao();
        ObservableList<ModelKey> chaves = FXCollections.observableArrayList(keydao.listar());
        select_chave.setItems(chaves);
    }

    @FXML
    private void reservarChave(ActionEvent event) throws SQLException {
        ReservaDao buscarlista = new ReservaDao();
        List<ModelReservas> listadereservas = new ArrayList();
        listadereservas = buscarlista.listaDisponivel();
        int cont=0;
        for(int i=0;i<listadereservas.size();i++){
            if(listadereservas.get(i).getId_usuario() == select_servidor.getSelectionModel().getSelectedItem().getId() && listadereservas.get(i).getId_chave() == select_chave.getSelectionModel().getSelectedItem().getId()){
               cont++; 
            }
        }
        
        if(cont > 0){
            JOptionPane.showMessageDialog(null, "Essa reserva ja foi realizada!");
        }else{
            ReservaDao reservadao = new ReservaDao();
            AdminDao admindao = new AdminDao();

            int token = Integer.parseInt(token_administrador.getText());
            ModelAdmin admin = admindao.buscarAdmin(token);

            int id_user = select_servidor.getSelectionModel().getSelectedItem().getId();
            String nome_user = select_servidor.getSelectionModel().getSelectedItem().getNome();
            int id_chave = select_chave.getSelectionModel().getSelectedItem().getId();
            String nome_chave = select_chave.getSelectionModel().getSelectedItem().getNome_sala();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String data_saida_format = data_saida.getValue().format(formatter);

            String data_entrada_format = data_entrada.getValue().format(formatter);

            ModelReservas reserva = new ModelReservas(id_user, nome_user, id_chave,nome_chave, admin.getId(), admin.getNome(), data_saida_format,hora_saida.getText(), data_entrada_format, hora_entrada.getText());
            reservadao.inserirUser(reserva);
            JOptionPane.showMessageDialog(null, "Reserva feita com sucesso!");
        }
    }

    @FXML
    private void backPage(ActionEvent event) {
        NewReserve.getStage().close();
        
        Reserve newFrame = new Reserve();
        try {
            newFrame.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(NewReserveController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
