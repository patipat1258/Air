package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;

public class LoginControl {

    @FXML
    TextField textID;
    @FXML
    PasswordField textPW;

    boolean check=true;



    public void gotoMenu(ActionEvent actionEvent){
        if(textID.getText().equals("a")&&textPW.getText().equals("a")){
            Stage thStage = new Stage();
            Button start =(Button)actionEvent.getSource();
            Stage stage = (Stage) start.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("menuadmin.fxml"));
            Parent root = null;
            try {
                stage.setScene(new Scene((Parent) loader.load(), 267, 383));
                stage.setTitle("Air-Management");
                stage.setY(200);
                stage.setX(650);
                stage.show();
                stage.setResizable(false);
                check=false;
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Air.db";
            Connection conn = DriverManager.getConnection(dbURL);
            String query = "select * from Employee";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                if(textID.getText().equals(resultSet.getString("employeeID"))&&textPW.getText().equals(resultSet.getString("employeePass"))){
                    Stage thStage = new Stage();
                    Button start =(Button)actionEvent.getSource();
                    Stage stage = (Stage) start.getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("menuemployee.fxml"));
                    Parent root = null;
                    try {
                        stage.setScene(new Scene((Parent) loader.load(), 267, 462));
                        stage.setTitle("Air-Management");
                        stage.setY(200);
                        stage.setX(650);
                        stage.show();
                        stage.setResizable(false);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    check=false;
                    break;
                }
        }
        if(check){
            JOptionPane.showMessageDialog(null,"Wrong ID or Password!!");
        }
            check=true;

            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
    public void close(ActionEvent actionEvent){
        System.exit(0);
    }


}
