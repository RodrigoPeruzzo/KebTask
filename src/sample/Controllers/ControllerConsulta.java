package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ControllerConsulta {
    @FXML
    private Button btnPesquisar;
    @FXML
    private TextField txTituloConsulta;
    @FXML
    private DatePicker dtCricaoConsulta;
    @FXML
    private DatePicker dtPrevistaConsulta;

    ControllerHome.Callback callback;
    public void handleBtnPesquisarAction(javafx.event.ActionEvent actionEvent) {
        String filtro = "";

        if (!txTituloConsulta.getText().isEmpty()){
            filtro += "AND titulo_task LIKE '%" + txTituloConsulta.getText() + "%' ";
        }

        if (dtCricaoConsulta.getValue() != null) {
            if (!dtCricaoConsulta.getValue().toString().isEmpty()) {
                filtro += "AND data_criacao >= '" + dtCricaoConsulta.getValue().toString() + " 00:00:00' AND data_criacao <= '" + dtCricaoConsulta.getValue().toString() + " 23:59:59' ";
            }
        }

        if (dtPrevistaConsulta.getValue() != null) {
            if (!dtPrevistaConsulta.getValue().toString().isEmpty()) {
                filtro += "AND data_prevista >= '" + dtPrevistaConsulta.getValue().toString() + " 00:00:00' AND data_prevista <= '" + dtPrevistaConsulta.getValue().toString() + " 23:59:59' ";

            }
        }
        if(callback != null){
            callback.onFiltroDefinido(filtro);
        }

        btnPesquisar.getScene().getWindow().hide();
    }

    public void setCallback(ControllerHome.Callback callback) {
        this.callback = callback;
    }
}
