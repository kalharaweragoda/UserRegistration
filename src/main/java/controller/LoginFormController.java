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
import java.util.regex.Pattern;

public class LoginFormController {

    @FXML private TextField txtEmail;
    @FXML private PasswordField txtPassword;
    @FXML private JFXButton txtSignIn;

    private final UserServiceImpl userService;

    // Gmail regex pattern
    private static final Pattern GMAIL_PATTERN =
            Pattern.compile("^[a-zA-Z0-9._%+-]+@gmail\\.com$");

    public LoginFormController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @FXML
    void btnSignIn(ActionEvent event) {

        String email = txtEmail.getText().trim();
        String password = txtPassword.getText();

        if (email.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.WARNING,
                    "Validation Error", "Please fill all fields.");
            return;
        }

        if (!GMAIL_PATTERN.matcher(email).matches()) {
            showAlert(Alert.AlertType.ERROR,
                    "Invalid Email", "Only valid @gmail.com emails are allowed.");
            return;
        }

        int status = userService.loginUser(email, password);

        switch (status) {
            case 1:
                navigateTo("/view/Dashboard.fxml", "Dashboard");
                break;

            case 0:
                showAlert(Alert.AlertType.CONFIRMATION,
                        "Account Not Found", "Please sign up to continue.");
                break;

            case -2:
                showAlert(Alert.AlertType.ERROR,
                        "Login Failed", "Incorrect password.");
                break;

            default:
                showAlert(Alert.AlertType.ERROR,
                        "Login Error", "Unexpected error occurred.");
        }
    }

    private void navigateTo(String fxmlPath, String title) {
        try {
            Stage stage = (Stage) txtSignIn.getScene().getWindow();
            stage.setScene(new Scene(
                    FXMLLoader.load(getClass().getResource(fxmlPath))
            ));
            stage.setTitle(title);
            stage.centerOnScreen();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR,
                    "Navigation Error", "Could not load " + title);
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
