package com.steven.controller;

import com.steven.entity.Category;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Steven Rumanto
 * 1772026
 */

public class CategoryManagementController implements Initializable {

    public TextField txtID;
    public TextField txtNama;
    @FXML
    private TableView tableItem;
    @FXML
    private TableColumn<Category, String> col01;
    @FXML
    private TableColumn<Category, String> col02;
    private MainFormController mainFormController;
    @FXML
    private TableView<Category> tableCategory;



    public void save(ActionEvent actionEvent) {
        Category c = new Category();
        try {
            if (txtNama.getText().trim().isEmpty() && txtID.getText().trim().isEmpty()) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Please fill category name");
                a.show();
            } else {
                boolean cek = false;
                for (Category ctr : mainFormController.getCategories()) {
                    if (ctr.getName().trim().contains(txtNama.getText().trim())) cek = true;
                }
                if (!cek) {
                    c.setName(txtNama.getText().trim());
                    c.setId(Integer.valueOf(txtID.getText().trim()));
                    mainFormController.getCategories().add(c);
                } else {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setContentText("Duplicate Category Name!");
                    a.show();
                }
            }
        }catch (Exception e){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Field ID insert number please..");
            a.show();
        }

    }

    public void pilihTabel(MouseEvent mouseEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        col01.setCellValueFactory((data) -> {
            Category c = data.getValue();
            return new SimpleStringProperty(c.getId());
        });
        col02.setCellValueFactory((data) -> {
            Category c = data.getValue();
            return new SimpleStringProperty(c.getName());
        });
    }

    public void setMainFormController(MainFormController mainFormController) {
        this.mainFormController = mainFormController;
        tableCategory.setItems(mainFormController.getCategories());
    }
}
