package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardFormController {

    @FXML
    private Label txtlabel;



    @FXML
    private JFXButton txtlogin;

    @FXML
    void btnLogIn(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();


        Stage loginStage = new Stage();
        loginStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/login_Form.fxml"))));
        loginStage.setTitle("Login");
        loginStage.show();
    }

}
