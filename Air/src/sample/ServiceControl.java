package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

import java.io.IOException;
import java.sql.*;


public class ServiceControl {
    ConnectDatabase connectDatabase = new ConnectDatabase();
    @FXML
    Button aa;
    @FXML
    TextField nameText,priceText;
    int i;

    @FXML
    TableView<Service> tableView;
    @FXML
    TableColumn<Service,String> serviceName;
    @FXML
    TableColumn<Service,Integer>servicePrice;
    @FXML
    TableColumn<Service,Integer>serviceNum;

    boolean check = false;
    String name;

    public void  initialize(){
        serviceNum.setCellValueFactory(new PropertyValueFactory<Service,Integer>("serviceOrder"));
        serviceName.setCellValueFactory(new PropertyValueFactory<Service,String>("serviceName"));
        servicePrice.setCellValueFactory(new PropertyValueFactory<Service,Integer>("servicePrice"));
        connectDatabase.connectService();
        tableView.setItems(connectDatabase.DSService);
    }
    public void add(ActionEvent actionEvent)
    {
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Air.db";
            Connection conn = DriverManager.getConnection(dbURL);
            String query = "select * from Service";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                i=resultSet.getInt("ID");
            }
            i=i+1;
            String query3 ="INSERT INTO Service VALUES(?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query3);
            preparedStatement.setInt(1,i);
            preparedStatement.setString(2,nameText.getText());
            preparedStatement.setString(3,priceText.getText());
            preparedStatement.executeUpdate();
            connectDatabase.connectService();
            tableView.setItems(connectDatabase.DSService);
            nameText.setText("");
            priceText.setText("");
            conn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public void del(ActionEvent actionEvent)
    {
        int index = tableView.getSelectionModel().selectedIndexProperty().get();
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Air.db";
            Connection conn = DriverManager.getConnection(dbURL);
            String query = "select * from Service";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                if(tableView.getItems().get(index).getServiceName().equals((resultSet.getString("serviceName")))){
                    check = true;
                    name=resultSet.getString("serviceName");
                    System.out.println(name);
                    break;
                }
            }
            if(check){
                String query3 ="DELETE FROM Service WHERE serviceName = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query3);
                preparedStatement.setString(1,name);
                preparedStatement.executeUpdate();
                Service selectedItem = tableView.getSelectionModel().getSelectedItem();
                tableView.getItems().remove(selectedItem);
                connectDatabase.connectService();
                tableView.setItems(connectDatabase.DSService);
                check=false;

            }
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

