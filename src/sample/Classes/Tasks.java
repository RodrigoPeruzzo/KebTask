package sample.Classes;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

public class Tasks {
    private SimpleIntegerProperty id_table;
    private SimpleIntegerProperty id;
    private SimpleStringProperty descricao;
    private SimpleStringProperty data;

    public Tasks(){}

    public Tasks(int id_table, int id, String descricao, String data) {
        this.id_table  = new SimpleIntegerProperty(id_table);
        this.id        = new SimpleIntegerProperty(id);
        this.descricao = new SimpleStringProperty(descricao);
        this.data      = new SimpleStringProperty(data);
    }

    public int getId_Table() {
        return id_table.get();
    }

    public void setId_Table(int id_table) {
        this.id_table.set(id_table);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getDescricao() {
        return descricao.get();
    }

    public void setDescricao(String descricao) {
        this.descricao.set(descricao);
    }

    public String getData() {
        return data.get();
    }

    public void setData(String data) {
        this.data.set(data);
    }
}
