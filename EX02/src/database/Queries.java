package database;

public class Queries {

	//Create statement
	public static String CREATE_DATABASE = "CREATE DATABASE IF NOT EXISTS mithalim";
	
	public static String CREATE_QUEUE_RESERVED_TABLE = "CREATE TABLE IF NOT EXISTS Queue_Reserved (\n"
								+ "queue_id INT NOT NULL, \n"
								+ "patient_id INT NOT NULL,\n"
								+ "queue_time DATETIME NOT NULL,\n"
								+ "doctor_id INT NOT NULL);\n ";
	
	public static String CREATE_DOCTOR_TABLE = "CREATE TABLE IF NOT EXISTS Doctors (\n"
							+ "doctor_id INT NOT NULL primary key, \n"
							+ "d_f_name VARCHAR(20) NOT NULL,\n"
							+ "d_l_name VARCHAR(20) NOT NULL,\n"
							+ "doctor_type VARCHAR(40) NOT NULL);\n ";
	
	public static String CREATE_PATIENTS_TABLE = "CREATE TABLE IF NOT EXISTS Patients (\n"
							+ "patient_id INT NOT NULL primary key, \n"
							+ "p_f_name VARCHAR(20) NOT NULL,\n"
							+ "p_l_name VARCHAR(20) NOT NULL,\n"
							+ "gender VARCHAR(20) NOT NULL);\n";
	
	public static String CREATE_QUEUE_TABLE =  "CREATE TABLE IF NOT EXISTS Queue (\n"
							+ "queue_id INT NOT NULL primary key,\n"
							+ "time DATETIME NOT NULL);\n";
	
	//Select statement
	public static String SELECT_WAITING_PATIENTS_BY_TIME = "SELECT p_f_name, p_l_name, queue_time FROM Patients\n"
			+ "INNER JOIN Queue_Reserved ON (Patients.patient_id = Queue_Reserved.patient_id)\n"
			+ "ORDER BY queue_time;";
										
	public static String SELECT_ALL_VIEW_DOCTOR_RELATIVES = "SELECT * FROM doctor_relatives;";

	//Insert statement
    public static String INSERT_PATIENTS = "INSERT INTO Patients (p_f_name, p_l_name, gender)\n" +
            "VALUES (?, ?, ?);";

    public static String INSERT_DOCTORS = "INSERT INTO Doctors (d_f_name, d_l_name, doctor_type)\n" +
            "VALUES (?, ?, ?);";
	
	//Update statement
	
	//Delete statement
	public static String DELETE_QUEUE = " ";
	
	//Trigger statement
	public static String TRIGGER_DELETE_QUEUE_RESERVED = "CREATE TRIGGER delete_queue_reserved AFTER INSERT ON Queue\n" +
            "  FOR EACH ROW\n" +
            "  DELETE FROM Queue_Reserved WHERE queue_id=NEW.queue_id;";
	
	//View statement
	public static String VIEW_DOCTOR_RELATIVES = "CREATE OR REPLACE VIEW IF NOT EXISTS doctor_relatives AS\n" +
            "  SELECT Patients.p_f_name, Patients.p_l_name FROM Patients\n" +
            "  INNER JOIN Doctors ON (Doctors.d_l_name=Patients.p_l_name);";
	
	//Procedure statement
	public static String UPDATE_QUEUE_PROCEDURE = "create procedure update_queue() begin select * from Queue; end";


}
