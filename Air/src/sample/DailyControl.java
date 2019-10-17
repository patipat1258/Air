package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class DailyControl {
    ConnectDatabase connectDatabase = new ConnectDatabase();
    @FXML
    ChoiceBox choiceBox;
    @FXML
    TableView<Booking> tableView;
    @FXML
    TableColumn<Booking,Integer> orderD;
    @FXML TableColumn<Booking,String> dateD;
    @FXML TableColumn<Booking,String> nameD;
    @FXML TableColumn<Booking,String> phoneD;
    @FXML TableColumn<Booking,String> serviceD;
    @FXML TableColumn<Booking,Integer> priceD;
    @FXML TableColumn<Booking,String> timeBooking;
    @FXML TableColumn<Booking,String> adderssD;
    @FXML TableColumn<Booking,String> tecD;

    String tecI="";





    public void initialize(){
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
        }  catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        choiceBox.setValue(null);


        orderD.setCellValueFactory(new PropertyValueFactory<Booking,Integer>("order"));
        dateD.setCellValueFactory(new PropertyValueFactory<Booking,String>("date"));
        nameD.setCellValueFactory(new PropertyValueFactory<Booking,String>("name"));
        phoneD.setCellValueFactory(new PropertyValueFactory<Booking,String>("phone"));
        serviceD.setCellValueFactory(new PropertyValueFactory<Booking,String>("service"));
        priceD.setCellValueFactory(new PropertyValueFactory<Booking,Integer>("price"));
        timeBooking.setCellValueFactory(new PropertyValueFactory<Booking,String>("timeBooking"));
        adderssD.setCellValueFactory(new PropertyValueFactory<Booking,String>("address"));
        tecD.setCellValueFactory(new PropertyValueFactory<Booking,String>("tec"));
        connectDatabase.con3();
        tableView.setItems(connectDatabase.DSBooking);
    }


    public void find(ActionEvent actionEvent){
        try {
            tecI = choiceBox.getValue().toString();
            connectDatabase.setTec(tecI);
            connectDatabase.con4();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"select technician!!");
        }


    }


    public void All(ActionEvent actionEvent){
        connectDatabase.con3();
        tableView.setItems(connectDatabase.DSBooking);

    }

    public void gotoMenu(ActionEvent actionEvent){

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


    }



}
