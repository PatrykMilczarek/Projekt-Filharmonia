package main;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Reservation_controller {
	
	
public Label event_name_label;
public static long event_id;
public static String event_name;


@FXML public void initialize(){
	currentEventInfo();
}
public void currentEventInfo(){
	
	event_name_label.setText("Nazwa: "+event_name+ " Id: "+event_id);
}

public static void setEventNameAndId(long id,String name){
	System.out.println(name);
	event_id=id;
	event_name=name;

}

}
