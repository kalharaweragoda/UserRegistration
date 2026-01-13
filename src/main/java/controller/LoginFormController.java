package controller;

import entity.UserEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.UserService;
import service.impl.UserServiceImpl;

public class LoginFormController {

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    private final UserService userService = new UserServiceImpl();

    @FXML
    void btnLogin(ActionEvent event) {

        String email = txtEmail.getText();
        String password = txtPassword.getText();

        if (email.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Email and Password required");
            return;
        }

        if (!email.matches("^[A-Za-z0-9._%+-]+@gmail\\.com$")) {
            showAlert(Alert.AlertType.ERROR, "Email must end with @gmail.com");
            return;
        }

        UserEntity user = userService.authenticate(email, password);

        if (user == null) {
            showAlert(Alert.AlertType.INFORMATION,
                    "User not found. Please create an account");
            navigate("/view/signup_form.fxml");
            return;
        }

        showAlert(Alert.AlertType.INFORMATION, "Login Successful");
        navigate("/view/dashboard_form.fxml");
    }


    private void navigate(String path) {
        try {
            Stage stage = (Stage) txtEmail.getScene().getWindow();
            stage.setScene(new Scene(
                    FXMLLoader.load(getClass().getResource(path))
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType type, String msg) {
        new Alert(type, msg).show();
    }
}
