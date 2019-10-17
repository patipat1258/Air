package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Payment {

    public SimpleIntegerProperty paymentID;
    public SimpleStringProperty paymentName;
    public SimpleStringProperty paymentService;
    public SimpleIntegerProperty paymentPrice;
    public SimpleStringProperty paymentDate;
    public SimpleStringProperty paymentPhone;


    public Payment(Integer paymentID,String paymentName,String paymentService, Integer paymentPrice,String paymentDate,String paymentPhone) {

        this.paymentID= new SimpleIntegerProperty(paymentID);
        this.paymentName= new SimpleStringProperty(paymentName);
        this.paymentService= new SimpleStringProperty(paymentService);
        this.paymentPrice= new SimpleIntegerProperty(paymentPrice);
        this.paymentDate= new SimpleStringProperty(paymentDate);
        this.paymentPhone= new SimpleStringProperty(paymentPhone);

    }
    public Integer getPaymentID() {
        return paymentID.get();
    }
    public String getPaymentName(){
        return paymentName.get();
    }
    public String getPaymentService(){
        return paymentService.get();
    }
    public Integer getPaymentPrice() {
        return paymentPrice.get();
    }
    public String getPaymentDate(){
        return paymentDate.get();
    }
    public String getPaymentPhone(){
        return paymentPhone.get();
    }



}
