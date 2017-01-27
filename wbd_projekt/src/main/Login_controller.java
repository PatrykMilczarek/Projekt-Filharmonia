package main;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Login_controller {
	
	public TextField login_text;
	public PasswordField pass_text;
	public Button ok_button;
	public Label error_label;
	
	public void login() {
		
		String login, pass;
		
		login= login_text.getText();
		pass= pass_text.getText();
		
		boolean check= Db_connection.verifyLogin(login,pass);
		
		if (check) {
			
			Db_connection.checkUserType(login, pass);
			
			main.login_stage.hide();
			main.main_stage.show();
			
			
			
		}
		else {error_label.setText("Niepoprawny login lub has³o");
		error_label.setStyle("-fx-text-fill:red;");
		
		
		}
	}

}
