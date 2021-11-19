package org.example;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryController implements Initializable {

    @FXML
    private JFXButton btn_Login;

    @FXML
    private HBox hbox_user, hbox_password;
    @FXML
    private Label label_user, label_password, label_login;
    @FXML
    private ImageView usericon;
    @FXML
    private VBox vbox_all;
    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;


    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    public static void showAlert(Alert.AlertType alertType, Window owner, String Message, String title) {
        Alert alert = new Alert(alertType);
        alert.setContentText(Message);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.initOwner(owner);
        alert.show();
    }


    @FXML
    private void login(ActionEvent event) {
        Window owner = txtUsername.getScene().getWindow();
        System.out.println(txtUsername.getText());
        System.out.println(txtPassword.getText());
        if (txtUsername.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Please enter a valid username", "Form error !");
            return;
        }
        if (txtPassword.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Please enter a valid password", "Form Error !");
            return;
        }


        String username = txtUsername.getText();
        String password = txtPassword.getText();
        JdbcDao JdbcDao = new JdbcDao();
        boolean flag = JdbcDao.validate(username, password);
        if (!flag) {
            infoBox("Please enter correct username and password", null, "Failed");
        } else {
            infoBox("Login successful !", null, "Success");
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dashbord.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("POS | Dashboard");
                stage.setScene(new Scene(root));
                DashboardController controller = fxmlLoader.getController();
                controller.setUsername(username);
                stage.show();
                ((Node) (event.getSource())).getScene().getWindow().hide();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
