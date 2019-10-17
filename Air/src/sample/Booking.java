package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Booking {
    private SimpleIntegerProperty order;
    private SimpleStringProperty name;
    private SimpleStringProperty phone;
    private SimpleStringProperty date;
    private SimpleStringProperty service;
    private SimpleStringProperty timeBooking;
    private SimpleStringProperty address;
    private SimpleIntegerProperty price;
    private SimpleStringProperty status;
    private SimpleStringProperty tec;

    public Booking(Integer order, String name, String phone,
                   String date, String service, String timeBooking,
                   String address, Integer price, String status,String tec) {
        this.order = new SimpleIntegerProperty(order);
        this.name = new SimpleStringProperty(name);
        this.phone = new SimpleStringProperty(phone);
        this.date = new SimpleStringProperty(date);
        this.service = new SimpleStringProperty(service);
        this.timeBooking = new SimpleStringProperty(timeBooking);
        this.address = new SimpleStringProperty(address);
        this.price = new SimpleIntegerProperty(price);
        this.status = new SimpleStringProperty(status);
        this.tec = new SimpleStringProperty(tec);
    }
    public String getName() { return name.get(); }

    public Integer getOrder() { return order.get(); }
    public String getPhone() {
        return phone.get();
    }
    public String getDate() {
        return date.get();
    }
    public String getService() {
        return service.get();
    }
    public int getPrice() {
        return price.get();
    }

    public String getTimeBooking() {
        return timeBooking.get();
    }

    public String getAddress() {
        return address.get();
    }


    public String getStatus() {
        return status.get();
    }

    public String getTec() {
        return tec.get();
    }

}
