package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class main extends Application {
	public static Stage main_stage,login_stage;
	
	public void start(Stage primaryStage) {
		login_stage=primaryStage;
		Db_connection db = new Db_connection();
		
		try {
			Parent login_root = FXMLLoader.load(getClass().getResource("/main/login_window.fxml"));
			Parent main_root = FXMLLoader.load(getClass().getResource("/main/main_window.fxml"));
			
			main_stage= new Stage();
			
			Scene scene_login = new Scene(login_root);
			Scene scene_main = new Scene(main_root);
			
			main_stage.setScene(scene_main);
			login_stage.setScene(scene_login);
			
			
			login_stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
