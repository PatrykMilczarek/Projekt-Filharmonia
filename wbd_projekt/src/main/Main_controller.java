package main;


import java.sql.Date;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Main_controller {
	
public TextField symph_name, symph_address, symph_num_house, symph_town, symph_tel_num, symph_owner;
public Label name_label,symphony_label,account_label,surname_label,
e_mail_label,profession_label,tel_label,start_work_label,house_num_label,town_label,address_label,welcome_label;
@FXML public TableColumn<Event, Integer> event_id;
@FXML public TableColumn<Event, String> event_name;
@FXML public TableColumn<Event, String> event_start;
@FXML public TableColumn<Event, Date> event_date;
@FXML public TableColumn<Event, Integer> event_seats;
@FXML public TableColumn<Event, String> event_symphony;
public ListView<String> user_list_view,salary_history_list;

@FXML
public void initialize(){

	showUserInfo();
}


public void initEventColumns(){
	event_id.setCellValueFactory(new PropertyValueFactory<>("id"));
	event_name.setCellValueFactory(new PropertyValueFactory<>("name"));
	event_start.setCellValueFactory(new PropertyValueFactory<>("hour"));
	event_date.setCellValueFactory(new PropertyValueFactory<>("date"));
	event_seats.setCellValueFactory(new PropertyValueFactory<>("max_seats_number"));
	event_symphony.setCellValueFactory(new PropertyValueFactory<>("symphony"));

}
public void add_symphony(){
	
	Db_connection.add_symphony_db(symph_name.getText(), symph_address.getText(), symph_num_house.getText(), symph_town.getText(), symph_tel_num.getText(), symph_owner.getText());
	
}

public void showUserInfo(){
	HashMap<String,String> info_map=Db_connection.getEmployeeInfoDB();
	ObservableList<String> info_items =FXCollections.observableArrayList();
	
	HashMap<Integer,String> salary_map=Db_connection.getEmployeeSalaryDB();
	ObservableList<String> salary_items =FXCollections.observableArrayList();
	
	
	welcome_label.setText("Witaj, "+info_map.get("imie"));
	info_items.add("Imie: "+ info_map.get("imie"));
	info_items.add("Nazwisko: "+ info_map.get("nazwisko"));
	info_items.add("Adres: "+ "ul."+info_map.get("ulica") + info_map.get("nr_budynku")+"/"+info_map.get("nr_mieszkania")+" "+ info_map.get("miasto"));

	info_items.add("Numer telefonu: "+ info_map.get("nr_telefonu"));
	info_items.add("Email: "+ info_map.get("e_mail"));

	
	for(int i=1;i<salary_map.size();i=i+2){
	
		salary_items.add("Wyplata: "+ salary_map.get(i) + "			Data wyplaty: "+salary_map.get(i+1));
	}
	
	
	
	user_list_view.setItems(info_items);
	salary_history_list.setItems(salary_items);
}
	
}
