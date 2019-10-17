package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuAdminControl {



    public void gotoLogin(ActionEvent actionEvent){
        Stage thStage = new Stage();
        Button start =(Button)actionEvent.getSource();
        Stage stage = (Stage) start.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = null;
        try {
            stage.setScene(new Scene((Parent) loader.load(), 412, 281));
            stage.setTitle("Air-Management");
            stage.setY(200);
            stage.setX(650);
            stage.show();
            stage.setResizable(false);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    public void gotoService(ActionEvent actionEvent){
        Stage thStage = new Stage();
        Button start =(Button)actionEvent.getSource();
        Stage stage = (Stage) start.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("service.fxml"));
        Parent root = null;
        try {
            stage.setScene(new Scene((Parent) loader.load(), 586, 429));
            stage.setTitle("Air-Management");
            stage.setY(200);
            stage.setX(650);
            stage.show();
            stage.setResizable(false);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }


    public void gotoAdduser(ActionEvent actionEvent){
        Stage thStage = new Stage();
        Button start =(Button)actionEvent.getSource();
        Stage stage = (Stage) start.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("adduser.fxml"));
        Parent root = null;
        try {
            stage.setScene(new Scene((Parent) loader.load(), 505, 310));
            stage.setTitle("Air-Management");
            stage.setY(200);
            stage.setX(650);
            stage.show();
            stage.setResizable(false);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }


    public void gotoAddEmployee(ActionEvent actionEvent){
        Stage thStage = new Stage();
        Button start =(Button)actionEvent.getSource();
        Stage stage = (Stage) start.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addemployee.fxml"));
        Parent root = null;
        try {
            stage.setScene(new Scene((Parent) loader.load(), 600, 400));
            stage.setTitle("Air-Management");
            stage.setY(200);
            stage.setX(650);
            stage.show();
            stage.setResizable(false);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
}
