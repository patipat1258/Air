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

import java.io.IOException;
import java.sql.*;

public class Addtechnician {
    ConnectDatabase connectDatabase = new ConnectDatabase();

    @FXML
    TextField textFieldName,textFieldPhone;

    @FXML
    TableView<Technician> tableView;
    @FXML
    TableColumn<Technician,String> tecName;
    @FXML
    TableColumn<Technician,String>tecPhone;
    @FXML
    TableColumn<Technician,Integer>tecNum;


    int i=0;
    boolean check = false;
    String name;




    public void  initialize(){
        tecNum.setCellValueFactory(new PropertyValueFactory<Technician,Integer>("tecOrder"));
        tecName.setCellValueFactory(new PropertyValueFactory<Technician,String>("tecName"));
        tecPhone.setCellValueFactory(new PropertyValueFactory<Technician,String>("tecPhone"));
        connectDatabase.conTec();
        tableView.setItems(connectDatabase.DSTechnician);
    }



    public void add(ActionEvent actionEvent){
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Air.db";
            Connection conn = DriverManager.getConnection(dbURL);
            System.out.println("+++");

            String query = "select * from Technician";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                //System.out.println(resultSet.getString("ID"));
                i=resultSet.getInt("ID");
            }
            i=i+1;
            String query3 ="INSERT INTO Technician VALUES(?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query3);
            preparedStatement.setInt(1,i);
            preparedStatement.setString(2,textFieldName.getText());
            preparedStatement.setString(3,textFieldPhone.getText());
            preparedStatement.setInt(4,0);
            preparedStatement.setInt(5,0);
            preparedStatement.executeUpdate();
            connectDatabase.conTec();
            tableView.setItems(connectDatabase.DSTechnician);
            textFieldName.setText("");
            textFieldPhone.setText("");
            conn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void del(ActionEvent actionEvent){
        int index = tableView.getSelectionModel().selectedIndexProperty().get();
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Air.db";
            Connection conn = DriverManager.getConnection(dbURL);
            String query = "select * from Technician";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                if(tableView.getItems().get(index).getTecName().equals((resultSet.getString("technicianName")))){
                    check = true;
                    name=resultSet.getString("technicianName");
                    System.out.println(name);
                    break;
                }
            }
            if(check){
                String query3 ="DELETE FROM Technician WHERE technicianName = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query3);
                preparedStatement.setString(1,name);
                preparedStatement.executeUpdate();
                Technician selectedItem = tableView.getSelectionModel().getSelectedItem();
                tableView.getItems().remove(selectedItem);
                connectDatabase.conTec();
                tableView.setItems(connectDatabase.DSTechnician);
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
