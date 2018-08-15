package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.Queries;

public class DatabaseFunction{
	private final String url_a = "jdbc:mysql://localhost:3306/";
	private final String url_b = "?allowPublicKeyRetrieval=true&useSSL=false";
	private final String user = "root";
	private final String password = "yehuda123";
	private String url = "";
	
	DatabaseFunction(String DB_name){
		this.url = url_a + DB_name + url_b;
		try (Connection conn = DriverManager.getConnection(url_a + url_b, user, password);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(Queries.CREATE_DATABASE);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		System.out.println(url);
	}
	
	
	public void create_QUEUE_RESERVED() {
        // SQL statement for creating a new table        
        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(Queries.CREATE_QUEUE_RESERVED_TABLE);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	
	public void CREATE_DOCTOR_TABLE(){
		// SQL statement for creating a new table        
        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(Queries.CREATE_DOCTOR_TABLE);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}

	public void CREATE_PATIENTS_TABLE(){
		// SQL statement for creating a new table        
        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(Queries.CREATE_PATIENTS_TABLE);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	
	public void CREATE_QUEUE_TABLE(){
		// SQL statement for creating a new table        
        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(Queries.CREATE_QUEUE_TABLE);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	
	public static void main(String[] args) {
	
		DatabaseFunction a = new DatabaseFunction("mithalim");
		a.create_QUEUE_RESERVED();
		a.CREATE_DOCTOR_TABLE();
		a.CREATE_PATIENTS_TABLE();
		a.CREATE_QUEUE_TABLE();
    }	
}
		
