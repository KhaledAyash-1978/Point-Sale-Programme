package org.example;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private Label lbUsername;
    @FXML
    private JFXButton btnManageTable;

    Scene fxmlFile;
    Parent root;
    Stage window;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    public void setUsername(String username) {
        lbUsername.setText(username);
    }

    public void manageTable(ActionEvent actionEvent) {
        try {
            openModalWindow("Tables.fxml", "Manage Table");
        } catch (Exception ex) {

        }
    }
    public void manageProduct(ActionEvent actionEvent) {
        try {
            openModalWindow("products.fxml", "Manage Products");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void openModalWindow(String resource, String title) throws IOException {
        root = FXMLLoader.load(getClass().getResource(resource));
        fxmlFile = new Scene(root);
        window = new Stage();
        window.setScene(fxmlFile);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setAlwaysOnTop(true);
        window.setIconified(false);
        //window.initStyle(StageStyle.UNDECORATED);
        window.setTitle(title);
        window.showAndWait();
    }


}
