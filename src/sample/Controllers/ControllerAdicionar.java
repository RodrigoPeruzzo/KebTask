package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.Classes.ControlaMySQL;
import sample.Classes.MensagemPersonalizada;
import sample.Classes.Sessao;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class ControllerAdicionar implements javafx.fxml.Initializable {
    @FXML
    private Button btnAdicionar;
    @FXML
    private TextField txTituloAdd;
    @FXML
    private DatePicker dtDataPrevista;
    @FXML
    private TextArea taDescricao;
    @FXML
    private ComboBox cbHora;
    @FXML
    private ComboBox cbMinuto;

    ControllerHome.Callback callback;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> ListHora = FXCollections.observableArrayList("00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23");
        ObservableList<String> ListMinuto = FXCollections.observableArrayList("00","05","10","15","20","25","30","35","40","45","50","55");

        cbHora.setItems(ListHora);
        cbMinuto.setItems(ListMinuto);

        Date date = new Date();

        cbHora.setValue(date.getHours());

        String stMinuto = String.valueOf(date.getMinutes());

        if (Integer.parseInt(stMinuto) >= 10){
            if (Integer.parseInt(stMinuto.substring(1,2)) < 5){
                cbMinuto.setValue(stMinuto.substring(0,1) + "0");
            } else {
                cbMinuto.setValue(stMinuto.substring(0,1) + "5");
            }
        } else {
            cbMinuto.setValue("0" + stMinuto);
        }

        dtDataPrevista.setValue(LocalDate.now());
    }

    public void handleBtnAdicionarAction(javafx.event.ActionEvent actionEvent) {
        if (txTituloAdd.getText().isEmpty()){
            new MensagemPersonalizada().EmiteMensagem("Informação obrigatória","Título da tarefa deve ser informado.","warning");
            return;
        }

        if (dtDataPrevista.getValue() == null){
            new MensagemPersonalizada().EmiteMensagem("Informação obrigatória","Data prevista deve ser informada.","warning");
            return;
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        boolean retorno = new ControlaMySQL().Inserir("INSERT INTO kt_task (titulo_task, descricao_task,data_criacao,data_prevista,id_usuario) VALUES ('" + txTituloAdd.getText() + "','" + taDescricao.getText() + "','" + dateFormat.format(date).toString() + "','" + dtDataPrevista.getValue().toString() + " " + cbHora.getValue() + ":" + cbMinuto.getValue() + ":00'," + Sessao.getIdUsuario() + ")");

        String stMes = (dtDataPrevista.getValue().getMonthValue() < 10) ? "0" + dtDataPrevista.getValue().getMonthValue() : String.valueOf(dtDataPrevista.getValue().getMonthValue());
        String stDia = (dtDataPrevista.getValue().getDayOfMonth() < 10) ? "0" + dtDataPrevista.getValue().getDayOfMonth() : String.valueOf(dtDataPrevista.getValue().getDayOfMonth());

        String DatFormatada = stDia + "/" + stMes + "/" + dtDataPrevista.getValue().getYear();

        if (retorno){
            new MensagemPersonalizada().EmiteMensagem("Nova tarefa adicionada","Sua tarefa para o dia " + DatFormatada + " foi adicionada com sucesso.","info");

            if(callback != null){
                callback.onFiltroDefinido("");
            }

            btnAdicionar.getScene().getWindow().hide();
        } else {
            new MensagemPersonalizada().EmiteMensagem("Falha ao adicionar tarefa","Não foi possível adicionar sua tarefa.","error");
            return;
        }
    }

    public void setCallback(ControllerHome.Callback callback) {
        this.callback = callback;
    }
}
