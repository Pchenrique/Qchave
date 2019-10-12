package Controller;

import Classe.LoanReport;
import Classe.Report;
import DAO.EmprestarDao;
import Model.ModelEmprestimo;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Samuel Lima
 */
public class LoanReportController implements Initializable {
    
    @FXML
    private TableView<ModelEmprestimo> table_relatorio_emprestimo;
    @FXML
    private TableColumn<ModelEmprestimo, String> col_chave;
    @FXML
    private TableColumn<ModelEmprestimo, String> col_responsavel;
    @FXML
    private TableColumn<ModelEmprestimo, String> col_emprestada_por;
    @FXML
    private TableColumn<ModelEmprestimo, String> col_status;
    @FXML
    private TableColumn<ModelEmprestimo, String> col_data;
    @FXML
    private TextField campoBuscar;
    @FXML
    private Button btnBuscar;
    
    private ObservableList<ModelEmprestimo> emprestimos = FXCollections.observableArrayList();
   
    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            initTable();
        } catch (SQLException ex) {
            Logger.getLogger(LoanReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        campoBuscar.setOnKeyReleased((KeyEvent)->{
            table_relatorio_emprestimo.setItems(buscar());
        });
        
        btnBuscar.setOnMouseClicked((MouseEvent)->{
            table_relatorio_emprestimo.setItems(buscar());
        });
        
    }    
    
     public void initTable() throws SQLException {
        col_chave.setCellValueFactory(new PropertyValueFactory("nome_chave"));
        col_responsavel.setCellValueFactory(new PropertyValueFactory("nome_usuario"));
        col_emprestada_por.setCellValueFactory(new PropertyValueFactory("nome_admin"));
        col_status.setCellValueFactory(new PropertyValueFactory("status"));
        col_data.setCellValueFactory(new PropertyValueFactory("data_emprestimo"));

        table_relatorio_emprestimo.setItems(atualizaTable());
    }

    //Função ObservableList para listar os campos da tabela.
    public ObservableList<ModelEmprestimo> atualizaTable() throws SQLException {
        EmprestarDao emprestardao = new EmprestarDao();
        this.emprestimos = FXCollections.observableArrayList(emprestardao.listarTudo());
        return emprestimos;
    }
    
    public ObservableList<ModelEmprestimo> buscar(){
        ObservableList<ModelEmprestimo> emprestimoFiltrada = FXCollections.observableArrayList();
        
        for(int i=0; i<emprestimos.size();i++){
            if(emprestimos.get(i).getNome_chave().toLowerCase().contains(campoBuscar.getText().toLowerCase()) || emprestimos.get(i).getNome_usuario().toLowerCase().contains(campoBuscar.getText().toLowerCase()) || emprestimos.get(i).getData_emprestimo().contains(campoBuscar.getText())){
                 emprestimoFiltrada.add(emprestimos.get(i));
            }
        }
        return emprestimoFiltrada;
    }
     
    @FXML
    private void backPage(ActionEvent event) {
        LoanReport.getStage().close();
        
        Report newFrame = new Report();
        try {
            newFrame.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(LoanReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
