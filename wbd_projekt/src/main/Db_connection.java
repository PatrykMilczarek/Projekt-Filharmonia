package main;


import java.sql.DriverManager;

//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;

import java.sql.*;

public class Db_connection {
	
	//dodac wczytywanie z pliku 
	
	private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";

    private static final String USER = "Ewelina";
    private static final String PASS = "password";
    
    public static Connection conn;
    
    public Db_connection(){


        try{
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("£¹czenie z baz¹ danych...");
            
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("Po³¹czono");
            
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(ClassNotFoundException ee){
            ee.printStackTrace();
        }

    }
    
    public void closeConnection(){
        try{
        conn.close();
            System.out.println("Zakoñczono po³¹czenie");
        }
        catch(SQLException se){
            se.printStackTrace();
        }
    }
    
    public static boolean verifyLogin(String login, String pass) {
    	
    	PreparedStatement preparedStatement;
    	
    	String query = "SELECT * from Konta where login=? and haslo=?";
    	boolean check=false;
    	try {
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, pass);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        check=resultSet.next();
    
       
     
    	} catch (Exception e){ e.printStackTrace();}
        

    	
        return check;
    }
    
    public static String checkUserType(String login, String pass)
    {
PreparedStatement preparedStatement;
    	
    	String query = "SELECT typ_konta from Konta where login=? and haslo=?";
    	String type="unknown";
    	try {
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, pass);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        resultSet.next();
    
       type=resultSet.getString(1);
     
    	} catch (Exception e){ e.printStackTrace();}
       
    	
        return type;
    
    }
    
    public static void add_symphony_db(String symph_name,String symph_address,String symph_num_house,String symph_town,String symph_tel_num,String symph_owner){
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
        
 
     
    	} catch (Exception e){ e.printStackTrace();}
    	
    }

    
}
