/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightservice;

import javafx.scene.control.ToggleGroup;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.Timer;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

/**
 * FXML Controller class
 *
 * @author Spark
 */
public class BookingAppController implements Initializable {

    private static final String FIRST_NAME = "(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}";
    private static final String LAST_NAME = "(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}";
    private static final String PHONE = "(?=.*[0-9])(?=\\S+$).{11,}";
    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @FXML
    private JFXTreeTableView<User> details;

    @FXML
    private Pane familyPane;

    @FXML
    private ToggleGroup flightClass;

    @FXML
    private ToggleGroup flightType;

    @FXML
    private ToggleGroup genderSelection;

    @FXML
    private TextArea address;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField email;

    @FXML
    private TextField phoneNumber;

    @FXML
    private AnchorPane mainPane;

    @FXML
    JFXComboBox fromDestination = new JFXComboBox();

    @FXML
    JFXComboBox toDestination = new JFXComboBox();

    @FXML
    JFXComboBox state = new JFXComboBox();

    @FXML
    private JFXCheckBox family;

    @FXML
    private StackPane pane;

    @FXML
    private Label userName;

    @FXML
    private Label from;

    @FXML
    private Pane home;

    @FXML
    private Pane homePane;

    @FXML
    private Pane ticket;

    @FXML
    private Pane ticketPane;

    @FXML
    private Pane flight;

    @FXML
    private Pane flightPane;

    @FXML
    private Pane costumer;

    @FXML
    private Pane costumerPane;

    @FXML
    private ImageView slider;

    @FXML
    private JFXRadioButton economic;

    @FXML
    private JFXRadioButton business;

    @FXML
    private JFXRadioButton local;

    @FXML
    private JFXRadioButton international;

    @FXML
    private Pane testB;

    Timer tm;
    int x = 0;

    @FXML
    private void homeButton(MouseEvent event) {
        homePane.setVisible(true);
        ticketPane.setVisible(false);
        flightPane.setVisible(false);
        costumerPane.setVisible(false);
        home.setVisible(true);
        ticket.setVisible(false);
        flight.setVisible(false);
        costumer.setVisible(false);
    }

    @FXML
    private void ticketButton(MouseEvent event) {
        ticketPane.setVisible(true);
        homePane.setVisible(false);
        flightPane.setVisible(false);
        costumerPane.setVisible(false);
        ticket.setVisible(true);
        home.setVisible(false);
        flight.setVisible(false);
        costumer.setVisible(false);
    }

    @FXML
    private void flightButton(MouseEvent event) {
        flightPane.setVisible(true);
        ticketPane.setVisible(false);
        homePane.setVisible(false);
        costumerPane.setVisible(false);
        flight.setVisible(true);
        ticket.setVisible(false);
        home.setVisible(false);
        costumer.setVisible(false);
    }

    @FXML
    private void familyOptional(ActionEvent event) {
        if (family.isSelected()) {
            familyPane.setDisable(false);
        } else if (!family.isSelected()) {
            familyPane.setDisable(true);
        }
    }

    @FXML
    private void costumerButton(MouseEvent event) {
        costumerPane.setVisible(true);
        ticketPane.setVisible(false);
        flightPane.setVisible(false);
        homePane.setVisible(false);
        costumer.setVisible(true);
        ticket.setVisible(false);
        flight.setVisible(false);
        home.setVisible(false);
    }

    @FXML
    private void logOut(ActionEvent event) {
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text("CONFIRM"));
        content.setBody(new Text("Do you want to Log Out?"));
        JFXDialog dialog = new JFXDialog(pane, content, JFXDialog.DialogTransition.CENTER);
        JFXButton button1 = new JFXButton("Yes");
        button1.setBackground(testB.getBackground());
        JFXButton button2 = new JFXButton("No");
        button2.setBackground(testB.getBackground());
        button1.setButtonType(JFXButton.ButtonType.RAISED);
        button2.setButtonType(JFXButton.ButtonType.RAISED);
        button1.setOnAction((eventA) -> {
            Parent view;
            try {
                view = (AnchorPane) FXMLLoader.load(getClass().getResource("FlightServiceFXML.fxml"));
                Scene flightScene = new Scene(view);

                Stage stage = (Stage) mainPane.getScene().getWindow();
                stage.setScene(flightScene);
                stage.centerOnScreen();
            } catch (IOException ex) {
                Logger.getLogger(BookingAppController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        button2.setOnAction((eventA) -> {
            dialog.close();
        });
        content.setActions(button1, button2);
        dialog.show();
    }

    @FXML
    private void exit(ActionEvent event) {
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text("CONFIRM"));
        content.setBody(new Text("Do you want to Exit?"));
        JFXDialog dialog = new JFXDialog(pane, content, JFXDialog.DialogTransition.CENTER);
        JFXButton button1 = new JFXButton("Yes");
        button1.setBackground(testB.getBackground());
        JFXButton button2 = new JFXButton("No");
        button2.setBackground(testB.getBackground());
        button1.setButtonType(JFXButton.ButtonType.RAISED);
        button2.setButtonType(JFXButton.ButtonType.RAISED);
        button1.setOnAction((eventA) -> {
            System.exit(0);
        });
        button2.setOnAction((eventA) -> {
            dialog.close();
        });
        content.setActions(button1, button2);
        dialog.show();
    }

    public void validators() {
        ValidationSupport validation = new ValidationSupport();
        validation.registerValidator(firstName, Validator.createEmptyValidator("Input Required", Severity.WARNING));
        ValidationSupport validation2 = new ValidationSupport();
        validation2.registerValidator(lastName, Validator.createEmptyValidator("Input Required", Severity.WARNING));
        ValidationSupport validation3 = new ValidationSupport();
        validation3.registerValidator(phoneNumber, Validator.createEmptyValidator("Input Required", Severity.WARNING));
        ValidationSupport validation4 = new ValidationSupport();
        validation4.registerValidator(email, Validator.createRegexValidator("Provide Correct Email", EMAIL_PATTERN, Severity.ERROR));
        ValidationSupport validation5 = new ValidationSupport();
        validation5.registerValidator(firstName, Validator.createRegexValidator("Provide Correct First Name", FIRST_NAME, Severity.ERROR));
        ValidationSupport validation6 = new ValidationSupport();
        validation6.registerValidator(lastName, Validator.createRegexValidator("Provide Correct Last Name", LAST_NAME, Severity.ERROR));
        ValidationSupport validation7 = new ValidationSupport();
        validation7.registerValidator(phoneNumber, Validator.createRegexValidator("Provide Correct Phone Number", PHONE, Severity.ERROR));
        ValidationSupport validation8 = new ValidationSupport();
        validation8.registerValidator(address, Validator.createEmptyValidator("Input Required", Severity.WARNING));
    }

    private boolean validationcheck() {
        if ("".equals(firstName.getText())) {
            return false;
        }
        if ("".equals(lastName.getText())) {
            return false;
        }
        if (!Pattern.matches(EMAIL_PATTERN, email.getText())) {
            return false;
        }
        return true;
    }

    public void populateComboBoxes() {
        ObservableList countries = FXCollections.observableArrayList(
                "Israel", "Germany", "Canada", "USA", "England", "Nigeria", "Brazil");
        fromDestination.setItems(countries);
        fromDestination.getSelectionModel().select(5);
        toDestination.setItems(countries);
    }

    @FXML
    public void local(ActionEvent event) {
        toDestination.setDisable(true);
        from.setText("Home");
    }

    @FXML
    public void international(ActionEvent event) {
        from.setText("From");
        toDestination.setDisable(false);
    }

    public void transition() {
//        FadeTransition fd1 = new FadeTransition(Duration.seconds(2), slider);
//        fd1.setDelay(Duration.seconds(2));
//        fd1.setFromValue(Double.MAX_VALUE);
//        fd1.setToValue(0);
//        fd1.play();
//        slider.setImage(value);
    }

    public void populateTreeTable() {
        JFXTreeTableColumn<User, String> nameCol = new JFXTreeTableColumn<>("Full Name");
        nameCol.setPrefWidth(180);
        nameCol.setResizable(false);
        nameCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().name;
            }
        });

        JFXTreeTableColumn<User, String> gender = new JFXTreeTableColumn<>("Gender");
        gender.setPrefWidth(100);
        gender.setResizable(false);
        gender.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().gender;
            }
        });

        JFXTreeTableColumn<User, String> flightClass = new JFXTreeTableColumn<>("Flight Class");
        flightClass.setPrefWidth(150);
        flightClass.setResizable(false);
        flightClass.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().flightClass;
            }
        });

        JFXTreeTableColumn<User, String> flightType = new JFXTreeTableColumn<>("Flight Type");
        flightType.setPrefWidth(150);
        flightType.setResizable(false);
        flightType.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().flightType;
            }
        });

        JFXTreeTableColumn<User, String> from = new JFXTreeTableColumn<>("Home");
        from.setPrefWidth(150);
        from.setResizable(false);
        from.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().from;
            }
        });

        JFXTreeTableColumn<User, String> to = new JFXTreeTableColumn<>("Destination");
        to.setPrefWidth(150);
        to.setResizable(false);
        to.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().to;
            }
        });

        String fullname = firstName.getText().concat(" " + lastName.getText());
        String gender1 = "";//genderSelection.getSelectedToggle().toString();
        String flight_Class = ""; //this.flightClass.getSelectedToggle().toString();
        String flight_Type = "";//this.flightType.getSelectedToggle().toString();
        String from_Destination = "";//fromDestination.getSelectionModel().toString();
        String to_Destination = "";//toDestination.getSelectionModel().toString();
        ObservableList<User> data = FXCollections.observableArrayList();
        data.add(new User(fullname, gender1, flight_Class, flight_Type, from_Destination, to_Destination));

        final TreeItem<User> root = new RecursiveTreeItem<User>(data, RecursiveTreeObject::getChildren);
        details.getColumns().setAll(nameCol, gender, flightClass, flightType, from, to);
        details.setRoot(root);
        details.setShowRoot(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        validators();
        transition();
        populateComboBoxes();
        populateTreeTable();
    }

    class User extends RecursiveTreeObject<User> {

        StringProperty name;
        StringProperty dateOfBirth;
        StringProperty gender;
        StringProperty flightClass;
        StringProperty flightType;
        StringProperty from;
        StringProperty to;
        StringProperty state;

        public User(String name, String gender, String flightClass, String flightType, String from, String to) {
            this.name = new SimpleStringProperty(name);
            this.gender = new SimpleStringProperty(gender);
            this.flightClass = new SimpleStringProperty(flightClass);
            this.flightType = new SimpleStringProperty(flightType);
            this.from = new SimpleStringProperty(from);
            this.to = new SimpleStringProperty(to);
        }
    }

}
