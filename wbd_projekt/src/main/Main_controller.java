package main;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class Main_controller {
	
public TextField symph_name, symph_address, symph_num_house, symph_town, symph_tel_num, symph_owner;
public Label name_label,symphony_label,account_label,surname_label,
e_mail_label,profession_label,tel_label,start_work_label,house_num_label,town_label,address_label;

public ListView<String> user_list_view;

@FXML
public void initialize(){

	showUserInfo();
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
	
}
