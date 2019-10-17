package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;


public class PaymentControl {
    ConnectDatabase connectDatabase = new ConnectDatabase();
    @FXML
    TextField textField;

    @FXML
    TableView<Booking> tableView;
    @FXML
    TableColumn<Booking,Integer> order;
    @FXML TableColumn<Booking,String> date;
    @FXML TableColumn<Booking,String> name;
    @FXML TableColumn<Booking,String> phone;
    @FXML TableColumn<Booking,String> service;
    @FXML TableColumn<Booking,Integer> price;

    String nameP="";
    String dateP="";
    String serviceP="";
    String phoneP="";
    int priceP;
    int i =0;
    boolean check= false;



    public void initialize(){

        order.setCellValueFactory(new PropertyValueFactory<Booking,Integer>("order"));
        date.setCellValueFactory(new PropertyValueFactory<Booking,String>("date"));
        name.setCellValueFactory(new PropertyValueFactory<Booking,String>("name"));
        phone.setCellValueFactory(new PropertyValueFactory<Booking,String>("phone"));
        service.setCellValueFactory(new PropertyValueFactory<Booking,String>("service"));
        price.setCellValueFactory(new PropertyValueFactory<Booking,Integer>("price"));


        connectDatabase.con2();
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

    public void addPayment(ActionEvent actionEvent){
        int p=JOptionPane.showConfirmDialog(null, "Payment Confirm","Payment",JOptionPane.YES_NO_OPTION);
        System.out.println(p);
        if(p==0){
            int index = tableView.getSelectionModel().selectedIndexProperty().get();
            nameP=tableView.getItems().get(index).getName().toString();
            dateP=tableView.getItems().get(index).getDate();
            serviceP=tableView.getItems().get(index).getService();
            phoneP=tableView.getItems().get(index).getPhone();
            priceP=tableView.getItems().get(index).getPrice();
            int v =0;
            if(!(textField.getText().isEmpty())){
                v= Integer.parseInt(textField.getText());
            }
            priceP=priceP+v;
            textField.setText("");
            try {
                Class.forName("org.sqlite.JDBC");
                String dbURL = "jdbc:sqlite:Air.db";
                Connection conn = DriverManager.getConnection(dbURL);
                String query = "select * from Payment";
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    // System.out.println(resultSet.getString("paymentID"));
                    i=resultSet.getInt("paymentID");
                }
                i=i+1;
                String query3 ="INSERT INTO Payment VALUES(?,?,?,?,?,?)";
                PreparedStatement preparedStatement = conn.prepareStatement(query3);
                preparedStatement.setInt(1,i);
                preparedStatement.setString(2,nameP);
                preparedStatement.setString(3,serviceP);
                preparedStatement.setInt(4,priceP);
                preparedStatement.setString(5,dateP);
                preparedStatement.setString(6,phoneP);
                preparedStatement.executeUpdate();
                conn.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                Class.forName("org.sqlite.JDBC");
                String dbURL = "jdbc:sqlite:Air.db";
                Connection conn = DriverManager.getConnection(dbURL);
                String query = "select * from Booking";
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    if(tableView.getItems().get(index).getName().equals((resultSet.getString("name")))){
                        check = true;
                        break;
                    }
                }
                if(check){
                    check=false;
                    String query3 ="update Booking set out = '3' where name = ?";
                    PreparedStatement preparedStatement = conn.prepareStatement(query3);
                    preparedStatement.setString(1,nameP);
                    preparedStatement.executeUpdate();
                    Booking selectedItem = tableView.getSelectionModel().getSelectedItem();
                    tableView.getItems().remove(selectedItem);
                    connectDatabase.con2();
                    tableView.setItems(connectDatabase.DSBooking);


                }
                JOptionPane.showMessageDialog(null,"Successful payment!!");
                conn.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }



    }

}
