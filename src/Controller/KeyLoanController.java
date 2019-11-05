package Controller;

import Classe.Key;
import Classe.KeyLoan;
import DAO.AdminDao;
import DAO.EmprestarDao;
import DAO.KeyDao;
import DAO.KeyPermissionDao;
import DAO.UserDao;
import Model.ModelAdmin;
import Model.ModelEmprestimo;
import Model.ModelKey;
import Model.ModelKeyPermission;
import Model.ModelUser;
import Validacoes.Validacoes;
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
 * @author Paulo Cesar
 */
public class KeyLoanController implements Initializable {

    @FXML
    private TextField token_administrador;
    @FXML
    private Button btn_emprestar_chave;
    @FXML
    private Label Label_Id_Chave;
    @FXML
    private Label Label_nome_chave;
    @FXML
    private ComboBox<ModelUser> lista_usuarios;

    private static Model.ModelKey selected;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.Label_Id_Chave.setText(Integer.toString(selected.getId()));

        this.Label_nome_chave.setText(selected.getNome_sala());

        try {
            BuscarListaDeUsuarios();
        } catch (SQLException ex) {
            Logger.getLogger(KeyPermissionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Função para pegar a lista de usuarios do BD e popular o ComboBox.
    public void BuscarListaDeUsuarios() throws SQLException {
        UserDao userdao = new UserDao();
        ObservableList<ModelUser> users = FXCollections.observableArrayList(userdao.listarOrdenado());
        lista_usuarios.setItems(users);
    }

    @FXML
    private void confirmEmprestimo(ActionEvent event) throws SQLException, Exception {
        EmprestarDao banco = new EmprestarDao();
        AdminDao banco_admindao = new AdminDao();
        KeyDao banco_key = new KeyDao();
        
        if(this.lista_usuarios.getSelectionModel().isEmpty()){
            JOptionPane.showMessageDialog(null, "O campo usuario precisar ser selecionado!");
        }else if (this.token_administrador.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo TOKEN!");
        } else if (this.token_administrador.getText().matches(Validacoes.regexLetras())) {
            JOptionPane.showMessageDialog(null, "O Campo token não pode ter letras!");
        } else if (this.token_administrador.getText().matches(Validacoes.regexCaracteres())) {
            JOptionPane.showMessageDialog(null, "O Campo token não pode ter caracteres especiais!");
        } else {
            ModelUser usuario = lista_usuarios.getSelectionModel().getSelectedItem();
            AdminDao listaAdminDao = new AdminDao();
            List<ModelAdmin> listaAdmin = new ArrayList();
            listaAdmin = listaAdminDao.listar();
            int indentificar = 0;
            for(int i=0; i < listaAdmin.size(); i++){
                if(listaAdmin.get(i).getToken() == Integer.parseInt(this.token_administrador.getText())){
                    indentificar++;
                }
            }
            
            if(indentificar > 0){
                ModelAdmin admin = banco_admindao.buscarAdmin(Integer.parseInt(this.token_administrador.getText()));
                
                    if (usuario.getTipo_user().equals("Servidor")) {
                        try {
                            ModelEmprestimo emprestimo = new ModelEmprestimo(usuario.getId(), usuario.getNome(), selected.getId(), selected.getNome_sala(), admin.getId(), admin.getNome());
                            banco.inserirEmprestimo(emprestimo);

                            selected.setStatus("Emprestada");
                            banco_key.editar(selected, selected.getId());

                            JOptionPane.showMessageDialog(null, "Chave Emprestada com sucesso!");
                            KeyLoan.getStage().close();
                            Key newFrame = new Key();
                            newFrame.start(new Stage());
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, "ERRO na base de dados!");
                            Logger.getLogger(KeyLoanController.class.getName()).log(Level.SEVERE, null, e);
                        }
                    } else {
                        KeyPermissionDao banco_chavesPermitidas = new KeyPermissionDao();
                        List<ModelKeyPermission> keysPermission = new ArrayList<>();
                        keysPermission = banco_chavesPermitidas.listarPorUsuario(usuario.getId());

                        for (int i = 0; i < keysPermission.size(); i++) {
                            if (selected.getId() == keysPermission.get(i).getId_chave()) {
                                try {
                                    ModelEmprestimo emprestimo = new ModelEmprestimo(usuario.getId(), usuario.getNome(), selected.getId(), selected.getNome_sala(), admin.getId(), admin.getNome());
                                    banco.inserirEmprestimo(emprestimo);

                                    selected.setStatus("Emprestada");
                                    banco_key.editar(selected, selected.getId());

                                    JOptionPane.showMessageDialog(null, "Chave Emprestada com sucesso!");
                                    KeyLoan.getStage().close();
                                    Key newFrame = new Key();
                                    newFrame.start(new Stage());
                                } catch (SQLException e) {
                                    JOptionPane.showMessageDialog(null, "ERRO na base de dados!");
                                }
                            }
                        }
                        if (selected.getStatus().equals("Disponivel")) {
                            JOptionPane.showMessageDialog(null, "O usuario " + usuario.getNome().toUpperCase() + " Não tem permissão de Pegar a chave " + selected.getNome_sala().toUpperCase() + "");
                        }
                    }
            }else{
                JOptionPane.showMessageDialog(null, "Token Incorreto!");
            }
        }
    }

    public static ModelKey getSelected() {
        return selected;
    }

    public static void setSelected(ModelKey selected) {
        KeyLoanController.selected = selected;
    }

    @FXML
    private void backPage(ActionEvent event) throws Exception {
        KeyLoan.getStage().close();

        Key newFrame = new Key();
        newFrame.start(new Stage());
    }
}
