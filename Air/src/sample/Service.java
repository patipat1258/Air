package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Service {

    private SimpleStringProperty serviceName;
    private SimpleIntegerProperty servicePrice;
    private SimpleIntegerProperty serviceorder;



    public Service(String serviceName, Integer servicePrice,Integer serviceorder) {


        this.serviceName= new SimpleStringProperty(serviceName);
        this.servicePrice= new SimpleIntegerProperty(servicePrice);
        this.serviceorder= new SimpleIntegerProperty(serviceorder);
    }
    public String getServiceName(){
        return serviceName.get();
    }
    public Integer getServicePrice() {
        return servicePrice.get();
    }
    public Integer getServiceOrder() {
        return serviceorder.get();
    }

}
