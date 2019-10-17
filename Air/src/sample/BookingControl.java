package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;


public class BookingControl implements Initializable {

    ConnectDatabase connectDatabase = new ConnectDatabase();
    PickControl pickControl;

    @FXML
    ChoiceBox serviceCB,timeBookingCB;
    @FXML
    DatePicker datePicker;
    @FXML
    TextField name_Cus ,phone_Cus;
    @FXML
    TextArea address_Cus;
    @FXML
    Button add,del;
    @FXML TableView<Booking> tableView;
    @FXML TableColumn<Booking,Integer> order;
    @FXML TableColumn<Booking,String> date;
    @FXML TableColumn<Booking,String> name;
    @FXML TableColumn<Booking,String> phone;
    @FXML TableColumn<Booking,String> service;
    @FXML TableColumn<Booking,Integer> price;
    @FXML TableColumn<Booking,String> status;
    @FXML TableColumn<Booking,String> timeBooking;
    @FXML TableColumn<Booking,String> tec;


    int i;
    int y ;
    String j="";
    String statusDate;
    boolean check=false;


    static String addresstoshow="+";
    static String nametoshow ="-";
    static String phonetoshow="/";
    static String technician="*";
    static String servicein="*";
    static String time="*";


    public void delBooking(ActionEvent Event){
        int index = tableView.getSelectionModel().selectedIndexProperty().get();
        //System.out.println(tableView.getItems().get(index).getOrder());
        int p=JOptionPane.showConfirmDialog(null, "Do you want to delete","Delete",JOptionPane.YES_NO_OPTION);
       // System.out.println(p);
        if(p==0){
            try {
                Class.forName("org.sqlite.JDBC");
                String dbURL = "jdbc:sqlite:Air.db";
                Connection conn = DriverManager.getConnection(dbURL);
                String query = "select * from Booking";
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("status"));
                    if((!(resultSet.getString("status").equals("Ready")))&&tableView.getItems().get(index).getName().equals((resultSet.getString("name")))){
                        check = true;
                        System.out.println(resultSet.getString("name")+"-----");
                        j = resultSet.getString("name");
                        // PreparedStatement preparedStatement = conn.prepareStatement(query3);
                        //preparedStatement.setInt(1,j);
                        // preparedStatement.executeUpdate();

                        // Booking selectedItem = tableView.getSelectionModel().getSelectedItem();
                        // tableView.getItems().remove(selectedItem);

                        // connectDatabase.con();
                        // tableView.setItems(connectDatabase.DSBooking);
                        break;
                    }

                }
                if(check){
                    String query3 ="update Booking set out = '1' where name = ?";
                    PreparedStatement preparedStatement = conn.prepareStatement(query3);
                    preparedStatement.setString(1,j);
                    preparedStatement.executeUpdate();
                    resultSet = statement.executeQuery(query);
                    while (resultSet.next()) {
                        System.out.println(resultSet.getString("status"));
                    }
                    Booking selectedItem = tableView.getSelectionModel().getSelectedItem();
                    tableView.getItems().remove(selectedItem);
                    connectDatabase.con();
                    tableView.setItems(connectDatabase.DSBooking);
                    check = false;
                    JOptionPane.showMessageDialog(null,"delete booking!!");
                }
                else{
                    JOptionPane.showMessageDialog(null,"You can't delete booking!!");
                }

                conn.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    public void addBooking(ActionEvent Event){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd ");
        LocalDateTime now = LocalDateTime.now();
        String arrayDateNow =dtf.format(now);
        arrayDateNow=arrayDateNow.replace(" ","");
        arrayDateNow=arrayDateNow.replace("-","");
        int dateNow = Integer.parseInt(arrayDateNow);

        String arrayDateSave =datePicker.getValue().toString();
        arrayDateSave=arrayDateSave.replace("-","");
        int dateSave = Integer.parseInt(arrayDateSave);
        if(dateNow==dateSave){
            statusDate="Ready";
        }
        if(dateNow>dateSave){
            statusDate="Fail";
        }
        if(dateNow<dateSave){
            statusDate="Wait";
        }
//        if(serviceCB.getValue().toString().equals("c-ari")){
//            y=300;
//        }
//        if(serviceCB.getValue().toString().equals("f-ari")){
//            y=400;
//        }
//        if(serviceCB.getValue().toString().equals("s-air")){
//            y=600;
//        }
        try{
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Air.db";
            Connection conn = DriverManager.getConnection(dbURL);
            String query = "select * from Service";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
//                System.out.println(serviceCB.getValue().toString());
//                System.out.println(resultSet.getString("serviceName"));
//                System.out.println(resultSet.getString("servicePrice"));
               if(serviceCB.getValue().toString().equals(resultSet.getString("serviceName"))){
                   y=resultSet.getInt("servicePrice");
                   break;
               }

            }
            conn.close();
        }catch (ClassNotFoundException e) {
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
                //System.out.println(resultSet.getString("ID"));
                i=resultSet.getInt("order");
            }
            i=i+1;
            String query3 ="INSERT INTO Booking VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query3);
            preparedStatement.setInt(1,i);
            preparedStatement.setString(2,name_Cus.getText());
            preparedStatement.setString(3,phone_Cus.getText());
            preparedStatement.setString(4,datePicker.getValue().toString());
            preparedStatement.setString(5,serviceCB.getValue().toString());
            preparedStatement.setString(6,timeBookingCB.getValue().toString());
            preparedStatement.setString(7,address_Cus.getText());
            preparedStatement.setInt(8,y);
            preparedStatement.setString(9,statusDate);
            preparedStatement.setString(10,"0");
            preparedStatement.setString(11,"0");
            preparedStatement.executeUpdate();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
            }
            conn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectDatabase.con();
        tableView.setItems(connectDatabase.DSBooking);
        name_Cus.setText("");
        phone_Cus.setText("");
        datePicker.setValue(null);
        serviceCB.setValue(null);
        timeBookingCB.setValue(null);
        address_Cus.setText("");
        i=i+1;

    }
    public void initialize(URL location, ResourceBundle resources){
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Air.db";
            Connection conn = DriverManager.getConnection(dbURL);
            String query = "select * from Service";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                serviceCB.getItems().addAll(resultSet.getString("serviceName"));
            }
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        serviceCB.setValue(null);

        timeBookingCB.getItems().addAll("8.00-9.00","9.00-10.00","11.00-12.00",
                "13.00-14.00","14.00-15.00","15.00-16.00","16.00-17.00","17.00-18.00");


        timeBookingCB.setValue(null);




        order.setCellValueFactory(new PropertyValueFactory<Booking,Integer>("order"));
        date.setCellValueFactory(new PropertyValueFactory<Booking,String>("date"));
        name.setCellValueFactory(new PropertyValueFactory<Booking,String>("name"));
        phone.setCellValueFactory(new PropertyValueFactory<Booking,String>("phone"));
        service.setCellValueFactory(new PropertyValueFactory<Booking,String>("service"));
        price.setCellValueFactory(new PropertyValueFactory<Booking,Integer>("price"));
        status.setCellValueFactory(new PropertyValueFactory<Booking,String>("status"));
        timeBooking.setCellValueFactory(new PropertyValueFactory<Booking,String>("timeBooking"));
        tec.setCellValueFactory(new PropertyValueFactory<Booking,String>("tec"));
        connectDatabase.con();
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


    public void addTOPayment(ActionEvent actionEvent){
        int index = tableView.getSelectionModel().selectedIndexProperty().get();
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Air.db";
            Connection conn = DriverManager.getConnection(dbURL);
            String query = "select * from Booking";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                if(resultSet.getString("status").equals("Ready")&&
                        tableView.getItems().get(index).getName().equals((resultSet.getString("name")))){
                    check = true;
                    j = resultSet.getString("name");
                    break;
                }
            }
            if(check){
                String query3 ="update Booking set out = '2' where name = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query3);
                preparedStatement.setString(1,j);
                preparedStatement.executeUpdate();
                resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("status"));
                }
                Booking selectedItem = tableView.getSelectionModel().getSelectedItem();
                tableView.getItems().remove(selectedItem);
                connectDatabase.con();
                tableView.setItems(connectDatabase.DSBooking);
                check = false;
            }
            JOptionPane.showMessageDialog(null,"completed!!");
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void showAddress(ActionEvent actionEvent){

        inAddress();

        Stage thStage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("address.fxml"));
            thStage.setTitle("Air-Management");
            thStage.setScene(new Scene(loader.load(), 393, 242));
            thStage.setX(700);
            thStage.setY(30);
            thStage.setResizable(false);
            thStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }
    public void inAddress(){
        int index = tableView.getSelectionModel().selectedIndexProperty().get();
        System.out.println(tableView.getItems().get(index).getName());
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Air.db";
            Connection conn = DriverManager.getConnection(dbURL);
            String query = "select * from Booking";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
//                System.out.println(tableView.getItems().get(index).getName()+"++++");
//                System.out.println((resultSet.getString("name"))+"----");
                if(tableView.getItems().get(index).getName().equals((resultSet.getString("name")))){
                    System.out.println("*******************");
                    nametoshow=resultSet.getString("name");
                    addresstoshow=resultSet.getString("address");
                    phonetoshow=resultSet.getString("phone");
                    servicein=resultSet.getString("service");
                    time=resultSet.getString("timeBooking");
                    technician=resultSet.getString("tec");
                    break;
                }
            }
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inAddress2(ActionEvent actionEvent){
        int index = tableView.getSelectionModel().selectedIndexProperty().get();
        System.out.println(pickControl.allNameTec+"7777777");
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Air.db";
            Connection conn = DriverManager.getConnection(dbURL);

            String query3 ="update Booking set tec = ? where name = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query3);
            preparedStatement.setString(1,pickControl.allNameTec);
            preparedStatement.setString(2,tableView.getItems().get(index).getName());
            preparedStatement.executeUpdate();
            conn.close();
            pickControl.allNameTec="0";
            JOptionPane.showMessageDialog(null,"complete!!");
            connectDatabase.con();
            tableView.setItems(connectDatabase.DSBooking);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void pick(ActionEvent actionEvent){
        Stage thStage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("picktec.fxml"));
            thStage.setTitle("Air-Management");
            thStage.setScene(new Scene(loader.load(), 401, 248));
            thStage.setX(700);
            thStage.setY(30);
            thStage.setResizable(false);
            thStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String setAddress(){
        System.out.println(nametoshow);
        return "name : "+nametoshow+"\n"+"phone : "+phonetoshow+"\n"+"time : "+time+"\n"
                +"service : "+servicein+"\n"+"technician : "+technician+"\n"+"address : "+addresstoshow;
    }




}
