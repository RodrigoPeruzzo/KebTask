package sample.Controllers;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import sample.Classes.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class ControllerHome extends Pane implements javafx.fxml.Initializable {
    @FXML
    private Button btnSairHome;
    @FXML
    private TableView<Tasks> tvTarefas;
    @FXML
    private Label lbNomeUser;
    @FXML
    private ImageView ImageHome;

    private final ObservableList<Tasks> data = FXCollections.observableArrayList();

    public String Filtro = "";
    public static int IdTaskTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AtualizaTela();

        ControllerImagePerfil.AtualizaTabela = false;

        try {
            ResultSet rs = new ControlaMySQL().Select("SELECT kt_usuario.nome_usuario, kt_usuario.id_imagem FROM kt_usuario WHERE id_usuario = " + Sessao.getIdUsuario());

            while (rs.next()){
                if (!rs.getString(1).isEmpty()){
                    lbNomeUser.setText(rs.getString(1));
                    ImageHome.setImage(new GetImagens().ImagemUsuario(rs.getInt(2)));
                } else {
                    lbNomeUser.setText("Desconhecido");
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void handleBtnSairHomeAction(ActionEvent actionEvent) {
        Sessao.setIdUsuario(0);

        new AbreTela().Login();
        btnSairHome.getScene().getWindow().hide();
    }

    public void handleBtnAdicionarHomeAction(ActionEvent actionEvent){
        new AbreTela().Adicionar(new Callback(){

            @Override
            public void onFiltroDefinido(String bla) {
                AtualizaTela();
            }
        });

        AtualizaTela();
    }

    public void handleBtnConsultarHomeAction(ActionEvent actionEvent){
        new AbreTela().Consulta(new Callback() {
            @Override
            public void onFiltroDefinido(String bla) {
                Filtro = bla;

                AtualizaTela();
            }
        });
    }

    public interface Callback{
        void onFiltroDefinido(String bla);
    }

    public void AtualizaTela(){
        ControlaMySQL cms = new ControlaMySQL();

        int idAtual = 1;

        ResultSet rs = cms.Select("SELECT id_task, titulo_task, data_prevista FROM kt_task WHERE id_usuario = " + Sessao.getIdUsuario() + " " + Filtro + " ORDER BY data_prevista DESC");

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        data.clear();

        try {
            while (rs.next()){
                data.add(new Tasks(rs.getInt(1),idAtual,rs.getString(2),dateFormat.format(rs.getDate(3)).toString() + " " + ((rs.getTimestamp(3).getHours() < 10) ? "0" + String.valueOf(rs.getTimestamp(3).getHours()) : String.valueOf(rs.getTimestamp(3).getHours())) + ":" + ((rs.getTimestamp(3).getMinutes() < 10) ? "0" + String.valueOf(rs.getTimestamp(3).getMinutes()) : String.valueOf(rs.getTimestamp(3).getMinutes()))));

                idAtual++;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        tvTarefas.setItems(data);
    }

    public void onMouseTvTarefas(MouseEvent mouseEvent) {
        try {
            if (mouseEvent.isPrimaryButtonDown() && mouseEvent.getClickCount() == 2) {
                Tasks tasks = (Tasks) tvTarefas.getSelectionModel().getSelectedItem();
                IdTaskTable = tasks.getId_Table();

                new AbreTela().Tarefa(new Callback() {
                    @Override
                    public void onFiltroDefinido(String bla) {
                        AtualizaTela();
                    }
                });
            }
        } catch (NullPointerException e){
            System.out.println("");
        }
    }

    public void handleMouseClickedImageHome(MouseEvent mouseEvent){
        ControllerImagePerfil.AtualizaTabela = true;
        new AbreTela().ImagePerfil();
        ImageHome.getScene().getWindow().hide();
    }
}