package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.impl.UserServiceImpl;

import java.io.IOException;

public class LoginFormController {

    @FXML private TextField txtEmail;
    @FXML private PasswordField txtPassword;
    @FXML private JFXButton txtSignIn;


    private final UserServiceImpl userService;

    public LoginFormController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @FXML
    void btnSignIn(ActionEvent event) {
        String email = txtEmail.getText();
        String password = txtPassword.getText();

        int status = userService.loginUser(email, password);

        switch (status) {
            case 1:
                navigateTo("/view/Dashboard.fxml", "Dashboard");
                break;
            case 0:
                showAlert(Alert.AlertType.CONFIRMATION, "Account Not Found", "Please sign up to continue.");
                break;
            case -1:
                showAlert(Alert.AlertType.ERROR, "Invalid Email", "Only @gmail.com accounts are allowed.");
                break;
            case -2:
                showAlert(Alert.AlertType.ERROR, "Login Failed", "Incorrect password.");
                break;
        }
    }

    private void navigateTo(String fxmlPath, String title) {
        try {
            Stage stage = (Stage) txtSignIn.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(fxmlPath))));
            stage.setTitle(title);
            stage.centerOnScreen();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Navigation Error", "Could not load " + title);
        }
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}