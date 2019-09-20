package com.steven.controller;

import com.steven.entity.Category;
import com.steven.entity.Item;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {
    public DatePicker date;


    public ObservableList<Category> getCategories() {
        if (categories == null) categories = FXCollections.observableArrayList();
        return categories;
    }

    private ObservableList<Category> categories;
    @FXML
    private ObservableList<Item> item;
    @FXML
    private TableView<Item> tableItem;
    @FXML
    private TextField txtNama;
    @FXML
    private TableColumn<Item, String> col01;
    @FXML
    private TableColumn<Item, String> col02;
    @FXML
    private TableColumn<Item, String> col03;
    @FXML
    private TableColumn<Item, String> col04;
    @FXML
    private TextField txtID;
    @FXML
    private Button update;
    @FXML
    private ChoiceBox choiceBox;
    private int index;


    @FXML
    private void tambahAction(ActionEvent actionEvent) {
        try {
            if (txtNama.getText().trim().isEmpty() ||
                    txtID.getText().trim().isEmpty() ||
                    choiceBox.getSelectionModel().isEmpty()
                    || date.getValue() == null
            ) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Please fill id/name/category/date");
                a.show();
            } else {
                Item i = new Item();
                i.setNama(txtNama.getText().trim());
                i.setPrice(txtID.getText().trim());
                i.setCategory((Category) choiceBox.getValue());

                i.setTanggal(date.getValue().format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
                item.add(i);
            }

        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Field ID insert number please..");
            a.show();
        }


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        item = FXCollections.observableArrayList();
        tableItem.setItems(item);
        choiceBox.setItems(getCategories());
        col01.setCellValueFactory((data) -> {
            Item f = data.getValue();
            return new SimpleStringProperty(f.getNama());
        });
        col02.setCellValueFactory((data) -> {
            Item f = data.getValue();
            return new SimpleStringProperty(f.getPrice());
        });
        col03.setCellValueFactory((data) -> {
            Item f = data.getValue();
            return new SimpleStringProperty(f.getCategory().getName());
        });
        col04.setCellValueFactory((data) -> {
            Item f = data.getValue();
            return new SimpleStringProperty(f.getTanggal());
        });
    }


    @FXML
    private void keluar(ActionEvent actionEvent) {
        Platform.exit();
    }

    @FXML
    private void tentang(ActionEvent actionEvent) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("Steven Rumanto \n1772026");
        a.show();
    }

    @FXML
    private void reset(ActionEvent actionEvent) {
        date.setValue(null);
        choiceBox.setValue(null);
        txtNama.setText("");
        txtID.setText("");
        update.setDisable(true);
    }

    @FXML
    private void update(ActionEvent actionEvent) {

        try {
            Item i = tableItem.getSelectionModel().getSelectedItem();
//            c.setName(choiceBox.getValue().toString());
            i.setNama(txtNama.getText());
            i.setPrice(txtID.getText());
            i.setCategory((Category) choiceBox.getValue());
            i.setTanggal(date.getValue().format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
            tableItem.getItems().set(index, i);
        } catch (Exception e) {

        }
    }


    @FXML
    private void pilihTabel(MouseEvent mouseEvent) {
        try {
            update.setDisable(false);
            ZoneId defaultZoneId = ZoneId.systemDefault();
            SimpleDateFormat formatter1=new SimpleDateFormat("dd MMM yyyy");

            Item i = tableItem.getSelectionModel().getSelectedItem();
            index = tableItem.getSelectionModel().getSelectedIndex();
            Date isidate=formatter1.parse(i.getTanggal());
            Instant instant=isidate.toInstant();
            LocalDate isidataAkhir=instant.atZone(defaultZoneId).toLocalDate();
            choiceBox.setValue(i.getCategory());
            txtNama.setText(i.getNama());
            txtID.setText(i.getPrice());
            date.setValue(isidataAkhir);
        } catch (Exception e) {
//            update.setDisable(true);
        }


    }

    public void openCategory(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/CategoryManagement.fxml"));
            BorderPane root = loader.load();
            CategoryManagementController controller = loader.getController();
            controller.setMainFormController(this);

            Stage mainStage = new Stage();
            mainStage.setTitle("Category Management");
            mainStage.setScene(new Scene(root));
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
