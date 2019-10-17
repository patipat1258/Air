package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;


public class ReportControl {

    ConnectDatabase connectDatabase = new ConnectDatabase();

    @FXML
    ChoiceBox choiceBox,choiceBox2;
    @FXML
    Label label;
    @FXML
    DatePicker datePicker1,datePicker2;
    @FXML
    TextField textField;


    @FXML TableView<Payment> tableView;
    @FXML TableColumn<Payment,Integer> paymentID;
    @FXML TableColumn<Payment,String> paymentName;
    @FXML TableColumn<Payment,String> paymentService;
    @FXML TableColumn<Payment,String> paymentDate;
    @FXML TableColumn<Payment,String> paymentPhone;
    @FXML TableColumn<Payment,Integer> paymentPrice;


    int i=0;
    int allMoney=0;
    static String serviceReport="";



    public void  initialize(){
        choiceBox2.getItems().addAll("01","02","03","04","05","06","07","08","09","10","11","12");
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Air.db";
            Connection conn = DriverManager.getConnection(dbURL);
            String query = "select * from Service";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                choiceBox.getItems().addAll(resultSet.getString("serviceName"));
            }
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        choiceBox.setValue(null);
        paymentID.setCellValueFactory(new PropertyValueFactory<Payment,Integer>("paymentID"));
        paymentName.setCellValueFactory(new PropertyValueFactory<Payment,String>("paymentName"));
        paymentService.setCellValueFactory(new PropertyValueFactory<Payment,String>("paymentService"));
        paymentDate.setCellValueFactory(new PropertyValueFactory<Payment,String>("paymentDate"));
        paymentPhone.setCellValueFactory(new PropertyValueFactory<Payment,String>("paymentPhone"));
        paymentPrice.setCellValueFactory(new PropertyValueFactory<Payment,Integer>("paymentPrice"));
        connectDatabase.connectPayment();
        tableView.setItems(connectDatabase.DSPayment);
        int index = tableView.getSelectionModel().selectedIndexProperty().get();

        while(i<tableView.getItems().size()){
            allMoney=allMoney+tableView.getItems().get(i).getPaymentPrice();
            i++;
        }
        label.setText(Integer.toString(allMoney));
        allMoney=0;
        i=0;





    }



    public void search(ActionEvent actionEvent){
       try {
           if(choiceBox.getValue()==null){
               if(!(textField.getText().equals(""))){
                   connectDatabase.setY(textField.getText());
                   if(choiceBox2.getValue()!=null){
                       connectDatabase.setm(choiceBox2.getValue().toString());
                   }
                   connectDatabase.connectPayment3();
                   tableView.setItems(connectDatabase.DSPayment);
               }
               if(textField.getText().equals("")){
                   JOptionPane.showMessageDialog(null,"Enter year!!");
               }

           }
           else if(choiceBox.getValue()!=null){
               if(!(textField.getText().equals(""))){
                   connectDatabase.setY(textField.getText());
                   if(choiceBox2.getValue()!=null){
                       connectDatabase.setm(choiceBox2.getValue().toString());
                   }
                   connectDatabase.setService(choiceBox.getValue().toString());
                   connectDatabase.connectPayment2();
                   tableView.setItems(connectDatabase.DSPayment);

               }
               if(textField.getText().equals("")){
                   JOptionPane.showMessageDialog(null,"Enter year!!");
               }


           }
           else{
               connectDatabase.connectPayment();
               tableView.setItems(connectDatabase.DSPayment);
           }
           while (i< tableView.getItems().size()) {
               allMoney = allMoney + tableView.getItems().get(i).getPaymentPrice();
               i++;
           }
           label.setText(Integer.toString(allMoney));
           allMoney=0;
           i=0;

           choiceBox.setValue(null);
           choiceBox2.setValue(null);
           textField.setText("");
       }catch (Exception e){
           JOptionPane.showMessageDialog(null,"select service!!");
       }

    }

    public void showAll(ActionEvent actionEvent){
//        if(textField.getText().equals("")){
//            System.out.println("...");
//        }

        connectDatabase.connectPayment();
        tableView.setItems(connectDatabase.DSPayment);
        while (i< tableView.getItems().size()) {
            allMoney = allMoney + tableView.getItems().get(i).getPaymentPrice();
            i++;
        }
        label.setText(Integer.toString(allMoney));
        allMoney=0;
        i=0;
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




//  if(datePicker1.getValue() != null){
//        System.out.println("**");
//    }
//           else{
//
//    }

}
