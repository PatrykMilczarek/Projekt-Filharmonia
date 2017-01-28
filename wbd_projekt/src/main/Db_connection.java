package main;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;

import java.sql.*;

public class Db_connection {

	private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
	
    public Statement stmt = null;
    
	private static String USER;
	private static String PASS;

	public static Connection conn;
	
	ObservableList<Employee> worker = FXCollections.observableArrayList();

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
					"C:\\Smart Git\\Projekt-Filharmonia\\wbd_projekt\\src\\main\\dbinfo.txt"));

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

	//nie ma insertowania wlaciciela
	public static void add_symphony_db(String symph_name, String symph_address, String symph_num_house,
			String symph_town, String symph_tel_num, String symph_owner) {
		PreparedStatement preparedStatement;

		String query = "INSERT INTO Filharmonie values(?,?,?,?,?,?,?)";

		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, symph_name);
			preparedStatement.setString(2, symph_town);
			preparedStatement.setString(3, "xxx");
			preparedStatement.setString(4, symph_address);
			preparedStatement.setString(5, symph_num_house);
			preparedStatement.setString(6, symph_tel_num);
			preparedStatement.setString(7, "yyy");
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
	
	  
	public ObservableList<Employee> getEmployeeInfo(){
		
	        ObservableList<Employee> flights = FXCollections.observableArrayList();

	        try{
	            System.out.println("Creating statement...");
	            stmt = conn.createStatement();

	            String sql = "SELECT imie,nazwisko,ulica,nr_budynku, miasto, pesel, nazwa_stanowiska, nazwa FROM Pracownicy join stanowiska using(id_pracownika) join filharmonie using(id_filharmonii) ";
	            ResultSet rs = stmt.executeQuery(sql);


	            while(rs.next()){

	                String name_worker  = rs.getInt("imie");

	                String surname_worker = rs.getString("nazwisko");
	                String address_worker = rs.getString("ulica");

	                String house_num_worker = rs.getDate("nr_budynku");
	                String town_worker = rs.getString("miasto");

	                String pesel_worker = rs.getString("pesel");
	                String profession_worker = rs.getString("nazwa_stanowiska");
	                String symphony_worker = rs.getString("nazwa");


	               worker.add(new Employee(name_worker, surname_worker, address_worker, house_num_worker, town_worker, pesel_worker, profession_worker, symphony_worker));

	            }

	            rs.close();
	            stmt.close();



	        }catch(SQLException se){
	            se.printStackTrace();
	        }finally{
	            try{
	                if(stmt!=null)
	                    stmt.close();
	            }catch(SQLException se2){
	                se2.printStackTrace();
	            }


	        }

	        return flights;
	    }

}
