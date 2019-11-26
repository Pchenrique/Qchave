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
import DAO.UserDao;
import Model.ModelAdmin;
import Model.ModelKey;
import Model.ModelReservas;
import Model.ModelUser;
import Validacoes.Validacoes;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
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
    private ComboBox<String> hora_saida;
    @FXML
    private ComboBox<String> hora_entrada;
    @FXML
    private TextField token_administrador;
    @FXML
    private TextField text_servidor;
    @FXML
    private TextField text_chave;
    
    private ObservableList<ModelUser> users = FXCollections.observableArrayList();
    
    private ObservableList<ModelKey> chaves = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            BuscarListaDeUsuarios();
            BuscarListaDeChaves();
            selectHora_saida();
            selectHora_Entrada();
        } catch (SQLException ex) {
            Logger.getLogger(KeyPermissionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        text_servidor.setOnKeyReleased((KeyEvent)->{
            select_servidor.setItems(buscarUser());
        });
        
        text_chave.setOnKeyReleased((KeyEvent)->{
            select_chave.setItems(buscarChave());
        });
        
    }

    public void selectHora_saida() {
        ObservableList<String> options = FXCollections.observableArrayList("7:20", "8:20", "9:40", "10:40", "13:00", "14:00", "15:20", "16:20", "18:20", "19:10", "20:20", "21:10");
        hora_saida.setItems(options);
    }

    public void selectHora_Entrada() {
        ObservableList<String> options = FXCollections.observableArrayList("8:20", "9:20", "10:40", "11:40", "14:00", "15:00", "16:20", "17:20", "19:10", "20:00", "21:10", "22:00");
        hora_entrada.setItems(options);
    }

    public void BuscarListaDeUsuarios() throws SQLException {
        select_servidor.setItems(atualizaListUser());
    }

    public void BuscarListaDeChaves() throws SQLException {
        select_chave.setItems(atualizaListChave());
    }
    
    public ObservableList<ModelUser> atualizaListUser() throws SQLException {
        UserDao userdao = new UserDao();
        this.users = FXCollections.observableArrayList(userdao.listarServidor());
        return users;
    }
    
    public ObservableList<ModelKey> atualizaListChave() throws SQLException {
        KeyDao keydao = new KeyDao();
        this.chaves = FXCollections.observableArrayList(keydao.listar());
        return chaves;
    }
    
    public ObservableList<ModelUser> buscarUser(){
        ObservableList<ModelUser> usuarioFiltrado = FXCollections.observableArrayList();
        
        for(int i=0; i<users.size();i++){
            if(users.get(i).getNome().toLowerCase().contains(text_servidor.getText().toLowerCase())){
                usuarioFiltrado.add(users.get(i));
            }
        }
        return usuarioFiltrado;
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
    private void reservarChave(ActionEvent event) throws SQLException, ParseException {

        if (select_servidor.getSelectionModel().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O usuario não foi selecionado!");
        } else if (select_chave.getSelectionModel().isEmpty()) {
            JOptionPane.showMessageDialog(null, "A chave não foi selecionada!");
        } else if (hora_saida.getSelectionModel().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo hora saida precisa ser selecionado!");
        } else if (hora_entrada.getSelectionModel().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo hora entrada precisa ser selecionado!");
        } else if (token_administrador.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo token não pode ser vazio!");
        } else if (token_administrador.getText().matches(Validacoes.regexLetras())) {
            JOptionPane.showMessageDialog(null, "O campo token não pode ter letras!");
        } else if (token_administrador.getText().matches(Validacoes.regexCaracteres())) {
            JOptionPane.showMessageDialog(null, "O campo token não pode ter caracteres especiais!");
        } else {
            
            ReservaDao buscarlista = new ReservaDao();
            List<ModelReservas> listadereservas = new ArrayList();
            listadereservas = buscarlista.listaDisponivel();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String data_saida_format = data_saida.getValue().format(formatter);
            String data_entrada_format = data_entrada.getValue().format(formatter);
            
            int contR = 0;
            int contRR = 0;
            for(int i = 0; i < listadereservas.size(); i++) {
                if (listadereservas.get(i).getId_usuario() == select_servidor.getSelectionModel().getSelectedItem().getId() && listadereservas.get(i).getId_chave() == select_chave.getSelectionModel().getSelectedItem().getId() && listadereservas.get(i).getData_saida().equals(data_saida_format) && listadereservas.get(i).getHora_saida().equals(hora_saida.getSelectionModel().getSelectedItem()) && listadereservas.get(i).getData_devolucao().equals(data_entrada_format) && listadereservas.get(i).getHora_devolucao().equals(hora_entrada.getSelectionModel().getSelectedItem())) {
                    contR++;
                }
                if ((listadereservas.get(i).getId_chave() == select_chave.getSelectionModel().getSelectedItem().getId() && listadereservas.get(i).getData_saida().equals(data_saida_format) && listadereservas.get(i).getHora_saida().equals(hora_saida.getSelectionModel().getSelectedItem())) || (listadereservas.get(i).getId_chave() == select_chave.getSelectionModel().getSelectedItem().getId() && listadereservas.get(i).getData_devolucao().equals(data_entrada_format) && listadereservas.get(i).getHora_devolucao().equals(hora_entrada.getSelectionModel().getSelectedItem()))) {
                    contRR++;
                }
            }

            if (contR > 0) {
                JOptionPane.showMessageDialog(null, "Essa reserva ja foi realizada!");
            } else if (contRR > 0) {
                JOptionPane.showMessageDialog(null, "Outro Usuario ja reservou essa chave nesse horario!");
            } else {
                ReservaDao reservadao = new ReservaDao();
                AdminDao admindao = new AdminDao();
                
                int token = Integer.parseInt(token_administrador.getText());
                ModelAdmin admin = admindao.buscarAdmin(token);
                
                AdminDao dao = new AdminDao();
                List<ModelAdmin> lista = new ArrayList();
                lista = dao.listar();
                int confirm=0;
                for(int i=0; i < lista.size(); i++){
                    if(lista.get(i).getToken() == token){
                       confirm++;
                    }
                }
                
                int id_user = select_servidor.getSelectionModel().getSelectedItem().getId();
                String nome_user = select_servidor.getSelectionModel().getSelectedItem().getNome();
                int id_chave = select_chave.getSelectionModel().getSelectedItem().getId();
                String nome_chave = select_chave.getSelectionModel().getSelectedItem().getNome_sala();
                
                if(confirm > 0){
                    ModelReservas reserva = new ModelReservas(id_user, nome_user, id_chave, nome_chave, admin.getId(), admin.getNome(), data_saida_format, hora_saida.getSelectionModel().getSelectedItem(), data_entrada_format, hora_entrada.getSelectionModel().getSelectedItem());
                    reservadao.inserirUser(reserva);
      
                    if(select_chave.getSelectionModel().getSelectedItem().getStatus().equals("Reservada")){

                    }else{
                       KeyDao keydao = new KeyDao();
                       ModelKey chave = select_chave.getSelectionModel().getSelectedItem();
                       chave.setStatus("Reservada");

                       keydao.editar(chave, chave.getId()); 
                    }

                    JOptionPane.showMessageDialog(null, "Reserva feita com sucesso!");
                    
                    NewReserve.getStage().close();
 
                    Reserve newFrame = new Reserve();
                    try {
                        newFrame.start(new Stage());
                    } catch (Exception ex) {
                        Logger.getLogger(NewReserveController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Token Invalido!");
                }
            }
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
