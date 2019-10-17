package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import javax.swing.*;
import java.sql.*;

public class PickControl {

    @FXML
    private javafx.scene.control.Button closeButton;

    @FXML
    ChoiceBox choiceBox;
    @FXML
    TextArea textArea;



    public static String allNameTec="0";

    public void initialize(){
        System.out.println( allNameTec+"9999999999999");
        allNameTec="0";
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Air.db";
            Connection conn = DriverManager.getConnection(dbURL);
            String query = "select * from Technician";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                choiceBox.getItems().addAll(resultSet.getString("technicianName"));
            }
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void pickAdd(ActionEvent actionEvent){
        if(allNameTec.equals("0")){
            allNameTec=choiceBox.getValue().toString();         ///////////////////////////////
            System.out.println( allNameTec+"77777");

        }
        else{
            allNameTec=allNameTec+","+choiceBox.getValue().toString();    ///////////////////////////////
            System.out.println( allNameTec+"6666");
        }
        textArea.setText(allNameTec);

    }

    public void done(){

        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
