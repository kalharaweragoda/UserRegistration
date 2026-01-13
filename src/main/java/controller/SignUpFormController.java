package controller;

import com.jfoenix.controls.JFXButton;
import dto.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import service.impl.UserServiceImpl;

public class SignUpFormController {

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtReenterPassword;

    @FXML
    private JFXButton txtRegister;

    @FXML
    private JFXButton txtBackToLogin;

    private final UserServiceImpl userService = new UserServiceImpl();

    @FXML
    void btnRegister(ActionEvent event) {

        User user = new User(
                null,
                txtFirstName.getText(),
                txtLastName.getText(),
                txtEmail.getText(),
                txtPassword.getText(),
                txtReenterPassword.getText()
        );

        if (user.getFirstName().isEmpty() ||
                user.getLastName().isEmpty() ||
                user.getEmail().isEmpty() ||
                user.getPassword().isEmpty() ||
                user.getReenterPassword().isEmpty()) {

            showAlert(Alert.AlertType.WARNING, "All fields required");
            return;
        }

        if (!user.getPassword().equals(user.getReenterPassword())) {
            showAlert(Alert.AlertType.ERROR, "Passwords do not match");
            return;
        }

        try {
            userService.registerUser(user);
            showAlert(Alert.AlertType.INFORMATION, "Registration Successful");
            clearFields();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    private void clearFields() {
        txtFirstName.clear();
        txtLastName.clear();
        txtEmail.clear();
        txtPassword.clear();
        txtReenterPassword.clear();
    }

    private void showAlert(Alert.AlertType type, String msg) {
        new Alert(type, msg).show();
    }
}
