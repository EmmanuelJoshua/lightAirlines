/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightservice;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import flightservice.validation.Validator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Spark
 */
public class FlightServiceFXMLController implements Initializable {
    
    @FXML
    private Pane testB;

    @FXML
    private JFXSpinner spinner;

    @FXML
    private AnchorPane rootpane;

    @FXML
    private StackPane pane1;

    @FXML
    private StackPane pane2;

    @FXML
    private ImageView gear1;

    @FXML
    private ImageView gear2;

    @FXML
    private ImageView enter;

    @FXML
    private JFXTextField userName;

    @FXML
    private JFXPasswordField passWord;

    @FXML
    private Pane pane;

    @FXML
    private Pane exitPane;

    @FXML
    private void hoverEnterEffect(MouseEvent event) {
        exitPane.setVisible(true);
    }

    @FXML
    private void hoverExitEffect(MouseEvent event) {
        exitPane.setVisible(false);
    }

    @FXML
    private void enterEffect(MouseEvent event) {
        pane.setVisible(true);
    }

    @FXML
    private void exitEffect(MouseEvent event) {
        pane.setVisible(false);
    }

    @FXML
    private void start(ActionEvent event) {

        if (userName.getText().isEmpty() && passWord.getText().isEmpty()) {
            JFXDialogLayout content = new JFXDialogLayout();
            content.setHeading(new Text("ERROR"));
            content.setBody(new Text("Please Provide Your Username And Password!"));
            JFXDialog dialog = new JFXDialog(pane1, content, JFXDialog.DialogTransition.CENTER);
            JFXButton button = new JFXButton("Okay");
            button.setBackground(testB.getBackground());
            button.setButtonType(JFXButton.ButtonType.RAISED);
            button.setOnAction((eventA) -> {
                dialog.close();
            });
            content.setActions(button);
            dialog.show();
        } else if (!userLogging(userName.getText(), passWord.getText())) {
            JFXDialogLayout content = new JFXDialogLayout();
            content.setHeading(new Text("ERROR"));
            content.setBody(new Text("Invalid Login"));
            JFXDialog dialog = new JFXDialog(pane2, content, JFXDialog.DialogTransition.CENTER);
            JFXButton button = new JFXButton("Okay");
            button.setBackground(testB.getBackground());
            button.setButtonType(JFXButton.ButtonType.RAISED);
            button.setOnAction((eventA) -> {
                dialog.close();
            });
            content.setActions(button);
            dialog.show();
        } else {
            enter.setVisible(false);
            spinner.setVisible(true);
            makeFade();
        }

    }

    @FXML
    private void exitAction(MouseEvent event) {
        System.exit(0);
    }

    public void makeFade() {
        FadeTransition fade = new FadeTransition();
        fade.setDelay(Duration.millis(3000));
        fade.setDuration(Duration.millis(1000));
        fade.setNode(rootpane);
        fade.setFromValue(Double.MAX_VALUE);
        fade.setToValue(Double.MIN_VALUE);
        fade.setAutoReverse(true);

        fade.setOnFinished((ActionEvent event) -> {
            nextScene();
        });
        fade.play();
    }

    public void nextScene() {
        try {
            Parent view;
            view = (AnchorPane) FXMLLoader.load(getClass().getResource("BookingApp.fxml"));

            Scene bookingScene = new Scene(view);

            Stage stage = (Stage) rootpane.getScene().getWindow();
            stage.setScene(bookingScene);
            stage.centerOnScreen();
        } catch (IOException ex) {
            Logger.getLogger(FlightServiceFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean userLogging(String username, String password) {
        if (Validator.validate(username, password)) {
            return true;
        } else {
            return false;
        }
    }

    public void rotatePlane() {
        RotateTransition rg2 = new RotateTransition(Duration.seconds(4), gear2);
        rg2.setFromAngle(0);
        rg2.setToAngle(-360);
        ParallelTransition pt = new ParallelTransition(rg2);
        pt.setCycleCount(ParallelTransition.INDEFINITE);
        pt.setDelay(Duration.seconds(3));
        pt.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rotatePlane();
    }

}
