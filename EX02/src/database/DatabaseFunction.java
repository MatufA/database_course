package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import database.Queries;

public class DatabaseFunction{
	private final String url = "jdbc:mysql://localhost:3306/mithalim?allowPublicKeyRetrieval=true&useSSL=false";
	private final String user = "root";
	private final String password = "yehuda123";

	public DatabaseFunction(){
		String url = "jdbc:mysql://localhost:3306/?allowPublicKeyRetrieval=true&useSSL=false";
		try (Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement()) {
            // create a new DataBase
            stmt.execute(Queries.CREATE_DATABASE);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	
	
	public void CREATE_QUEUE_RESERVED() {
        // SQL statement for creating a new table        
        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
                Statement stmt = conn.createStatement()) {
/*            String sql = "Alter table Queue_Reserved Add primary key (doctor_id)"; //declare PK
            stmt.executeUpdate(sql);*/
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
/*            String sql = "Alter table Doctors Add primary key (doctor_id)"; //declare PK
            stmt.executeUpdate(sql);*/
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
        	
       /*     String sql = "Alter table Patients Add primary key (patient_id)"; //declare PK
            stmt.executeUpdate(sql);*/
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
            /*String sql = "Alter table Queue Add primary key (queue_id)"; //declare PK
            stmt.executeUpdate(sql);*/
            // create a new table
            stmt.execute(Queries.CREATE_QUEUE_TABLE);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	
	public void waitingListByTime(){
		// SQL statement for creating a new table        
        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
                Statement stmt = conn.createStatement()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(Queries.SELECT_WAITING_PATIENTS_BY_TIME);
            toString(rs);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	
	public void updateQueue() {
		Scanner fName = new Scanner( System.in );
		System.out.println("Insert First name of patient: ");
		String p_f_name_input = fName.nextLine();
		Scanner lName = new Scanner( System.in );
		System.out.println("Insert Last name of patient: ");
		String p_l_name_input = lName.nextLine();
		System.out.println( "Patient name is " + p_f_name_input + " " + p_l_name_input );
		
		try {
			Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
			String dpProcedure = "drop procedure if exists update_queue"; //drop procedure if exists
			Statement st = conn.createStatement();
			st.execute(dpProcedure);
			
			/*String ctprocedure = "create procedure update_queue() begin select time from Queue where queue_id = 1; end";
			Statement st2 = conn.createStatement();
			st.execute(ctprocedure);*/
			
			st.execute(Queries.UPDATE_QUEUE_PROCEDURE);
			CallableStatement cs = conn.prepareCall("{call update_queue()}");
			ResultSet rs = cs.executeQuery();
			toString(rs);
			
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	String toString(ResultSet rs) {
		try {
			int numOfColumns = rs.getMetaData().getColumnCount();
            while (rs.next()) {
            	for (int col = 1; col <= numOfColumns; col++) {
            		System.out.print(rs.getString(col) + " ");
            		}
            	System.out.println();
            }
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return " ";
	}
}
		
