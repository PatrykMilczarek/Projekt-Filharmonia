package main;

import javafx.scene.control.TextField;

public class Main_controller {
	
public TextField symph_name, symph_address, symph_num_house, symph_town, symph_tel_num, symph_owner;

public void add_symphony(){
	
	Db_connection.add_symphony_db(symph_name.getText(), symph_address.getText(), symph_num_house.getText(), symph_town.getText(), symph_tel_num.getText(), symph_owner.getText());
	
}


	
}
