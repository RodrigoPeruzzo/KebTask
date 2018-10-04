package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.w3c.dom.Text;
import sample.Classes.ControlaMySQL;
import sample.Classes.MensagemPersonalizada;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerTarefa implements javafx.fxml.Initializable {
    @FXML
    TextField txTituloTarefa;
    @FXML
    DatePicker dtDataPrevistaTarefa;
    @FXML
    TextArea taDescricaoTarefa;
    @FXML
    Button btnAlterarTarefa;
    @FXML
    Button btnRemoverTarefa;
    @FXML
    Button btnVoltarTarefa;
    @FXML
    Button btnConfirmarTarefa;
    @FXML
    Button btnCancelarTarefa;
    @FXML
    ComboBox cbTarefaHora;
    @FXML
    ComboBox cbTarefaMinuto;

    ControllerHome.Callback callback;

    int IdTarefa;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> ListHora = FXCollections.observableArrayList("00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23");
        ObservableList<String> ListMinuto = FXCollections.observableArrayList("00","05","10","15","20","25","30","35","40","45","50","55");

        cbTarefaHora.setItems(ListHora);
        cbTarefaMinuto.setItems(ListMinuto);

        btnConfirmarTarefa.setVisible(false);
        btnCancelarTarefa.setVisible(false);

        AtualizaTarefa();
    }

    public void handleBtnAlterarAction(){
        AtivaDesativaEdicao(1);
    }

    public void handleBtnRemoverAction(){
        if (new MensagemPersonalizada().EmitePerguntaObjetiva("Exclusão de tarefa","Deseja efetuar a exclusão da tarefa?")){
            AtivaDesativaEdicao(2);
            new ControlaMySQL().Delete("DELETE FROM kt_task WHERE id_task = " + ControllerHome.IdTaskTable);

            if(callback != null){
                callback.onFiltroDefinido("");
            }

            btnConfirmarTarefa.getScene().getWindow().hide();

            new MensagemPersonalizada().EmiteMensagem("Exclusão de tarefa","Tarefa cancelada com sucesso.","info");
        }
    }

    public void handleBtnVoltarAction(){
        btnConfirmarTarefa.getScene().getWindow().hide();
    }

    public void handleBtnConfirmarTarefaAction(){
        new ControlaMySQL().Update("UPDATE kt_task SET titulo_task = '" + txTituloTarefa.getText() + "', descricao_task = '" + taDescricaoTarefa.getText() + "', data_prevista = '" + dtDataPrevistaTarefa.getValue().toString() + " " + cbTarefaHora.getValue().toString() + ":" + cbTarefaMinuto.getValue().toString() + ":00' WHERE id_task = " + ControllerHome.IdTaskTable);
        new MensagemPersonalizada().EmiteMensagem("Alteração de tarefa","Tarefa alterada com sucesso.","info");
        AtivaDesativaEdicao(2);

        if(callback != null){
            callback.onFiltroDefinido("");
        }
    }

    public void handleBtnCancelarTarefaAction(){
        AtivaDesativaEdicao(2);
        AtualizaTarefa();
    }

    private void AtivaDesativaEdicao(int tipo){
        boolean TipoUm = true;
        boolean TipoDois = false;

        switch (tipo){
            case 1:
                TipoUm = true;
                TipoDois = false;
                break;
            case 2:
                TipoUm = false;
                TipoDois = true;
                break;
        }

        txTituloTarefa.setEditable(TipoUm);
        dtDataPrevistaTarefa.setEditable(false);
        taDescricaoTarefa.setEditable(TipoUm);
        cbTarefaHora.setEditable(false);
        cbTarefaMinuto.setEditable(false);
        btnAlterarTarefa.setVisible(TipoDois);
        btnRemoverTarefa.setVisible(TipoDois);
        btnVoltarTarefa.setVisible(TipoDois);
        btnConfirmarTarefa.setVisible(TipoUm);
        btnCancelarTarefa.setVisible(TipoUm);
    }

    private void AtualizaTarefa(){
        try {
            ResultSet rs = new ControlaMySQL().Select("SELECT titulo_task, descricao_task, data_prevista FROM kt_task WHERE id_task = " + ControllerHome.IdTaskTable);

            while (rs.next()) {
                txTituloTarefa.setText(rs.getString(1));
                taDescricaoTarefa.setText(rs.getString(2));
                dtDataPrevistaTarefa.setValue(rs.getDate(3).toLocalDate());

                cbTarefaHora.setValue((rs.getTimestamp(3).getHours() < 10) ? "0" + String.valueOf(rs.getTimestamp(3).getHours()) : String.valueOf(rs.getTimestamp(3).getHours()));
                cbTarefaMinuto.setValue((rs.getTimestamp(3).getMinutes() < 10) ? "0" + String.valueOf(rs.getTimestamp(3).getMinutes()) : String.valueOf(rs.getTimestamp(3).getMinutes()));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void setCallback(ControllerHome.Callback callback) {
        this.callback = callback;
    }

    public void OnMouseClickedCBTarefaMinuto(javafx.scene.input.MouseEvent mouseEvent){
        if (!txTituloTarefa.isEditable() && cbTarefaMinuto.isShowing()){
            cbTarefaMinuto.hide();
        }
    }

    public void OnMouseClickedCBTarefaHora(javafx.scene.input.MouseEvent mouseEvent){
        if (!txTituloTarefa.isEditable() && cbTarefaHora.isShowing()){
            cbTarefaHora.hide();
        }
    }

    public void OnMouseClickedDataPrevista(javafx.scene.input.MouseEvent mouseEvent) {
        if (!txTituloTarefa.isEditable() && dtDataPrevistaTarefa.isShowing()){
            dtDataPrevistaTarefa.hide();
        }
    }
}
