/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

//Importação das classes EditUser, RegisterUser e do Model User e do DAO User.
import Classe.EditUser;
import Classe.Home;
import Classe.KeyPermission;
import Classe.RegisterUser;
import Classe.User;
import DAO.UserDAO;
import Model.ModelUser;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Samuel Lima
 */
public class UserController implements Initializable {

    //Ids das colunas da tabela.
    @FXML
    private TableView<ModelUser> table_users;
    @FXML
    private TableColumn<ModelUser, Integer> id;
    @FXML
    private TableColumn<ModelUser, String> col_nome;
    @FXML
    private TableColumn<ModelUser, Long> col_matricula;
    @FXML
    private TableColumn<ModelUser, String> col_tipo_usuario;
    @FXML
    private TableColumn<ModelUser, String> col_email;
    @FXML
    private TextField buscar_user;
    @FXML
    private ImageView btn_buscar;

    private Model.ModelUser selected;

    private ObservableList<ModelUser> usuarios = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            //Inicializa a tela de usuários.
            initTable();
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        buscar_user.setOnKeyReleased((KeyEvent) -> {
            table_users.setItems(buscar());
        });

        btn_buscar.setOnMouseClicked((MouseEvent) -> {
            table_users.setItems(buscar());
        });

        //Função para verificar a linha selecionada na tabela.
        table_users.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                selected = (Model.ModelUser) newValue;
            }
        });
    }

    //Método de cadastrar usuário.
    @FXML
    void cadastrarUsuario(javafx.event.ActionEvent event) {
        RegisterUser user = new RegisterUser();

        try {
            user.start(new Stage());
            User.getStage().close();
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Método de editar usuário.
    @FXML
    private void editarUsuario(ActionEvent event) {
        if (selected != null) {
            EditUser edit = new EditUser(selected);
            try {
                edit.start(new Stage());
                User.getStage().close();
            } catch (Exception ex) {
                Logger.getLogger(KeyController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setHeaderText("Selecione um usuário clicando sobre o mesmo para edita-lo.");
            alerta.show();
        }
    }

    //Método de excluir usuário.
    @FXML
    private void excluirUsuario(ActionEvent event) throws SQLException, Exception {
        if (selected != null) {
            UserDAO deletar = new UserDAO();
            int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir " + selected.getNome() + "?","Deseja excluir a chave?", JOptionPane.YES_NO_OPTION);
            
            if(resposta == JOptionPane.YES_OPTION){
                try {
                    deletar.excluir(selected);
                    User.getStage().close();

                    User newFrame = new User();
                    newFrame.start(new Stage());
                } catch (SQLException ex) {
                    Alert alerta = new Alert(Alert.AlertType.WARNING);
                    alerta.setTitle("Cuidado!");
                    alerta.setHeaderText("O Usuario não pode ser excluido!.");
                    alerta.setContentText("Consulte o Administrador do sistema para resolver isso!");
                    alerta.show();
                    
                    Logger.getLogger(KeyController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setHeaderText("Selecione um usuário clicando sobre o mesmo!");
            alerta.show();
        }
    }

    @FXML
    private void permitirChave(ActionEvent event) {
        if (selected != null) {
            if (selected.getTipo_user().equals("Bolsista") || selected.getTipo_user().equals("Estagiário")) {
                KeyPermission permission = new KeyPermission(selected);
                try {
                    User.getStage().close();
                    permission.start(new Stage());
                } catch (Exception ex) {
                    Logger.getLogger(KeyPermissionController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuario do tipo SERVIDOR tem acesso a qualquer chave!");
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setHeaderText("Selecione um usuário clicando sobre o mesmo para dá permissão para pegar uma chave.");
            alerta.show();
        }
    }

    //Função para set os valores das colunas da tabela.
    public void initTable() throws SQLException {
        id.setCellValueFactory((new PropertyValueFactory("id")));
        col_nome.setCellValueFactory(new PropertyValueFactory("nome"));
        col_email.setCellValueFactory(new PropertyValueFactory("email"));
        col_matricula.setCellValueFactory(new PropertyValueFactory("matricula"));
        col_tipo_usuario.setCellValueFactory(new PropertyValueFactory("tipo_user"));
        table_users.setItems(atualizaTable());
    }

    //Função ObservableList para listar os campos da tabela.
    public ObservableList<Model.ModelUser> atualizaTable() throws SQLException {
        UserDAO userdao = new UserDAO();
        usuarios = FXCollections.observableArrayList(userdao.listar());
        return usuarios;
    }

    public ObservableList<ModelUser> buscar() {
        ObservableList<ModelUser> userFiltrada = FXCollections.observableArrayList();

        for (int i = 0; i < usuarios.size(); i++) {
            String matricula = Long.toString(usuarios.get(i).getMatricula());
            if (usuarios.get(i).getNome().toLowerCase().contains(buscar_user.getText().toLowerCase()) || matricula.contains(buscar_user.getText())) {
                userFiltrada.add(usuarios.get(i));
            }
        }
        return userFiltrada;
    }

    @FXML
    private void backPage(ActionEvent event) throws Exception {
        User.getStage().close();

        Home newframe = new Home();
        newframe.start(new Stage());
    }
}
