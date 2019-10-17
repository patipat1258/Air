package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Technician {
    public SimpleIntegerProperty tecOrder;
    public SimpleStringProperty tecName;
    public SimpleStringProperty tecPhone;
    public SimpleStringProperty tecStatus;
    public SimpleStringProperty tecGroup;

    public Technician(Integer tecOrder,String tecName, String tecPhone, String tecStatus, String tecGroup) {

        this.tecOrder= new SimpleIntegerProperty(tecOrder);
        this.tecName= new SimpleStringProperty(tecName);
        this.tecPhone= new SimpleStringProperty(tecPhone);
        this.tecStatus= new SimpleStringProperty(tecStatus);
        this.tecGroup= new SimpleStringProperty(tecGroup);

    }
    public Integer getTecOrder(){
        return tecOrder.get();
    }
    public String getTecName(){
        return tecName.get();
    }
    public String getTecPhone() {
        return tecPhone.get();
    }
    public String getTecStatus() {
        return tecStatus.get();
    }

    public String getTecGroup() {
        return tecGroup.get();
    }





}
