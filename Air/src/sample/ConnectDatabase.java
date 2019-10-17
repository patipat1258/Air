package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ConnectDatabase {

    public int serviceOrder;
    public String serviceName;
    public int servicePrice;
    public String serviceReport;
    public String yy="null";
    public String mm="null";
    public String checkDate;

    public String tecChoi="null";

    public int tecOrder;
    public String tecName;
    public String tecPhone;
    public String tecStatus;
    public String tecGroup;




    public int order;
    public String name;
    public String phone;
    public String date;
    public String service;
    public String timeBooking;
    public String address;
    public int price;
    public String status;
    public String tec;
    ReportControl reportControl ;


    public ObservableList<Booking> DSBooking = FXCollections.observableArrayList();
    public ObservableList<Service> DSService = FXCollections.observableArrayList();
    public ObservableList<Payment> DSPayment = FXCollections.observableArrayList();
    public ObservableList<Technician> DSTechnician = FXCollections.observableArrayList();

    public void connectService(){
        DSService.clear();
        serviceOrder=0;
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Air.db";
            Connection conn = DriverManager.getConnection(dbURL);
            System.out.println("ok_DB");
            String query = "select * from Service";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                serviceName=resultSet.getString("serviceName");
                servicePrice=resultSet.getInt("servicePrice");
                serviceOrder=serviceOrder+1;
                DSService.add(new Service(serviceName,servicePrice,serviceOrder));
            }
            conn.close();

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public  void con(){
        DSBooking.clear();
        order=0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd ");
        LocalDateTime now = LocalDateTime.now();
        String arrayDateNow =dtf.format(now);
        arrayDateNow=arrayDateNow.replace(" ","");
        arrayDateNow=arrayDateNow.replace("-","");
        int dateNow = Integer.parseInt(arrayDateNow);
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Air.db";
            Connection conn = DriverManager.getConnection(dbURL);
            System.out.println("ok_DB");

            String query = "select * from Booking";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                if(resultSet.getInt("out")==0){
                    //order=resultSet.getInt("order");
                    name=resultSet.getString("name");
                    phone=resultSet.getString("phone");
                    date=resultSet.getString("date");
                    service=resultSet.getString("service");
                    timeBooking=resultSet.getString("timeBooking");
                    price=resultSet.getInt("price");
                    status=resultSet.getString("status");
                    tec=resultSet.getString("tec");
                    if(tec.equals("0")){
                        tec="-";
                    }
                    String arrayDateSave =date.replace("-","");
                    int dateSave = Integer.parseInt(arrayDateSave);
                    if(dateNow==dateSave){
                        status="Ready";
                        String query3 ="update Booking set status = 'Ready' where name = ?";
                        PreparedStatement preparedStatement = conn.prepareStatement(query3);
                        preparedStatement.setString(1,name);
                        preparedStatement.executeUpdate();
                    }
                    if(dateNow>dateSave){
                        status="Fail";
                        String query3 ="update Booking set status = 'Fail' where name = ?";
                        PreparedStatement preparedStatement = conn.prepareStatement(query3);
                        preparedStatement.setString(1,name);
                        preparedStatement.executeUpdate();
                    }
                    if(dateNow<dateSave){
                        status="Wait";
                        String query3 ="update Booking set status = 'Wait' where name = ?";
                        PreparedStatement preparedStatement = conn.prepareStatement(query3);
                        preparedStatement.setString(1,name);
                        preparedStatement.executeUpdate();
                    }

                    order=order+1;
                    DSBooking.add(new Booking(order,name,phone,date,service,timeBooking,address,price,status,tec));
                }
            }
            conn.close();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void con2(){
        DSBooking.clear();
        order=0;
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Air.db";
            Connection conn = DriverManager.getConnection(dbURL);
            System.out.println("ok_DB");

            String query = "select * from Booking";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                if(resultSet.getInt("out")==2){
                    name=resultSet.getString("name");
                    phone=resultSet.getString("phone");
                    date=resultSet.getString("date");
                    service=resultSet.getString("service");
                    timeBooking=resultSet.getString("timeBooking");
                    price=resultSet.getInt("price");
                    status=resultSet.getString("status");
                    tec=resultSet.getString("tec");
                    if(tec.equals("0")){
                        tec="-";
                    }
                    order=order+1;
                    DSBooking.add(new Booking(order,name,phone,date,service,timeBooking,address,price,status,tec));

                }


            }
            conn.close();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        }

    public void con3(){
        DSBooking.clear();
        order=0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd ");
        LocalDateTime now = LocalDateTime.now();
        String arrayDateNow =dtf.format(now);
        arrayDateNow=arrayDateNow.replace(" ","");
        arrayDateNow=arrayDateNow.replace("-","");
        int dateNow = Integer.parseInt(arrayDateNow);

        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Air.db";
            Connection conn = DriverManager.getConnection(dbURL);
            System.out.println("ok_DB");

            String query = "select * from Booking";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                date=resultSet.getString("date");
                String arrayDateSave =date.replace("-","");
                int dateSave = Integer.parseInt(arrayDateSave);
                if(  (resultSet.getString("status").equals("Ready")&& (dateNow==dateSave))&&resultSet.getInt("out")==0 ){
                    System.out.println(resultSet.getString("tec")+"894");
                    name=resultSet.getString("name");
                    phone=resultSet.getString("phone");
                    date=resultSet.getString("date");
                    service=resultSet.getString("service");
                    timeBooking=resultSet.getString("timeBooking");
                    price=resultSet.getInt("price");
                    status=resultSet.getString("status");
                    tec=resultSet.getString("tec");
                    address=resultSet.getString("address");
                    if(tec.equals("0")){
                        tec="-";
                    }
                    order=order+1;
                    DSBooking.add(new Booking(order,name,phone,date,service,timeBooking,address,price,status,tec));

                }


            }
            conn.close();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void con4(){
        DSBooking.clear();
        order=0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd ");
        LocalDateTime now = LocalDateTime.now();
        String arrayDateNow =dtf.format(now);
        arrayDateNow=arrayDateNow.replace(" ","");
        arrayDateNow=arrayDateNow.replace("-","");
        int dateNow = Integer.parseInt(arrayDateNow);
        String checkTec1="";
        String checkTec2="";
        String checkTec3="";
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Air.db";
            Connection conn = DriverManager.getConnection(dbURL);
            System.out.println("ok_DB");

            String query = "select * from Booking";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {



                date=resultSet.getString("date");
                String arrayDateSave =date.replace("-","");
                int dateSave = Integer.parseInt(arrayDateSave);
                if(  (resultSet.getString("status").equals("Ready")&& (dateNow==dateSave))&&resultSet.getInt("out")==0 ){
                    if(resultSet.getString("tec").contains(",")){
                        String[] nameT = resultSet.getString("tec").split(",");
                        checkTec1=nameT[0];
                        checkTec2=nameT[1];
//                        for(int i = 0; i < nameT.length; i++) {
//                            System.out.println(nameT[i]+"-------------"+resultSet.getString("order"));
//
//                        }

                    }
                    if(!resultSet.getString("tec").contains(",")){
                        checkTec3=resultSet.getString("tec");
                       // System.out.println(resultSet.getString("tec")+"-------------"+resultSet.getString("order"));
                    }
                    if(  (tecChoi.equals(checkTec1)|| tecChoi.equals(checkTec2))||tecChoi.equals(checkTec3)          ){
                        name=resultSet.getString("name");
                        phone=resultSet.getString("phone");
                        date=resultSet.getString("date");
                        service=resultSet.getString("service");
                        timeBooking=resultSet.getString("timeBooking");
                        price=resultSet.getInt("price");
                        status=resultSet.getString("status");
                        tec=resultSet.getString("tec");
                        address=resultSet.getString("address");
                        if(tec.equals("0")){
                            tec="-";
                        }
                        order=order+1;
                        DSBooking.add(new Booking(order,name,phone,date,service,timeBooking,address,price,status,tec));
                        checkTec1="";
                        checkTec2="";
                        checkTec3="";
                    }



                }


            }
            conn.close();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void conTec(){
        DSTechnician.clear();
        tecOrder=0;
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Air.db";
            Connection conn = DriverManager.getConnection(dbURL);
            System.out.println("ok_DB");

            String query = "select * from Technician";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                if(resultSet.getInt("technicianStatus")==0){
                    tecName=resultSet.getString("technicianName");
                    tecPhone=resultSet.getString("technicianPhone");
                    status=resultSet.getString("technicianStatus");
                    tecGroup=resultSet.getString("technicianGroup");
                    tecOrder=tecOrder+1;
                    DSTechnician.add(new Technician(tecOrder,tecName,tecPhone,tecStatus,tecGroup));
                }


            }
            conn.close();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void connectPayment(){
        DSPayment.clear();
        order=0;
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Air.db";
            Connection conn = DriverManager.getConnection(dbURL);
            System.out.println("ok_DB");

            String query = "select * from Payment";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                    name=resultSet.getString("paymentName");
                    phone=resultSet.getString("paymentPhone");
                    date=resultSet.getString("paymentDate");
                    service=resultSet.getString("paymentService");
                    price=resultSet.getInt("paymentPrice");
                    order=order+1;
                    DSPayment.add(new Payment(order,name,service,price,date,phone));

            }
            conn.close();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }



    }

    public void connectPayment2(){
        DSPayment.clear();
        order=0;
        if(!(mm.equals("null"))&&!(yy.equals("null"))){
            checkDate=yy+"-"+mm;
        }
        if(!(mm.equals("null"))&&(yy.equals("null"))){
            checkDate=mm;
        }
        if((mm.equals("null"))&&!((yy.equals("null")))){
            checkDate=yy;
        }



        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Air.db";
            Connection conn = DriverManager.getConnection(dbURL);
            System.out.println("ok_DB");

            String query = "select * from Payment";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println(checkDate+"   .....");
                System.out.println(resultSet.getString("paymentDate").substring(5,7)+"   ....");
                if(resultSet.getString("paymentService").equals(serviceReport)&&( (mm.equals("null"))&&(yy.equals("null"))  )){
                    name=resultSet.getString("paymentName");
                    phone=resultSet.getString("paymentPhone");
                    date=resultSet.getString("paymentDate");
                    service=resultSet.getString("paymentService");
                    price=resultSet.getInt("paymentPrice");
                    order=order+1;
                    DSPayment.add(new Payment(order,name,service,price,date,phone));
                    System.out.println("++++");
                    continue;
                }
                if(resultSet.getString("paymentService").equals(serviceReport)&&(!((mm.equals("null")))&&(!(yy.equals("null")))  )){
                    if(checkDate.equals(resultSet.getString("paymentDate").substring(0,7))){
                        name=resultSet.getString("paymentName");
                        phone=resultSet.getString("paymentPhone");
                        date=resultSet.getString("paymentDate");
                        service=resultSet.getString("paymentService");
                        price=resultSet.getInt("paymentPrice");
                        order=order+1;
                        DSPayment.add(new Payment(order,name,service,price,date,phone));
                        System.out.println("---");
                    }
                }
                if(resultSet.getString("paymentService").equals(serviceReport)&&(!((mm.equals("null")))&&((yy.equals("null")))  )){
                    if(checkDate.equals(resultSet.getString("paymentDate").substring(5,7))){
                        System.out.println("+++++++++++++");
                        name=resultSet.getString("paymentName");
                        phone=resultSet.getString("paymentPhone");
                        date=resultSet.getString("paymentDate");
                        service=resultSet.getString("paymentService");
                        price=resultSet.getInt("paymentPrice");
                        order=order+1;
                        DSPayment.add(new Payment(order,name,service,price,date,phone));
                    }
                }
                if(resultSet.getString("paymentService").equals(serviceReport)&&(((mm.equals("null")))&&(!(yy.equals("null")))  )){
                    if(checkDate.equals(resultSet.getString("paymentDate").substring(0,4))){
                        System.out.println("+++++++++++++");
                        name=resultSet.getString("paymentName");
                        phone=resultSet.getString("paymentPhone");
                        date=resultSet.getString("paymentDate");
                        service=resultSet.getString("paymentService");
                        price=resultSet.getInt("paymentPrice");
                        order=order+1;
                        DSPayment.add(new Payment(order,name,service,price,date,phone));
                    }

                }


            }
            conn.close();
            mm ="null";
            yy ="null";
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }



    }

    public void connectPayment3(){
        DSPayment.clear();
        order=0;
        if(!(mm.equals("null"))&&!(yy.equals("null"))){
            checkDate=yy+"-"+mm;
        }
        if(!(mm.equals("null"))&&(yy.equals("null"))){
            checkDate=mm;
        }
        if((mm.equals("null"))&&!((yy.equals("null")))){
            checkDate=yy;
        }
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Air.db";
            Connection conn = DriverManager.getConnection(dbURL);
            System.out.println("ok_DB");

            String query = "select * from Payment";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {


                if((!((mm.equals("null")))&&(!(yy.equals("null")))  )){
                    System.out.println("2222");
                    if(checkDate.equals(resultSet.getString("paymentDate").substring(0,7))){
                        name=resultSet.getString("paymentName");
                        phone=resultSet.getString("paymentPhone");
                        date=resultSet.getString("paymentDate");
                        service=resultSet.getString("paymentService");
                        price=resultSet.getInt("paymentPrice");
                        order=order+1;
                        DSPayment.add(new Payment(order,name,service,price,date,phone));
                        System.out.println("---");
                    }
                }
                if((!((mm.equals("null")))&&((yy.equals("null")))  )){
                    System.out.println("333");
                    if(checkDate.equals(resultSet.getString("paymentDate").substring(5,7))){
                        name=resultSet.getString("paymentName");
                        phone=resultSet.getString("paymentPhone");
                        date=resultSet.getString("paymentDate");
                        service=resultSet.getString("paymentService");
                        price=resultSet.getInt("paymentPrice");
                        order=order+1;
                        DSPayment.add(new Payment(order,name,service,price,date,phone));
                    }
                }
                if((((mm.equals("null")))&&(!(yy.equals("null")))  )){
                    System.out.println(checkDate+" 1");
                    System.out.println(resultSet.getString("paymentDate").substring(0,4)+" 2");
                    if(checkDate.equals(resultSet.getString("paymentDate").substring(0,4))){
                        System.out.println("innnnnnnnnnn");
                        name=resultSet.getString("paymentName");
                        phone=resultSet.getString("paymentPhone");
                        date=resultSet.getString("paymentDate");
                        service=resultSet.getString("paymentService");
                        price=resultSet.getInt("paymentPrice");
                        order=order+1;
                        DSPayment.add(new Payment(order,name,service,price,date,phone));
                    }
                }
            }
            mm ="null";
            yy ="null";
            conn.close();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void setService(String service){
        serviceReport=service;

    }
    public void setY(String y){
        yy=y;

    }
    public void setm(String m){
        mm=m;

    }
    public void setTec(String tecChoii){
        tecChoi=tecChoii;

    }




    }


