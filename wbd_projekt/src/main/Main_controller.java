package main;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
e_mail_label,profession_label,tel_label,start_work_label,house_num_label,town_label,address_label;

@FXML public TableView<Employee> EmployeeTable;
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
@FXML public Button modyfie_worker;

public ListView<String> user_list_view;

@FXML
public void initialize(){

	showUserInfo();
	refreshEmployee();
	
	
}
public void add_symphony(){
	
	Db_connection.add_symphony_db(symph_name.getText(), symph_address.getText(), symph_num_house.getText(), symph_town.getText(), symph_tel_num.getText(), symph_owner.getText());
	
}

public void showUserInfo(){
	HashMap<String,String> info_map=Db_connection.getEmployeeInfoDB();
	ObservableList<String> items =FXCollections.observableArrayList ();
	
	items.add("Imie: "+ info_map.get("imie"));
	items.add("Nazwisko: "+ info_map.get("nazwisko"));
	items.add("Adres: "+ "ul."+info_map.get("ulica") + info_map.get("nr_budynku")+"/"+info_map.get("nr_mieszkania")+" "+ info_map.get("miasto"));

	items.add("Numer telefonu: "+ info_map.get("nr_telefonu"));
	items.add("Email: "+ info_map.get("e_mail"));

	user_list_view.setItems(items);
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

	// dodac okienko ktore sie bedzie otwierac!
    EmployeeTable.setItems(main.Db_connection.getEmployeeInfo());

}



	
}
