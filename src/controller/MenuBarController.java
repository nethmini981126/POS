package controller;

import com.sun.deploy.security.CertStore;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import util.ObjectPasser;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MenuBarController {

    public Label txtUserFullName;
    public Label lblTime;
    public Label lblDate;

    public LocalTime currentTime;
    public MenuButton btnMenuItems;
    public MenuButton btnMenuOrders;
    public MenuButton btnMenuCustomers;
    public Button btnMenuDashboard;
    public AnchorPane playGroundAncorpane;

    public void initialize(){
        //automatically run this initialize method
        txtUserFullName.setText(ObjectPasser.userFullName);
        startClock();
        setCurrentDate();
    }

    private void setCurrentDate() {
        //set date
        LocalDate date = LocalDate.now();
        lblDate.setText(String.valueOf(date));
    }

    private void startClock() {
        //set current time
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            currentTime = LocalTime.now();
            lblTime.setText(currentTime.format(DateTimeFormatter.ofPattern("hh:mm:ss a")));
        }), new KeyFrame(Duration.seconds(1)));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void addItemOnAction(ActionEvent actionEvent) throws IOException {
        //set AddItemForm to playgroundancorpane that is located below the menubar
        URL resource = getClass().getResource("/view/AddItemForm.fxml");
        Parent load = FXMLLoader.load(resource);
        playGroundAncorpane.getChildren().clear();
        playGroundAncorpane.getChildren().add(load);
    }
}
