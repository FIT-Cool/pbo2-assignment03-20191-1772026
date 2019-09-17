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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {
    public DatePicker date;
    public TableColumn col04;

    public ObservableList<Category> getCategories() {
        if (categories==null)categories= FXCollections.observableArrayList();
        return categories;
    }

    public ObservableList<Category> categories;
    List<String> myCategory = new ArrayList<>();
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
    private TextField txtID;
    @FXML
    private Button update;
    @FXML
    private TextField txtCategoryName;
    @FXML
    private ChoiceBox choiceBox;
    private int index;


    @FXML
    private void tambahAction(ActionEvent actionEvent) {
        try {
            if (txtNama.getText().trim().isEmpty() ||
                    txtID.getText().trim().isEmpty() ||

                    choiceBox.getSelectionModel().isEmpty()) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Please fill id/name/category/date");
                a.show();
            } else {
                Item i = new Item();
                Category c = new Category();
                c.setName(choiceBox.getValue().toString());
                i.setNama(txtNama.getText().trim());
                i.setPrice(txtID.getText().trim());
                i.setCategory(c);
                item.add(i);
            }

        } catch (Exception e) {
        }


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        item = FXCollections.observableArrayList();
        categories=FXCollections.observableArrayList(getCategories());
        tableItem.setItems(item);
        choiceBox.setItems(categories);
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
        txtCategoryName.setText("");
        txtNama.setText("");
        txtID.setText("");
        update.setDisable(true);
    }

    @FXML
    private void update(ActionEvent actionEvent) {
        Item i = tableItem.getSelectionModel().getSelectedItem();
        Category c = new Category();
        c.setName(choiceBox.getValue().toString());
        i.setNama(txtNama.getText());
        i.setPrice(txtID.getText());
        i.setCategory(c);
        tableItem.getItems().set(index, i);

    }


    @FXML
    private void pilihTabel(MouseEvent mouseEvent) {
        try {
            update.setDisable(false);
            Item i = tableItem.getSelectionModel().getSelectedItem();
            index = tableItem.getSelectionModel().getSelectedIndex();
            choiceBox.setValue(i.getCategory().getName());
            txtNama.setText(i.getNama());
            txtID.setText(i.getPrice());
        } catch (Exception e) {
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
