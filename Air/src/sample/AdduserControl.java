package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;

public class AdduserControl {
    @FXML
    TextField textFieldID,textFieldPass;

    boolean check=true;



    public void addUser(ActionEvent actionEvent){
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Air.db";
            Connection conn = DriverManager.getConnection(dbURL);
            System.out.println("+++");

            String query = "select * from Employee";
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                if(textFieldID.getText().equals("")||textFieldPass.getText().equals("")){
                    check=false;
                    if(textFieldID.getText().equals("")){
                        JOptionPane.showMessageDialog(null,"Pls Enter ID!!");
                        break;
                    }
                    if(textFieldPass.getText().equals("")){
                        JOptionPane.showMessageDialog(null,"Pls Enter Password!!");
                        break;
                    }

                }
                if(resultSet.getString("employeeID").equals(textFieldID.getText())){
                    JOptionPane.showMessageDialog(null,"Have an account in the system!!");
                    textFieldID.setText("");
                    textFieldPass.setText("");
                    check=false;
                }

            }
            if(check){
                String query3 ="INSERT INTO Employee VALUES(?,?)";
                PreparedStatement preparedStatement = conn.prepareStatement(query3);
                preparedStatement.setString(1,textFieldID.getText());
                preparedStatement.setString(2,textFieldPass.getText());
                preparedStatement.executeUpdate();
                textFieldID.setText("");
                textFieldPass.setText("");
                JOptionPane.showMessageDialog(null,"complete!!");
            }
            check=true;
            conn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }




    public void gotoMenu(ActionEvent actionEvent){
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
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

}
