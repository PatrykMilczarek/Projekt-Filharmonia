package main;


import java.sql.Date;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
@FXML public TableColumn<Event, String> event_time;
public ListView<String> user_list_view,salary_history_list;
public static ObservableList<Event> event_list;

@FXML public TableView<Employee> EmployeeTable;
@FXML public TableView<Event> EventsTable;
@FXML public TableColumn<Employee, String> name_worker_column;
@FXML public TableColumn<Employee, String> surname_worker_column;
@FXML public TableColumn<Employee, String> address_worker_column;
@FXML public TableColumn<Employee, String> house_num_worker_column;
@FXML public TableColumn<Employee, String> town_worker_column;
@FXML public TableColumn<Employee, String> pesel_worker_column;
@FXML public TableColumn<Employee, String> profession_worker_column;
@FXML public TableColumn<Employee, String> symphony_worker_column;
@FXML public TextField search_worker_textfield;
@FXML public ChoiceBox<String> search_worker_choicebox;
@FXML public Button add_worker;
@FXML public Button delete_worker;
@FXML public Button modyfie_worker,reservation_button;



@FXML
public void initialize(){
	initColumnsEmployee();
	initEventColumns();
	showUserInfo();
	refreshEmployee();
	refreshEvents();
	
	
}


public void initEventColumns(){
	event_id.setCellValueFactory(new PropertyValueFactory<>("id"));
	event_name.setCellValueFactory(new PropertyValueFactory<>("name"));
	event_start.setCellValueFactory(new PropertyValueFactory<>("hour"));
	event_date.setCellValueFactory(new PropertyValueFactory<>("date"));
	event_seats.setCellValueFactory(new PropertyValueFactory<>("max_seats_number"));
	event_symphony.setCellValueFactory(new PropertyValueFactory<>("symphony"));
	event_time.setCellValueFactory(new PropertyValueFactory<>("time"));

}
public void add_symphony(){
	
	Db_connection.add_symphony_db(symph_name.getText(), symph_address.getText(), symph_num_house.getText(), symph_town.getText(), symph_tel_num.getText(), symph_owner.getText());
	
}


public void showUserInfo(){
	HashMap<String,String> info_map=Db_connection.getCurrentUserInfo();
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


public void initColumnsEmployee(){
	name_worker_column.setCellValueFactory(new PropertyValueFactory<>("name_worker"));
	surname_worker_column.setCellValueFactory(new PropertyValueFactory<>("surname_worker"));
	address_worker_column.setCellValueFactory(new PropertyValueFactory<>("address_worker"));
	house_num_worker_column.setCellValueFactory(new PropertyValueFactory<>("house_num_worker"));
	town_worker_column.setCellValueFactory(new PropertyValueFactory<>("town_worker"));
	pesel_worker_column.setCellValueFactory(new PropertyValueFactory<>("pesel_worker"));
	profession_worker_column.setCellValueFactory(new PropertyValueFactory<>("profession_worker"));
	name_worker_column.setCellValueFactory(new PropertyValueFactory<>("name_worker"));
	symphony_worker_column.setCellValueFactory(new PropertyValueFactory<>("symphony_worker"));
}

public void refreshEmployee(){

   EmployeeTable.setItems(Db_connection.getEmployeeInfo());

}


public void refreshEvents(){
	event_list=Db_connection.getEventsInfo();

	EventsTable.setItems(event_list);
}

public void reserveEvent(){
	Event selectedEvent= EventsTable.getSelectionModel().getSelectedItem();
	try{
		
		
		if(selectedEvent != null){
		Reservation_controller.setEventNameAndId(selectedEvent.getId(),selectedEvent.getName());
		}
		
		Parent reserve_root = FXMLLoader.load(getClass().getResource("/main/reservation_list_window.fxml"));

		main.reservation_stage.setScene(new Scene(reserve_root));
		main.reservation_stage.setTitle("Rezerwacja");

		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	if(selectedEvent != null){
		main.reservation_stage.showAndWait();}
}
}

	

