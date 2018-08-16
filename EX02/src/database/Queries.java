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
							+ "queue_id INT NOT NULL primary key);\n";
	
	//Select statement
	public static String SELECT_ALL = " ";
	public static String SELECT_WAITING_PATIENTS_BY_TIME = "SELECT p_f_name, p_l_name, queue_time FROM Patients\n"
			+ "INNER JOIN Queue_Reserved ON (Patients.patient_id = Queue_Reserved.patient_id)\n"
			+ "ORDER BY queue_time;";
										
	public static String SELECT_ALL_VIEW_DOCTOR_ASSOSIETES = " ";
	
	//Update statement
	
	//Delete statement
	public static String DELETE_QUEUE = " ";
	
	//Trigger statement
	public static String TRIGGER_MANAGE_QUEUE = " ";
	
	//View statement
	public static String VIEW_DOCTOR_ASSOSIETES = " ";
}
