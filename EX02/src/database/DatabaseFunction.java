package database;

import java.sql.*;
import java.util.Scanner;

public class DatabaseFunction{
	private final String url = "jdbc:mysql://localhost:3306/mithalim?allowPublicKeyRetrieval=true&useSSL=false";
	private final String user = "root";
	private final String password = "zubur1";


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

	public void initialDB(){
	    this.createQueueReserved();
	    this.createDoctorTable();
        this.createPatientsTable();
        this.createQueueTable();
	    this.createDoctorRelativesView();
	    this.createDeleteQueueReservedTrigger();
    }
	
	
	private void createQueueReserved() {
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
	
	private void createDoctorTable(){
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

	private void createPatientsTable(){
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
	
	private void createQueueTable(){
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

    private void createDoctorRelativesView(){
        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
             Statement stmt = conn.createStatement()) {
            stmt.execute(Queries.VIEW_DOCTOR_RELATIVES);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createDeleteQueueReservedTrigger(){
        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
             Statement stmt = conn.createStatement()) {
            stmt.execute(Queries.TRIGGER_DELETE_QUEUE_RESERVED);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ResultSet waitingListByTime(){
        ResultSet rs = null;
		// SQL statement for creating a new table        
        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
                Statement stmt = conn.createStatement()) {
            Statement st = conn.createStatement();
            rs = st.executeQuery(Queries.SELECT_WAITING_PATIENTS_BY_TIME);
            return rs;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rs;
	}

	public void insertPatients(String pname, String plast, String gender){
        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
             PreparedStatement stmt = conn.prepareStatement(Queries.INSERT_PATIENTS)) {
            stmt.setString(1,pname);
            stmt.setString(2,plast);
            stmt.setString(3,gender);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertDoctors(String dname, String dlast, String dtype){
        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
             PreparedStatement stmt = conn.prepareStatement(Queries.INSERT_DOCTORS)) {
            stmt.setString(1,dname);
            stmt.setString(2,dlast);
            stmt.setString(3,dtype);
            stmt.executeUpdate();
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
			printResult(rs);
			
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
	}

    public ResultSet doctorRelativesView(){
	    ResultSet rs = null;
        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
             Statement stmt = conn.createStatement()) {
            rs = stmt.executeQuery(Queries.SELECT_ALL_VIEW_DOCTOR_RELATIVES);
            return rs;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String printResult(ResultSet rs) {
	    if (rs==null) return " ";
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
		
