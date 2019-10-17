package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class AddressControl {
    @FXML
    TextArea showAddress;


    public void initialize(){
        BookingControl bookingControl = new BookingControl();


        showAddress.setText(bookingControl.setAddress());

    }
}
