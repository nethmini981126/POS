package controller;

import javafx.scene.control.Label;
import util.ObjectPasser;

public class MenuBarController {

    public Label txtUserFullName;

    //automatically run this initialize method
    public void initialize(){
        txtUserFullName.setText(ObjectPasser.userFullName);


    }
}
