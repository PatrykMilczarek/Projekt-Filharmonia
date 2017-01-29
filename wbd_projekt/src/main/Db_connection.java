package main;

import java.sql.DriverManager;

import java.util.HashMap;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;

//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;

import java.sql.*;

public class Db_connection {

	// dodac wczytywanie z pliku

	private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";

	private static String USER;
	private static String PASS;

	public static Connection conn;

	public Db_connection() {

		getDBUserFromFile();
		try {
			// STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// STEP 3: Open a connection
			System.out.println("£¹czenie z baz¹ danych...");

			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Po³¹czono");

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (ClassNotFoundException ee) {
			ee.printStackTrace();
		}

	}

	public void getDBUserFromFile() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(
					"C:\\Users\\Patryk Milczarek\\git\\Projekt-Filharmonia\\wbd_projekt\\src\\main\\dbinfo.txt"));

			setUSER(in.readLine());
			setPASS(in.readLine());

			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static HashMap<String,String> getEmployeeInfoDB() {

		HashMap<String,String> map = new HashMap<String,String>();

		PreparedStatement preparedStatement;

		String query = "SELECT * from Pracownicy where id_pracownika=?";
		
		try {
			preparedStatement = conn.prepareStatement(query);
			
			preparedStatement.setString(1, CurrentUser.id_current_user+"");
		
			ResultSet resultSet = preparedStatement.executeQuery();

			
		
			resultSet.next();
			
			map.put("imie",resultSet.getString("imie"));
			map.put("nazwisko",resultSet.getString("nazwisko"));
			map.put("ulica",resultSet.getString("ulica"));
			map.put("miasto",resultSet.getString("miasto"));
			map.put("nr_budynku",resultSet.getString("nr_budynku"));
			map.put("nr_mieszkania",resultSet.getString("nr_mieszkania"));
			map.put("nr_telefonu",resultSet.getString("nr_telefonu"));
			map.put("e_mail",resultSet.getString("e_mail"));
		
			
			resultSet.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}
	
	public static HashMap<Integer,String> getEmployeeSalaryDB() {
		
		HashMap<Integer,String> map = new HashMap<Integer,String>();

		PreparedStatement preparedStatement;

		String query = "SELECT * from Wynagrodzenie where id_pracownika=?";
		
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, CurrentUser.id_current_user+"");
		
			ResultSet resultSet = preparedStatement.executeQuery();

			
			int i=1;
			while(resultSet.next()){
				
	
				map.put(i++,resultSet.getString("pensja"));
				map.put(i++,resultSet.getString("data_wyplaty"));
				
				
			}
			
			
			
			
			
		
			
			resultSet.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
		
		
	}

	public void closeConnection() {
		try {
			conn.close();
			System.out.println("Zakoñczono po³¹czenie");
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	public static boolean verifyLogin(String login, String pass) {

		PreparedStatement preparedStatement;

		String query = "SELECT * from Konta where login=? and haslo=?";
		boolean check = false;
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, pass);
			ResultSet resultSet = preparedStatement.executeQuery();

			check = resultSet.next();
			resultSet.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return check;
	}

	public static String checkUserType(String login, String pass) {
		PreparedStatement preparedStatement;

		String query = "SELECT typ_konta from Konta where login=? and haslo=?";
		String type = "unknown";
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, pass);
			ResultSet resultSet = preparedStatement.executeQuery();

			resultSet.next();

			type = resultSet.getString(1);
			resultSet.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return type;

	}

	public static long getUserId(String login, String pass) {
		long id = 0;

		PreparedStatement preparedStatement;

		String query = "SELECT id_pracownika from Konta where login=? and haslo=?";

		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, pass);
			ResultSet resultSet = preparedStatement.executeQuery();

			resultSet.next();

			id = resultSet.getLong(1);
			resultSet.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return id;
	}

	public static void add_symphony_db(String symph_name, String symph_address, String symph_num_house,
			String symph_town, String symph_tel_num, String symph_owner) {
		PreparedStatement preparedStatement;

		String query = "INSERT INTO Filharmonie values(?,?,?,?,?,?,?)";

		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, symph_name);
			preparedStatement.setString(2, symph_town);
			preparedStatement.setString(3, "xxx");
			preparedStatement.setString(2, symph_town);
			preparedStatement.setString(2, symph_town);
			preparedStatement.setString(2, symph_town);
			preparedStatement.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void setUSER(String uSER) {
		USER = uSER;
	}

	public static void setPASS(String pASS) {
		PASS = pASS;
	}

}
