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
@FXML public TableColumn<Event, String> event_name,event_time;
@FXML public TableColumn<Event, String> event_start;
@FXML public TableColumn<Event, Date> event_date;
@FXML public TableColumn<Event, Integer> event_seats;
@FXML public TableColumn<Event, String> event_symphony;
public ListView<String> user_list_view,salary_history_list, symphony_list_view;
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
@FXML public Button reservation_button;
@FXML public Button modyfie_worker, show_button, modyfie_symph, save_symph_button;

public static String nazw;
public String search_option="";


@FXML
public void initialize(){
	initColumnsEmployee();
	initEventColumns();
	showUserInfo();
	refreshEmployee();
	refreshEvents();
	showSymphonies();
	
	search_worker_choicebox.getItems().addAll("Nazwisko","Filharmonia","Miasto");
	search_worker_choicebox.valueProperty().addListener((v,oldValue,newValue)-> search_option = newValue);

	search_worker_textfield.textProperty().addListener((v,oldv,newv)->{
		
	EmployeeTable.setItems(Db_connection.getEmployeeInfoByAttribute(newv,search_option));
	});

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

public void showSymphonyInfo(){
	String symphonySelected;
	symphonySelected = symphony_list_view.getSelectionModel().getSelectedItem();
    
    HashMap<String,String> symphony_map=Db_connection.findSymphony(symphonySelected);
	nazw=symphony_map.get("wlasciciele");
	String[] parts2 = nazw.split(" ");
	
	nazw = parts2[1];
    symph_name.setText(symphony_map.get("nazwa"));
	symph_address.setText(symphony_map.get("ulica"));
	symph_num_house.setText(symphony_map.get("nr_budynku"));
	symph_town.setText(symphony_map.get("miasto"));
	symph_tel_num.setText(symphony_map.get("nr_telefonu"));
	symph_owner.setText(symphony_map.get("wlasciciele"));
	
}

public void editSymph(){

	symph_name.setEditable(true);
	symph_name.setDisable(false);
	symph_address.setEditable(true);
	symph_address.setDisable(false);
	symph_num_house.setEditable(true);
	 symph_town.setEditable(true);
	 symph_tel_num.setEditable(true);
	 symph_owner.setEditable(true);
	 symph_num_house.setDisable(false);
	 symph_town.setDisable(false);
	 symph_tel_num.setDisable(false);
	 symph_owner.setDisable(false);

}

public void stopEditSymph(){
	symph_name.setEditable(false);
	symph_name.setDisable(true);
	symph_address.setEditable(false);
	symph_address.setDisable(true);
	symph_num_house.setEditable(false);
	 symph_town.setEditable(false);
	 symph_tel_num.setEditable(false);
	 symph_owner.setEditable(false);
	 symph_num_house.setDisable(true);
	 symph_town.setDisable(true);
	 symph_tel_num.setDisable(true);
	 symph_owner.setDisable(true);
}

public void save_symph_modification(){
	

	String nazw_symph=symph_name.getText();
	
	if (symph_name.isDisable()==false)
	{
		
		Db_connection.modify_symph(symph_name.getText(), symph_address.getText(), symph_num_house.getText(),
				symph_town.getText(), symph_tel_num.getText(), symph_owner.getText(), nazw,nazw_symph);
		
		stopEditSymph();
		
		
	}
	
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

public void showSymphonies(){
	
	HashMap<Integer,String> info_map=Db_connection.getSymphonyDBlist();
	ObservableList<String> info_items =FXCollections.observableArrayList();
	for (int i=0;i<info_map.size(); i++){
	info_items.add(info_map.get(i));}
	
	
	symphony_list_view.setItems(info_items);
	
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

	

