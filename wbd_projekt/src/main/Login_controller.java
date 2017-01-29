package main;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
		
		login= login_text																																									.getText();
		pass= pass_text.getText();
		
		boolean check= Db_connection.verifyLogin(login,pass);
		
		if (check) {
			
			Db_connection.checkUserType(login, pass);
			CurrentUser.id_current_user=Db_connection.getUserId(login, pass);
			
			main.login_stage.hide();
			
			try{
			Parent main_root = FXMLLoader.load(getClass().getResource("/main/main_window.fxml"));
			main.main_stage.setScene(new Scene(main_root));
			main.main_stage.setTitle("System Zarz¹dzania Filharmoni¹");
			}catch(Exception e){
				e.printStackTrace();
			}
			main.main_stage.show();
			
			
		}
		else {error_label.setText("Niepoprawny login lub has³o");
		error_label.setStyle("-fx-text-fill:red;");
		
		
		}
	}

}
