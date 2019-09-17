package com.steven.controller;

import com.steven.entity.Category;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

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
        c.setName(txtNama.getText().trim());
        c.setId(Integer.valueOf(txtID.getText().trim()));
        mainFormController.getCategories().add(c);
//        if (txtCategoryName.getText().trim().isEmpty()) {
//            Alert a = new Alert(Alert.AlertType.ERROR);
//            a.setContentText("Please fill category name");
//            a.show();
//        } else {
//            boolean cek = false;
//            for (String str : myCategory) {
//                if (str.trim().contains(txtCategoryName.getText().trim())) cek = true;
//            }
//            if (!cek) {
//                myCategory.add(txtCategoryName.getText().trim());
//                choiceBox.setItems(FXCollections.observableArrayList(myCategory));
//            } else {
//                Alert a = new Alert(Alert.AlertType.ERROR);
//                a.setContentText("Duplicate Category Name!");
//                a.show();
//            }
//        }
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
